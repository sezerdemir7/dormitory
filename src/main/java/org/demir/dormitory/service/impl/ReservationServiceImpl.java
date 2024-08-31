package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.ReservationRequest;
import org.demir.dormitory.dto.request.ReservationUpdateRequest;
import org.demir.dormitory.dto.response.PlayGroundResponse;
import org.demir.dormitory.dto.response.ReservationResponse;
import org.demir.dormitory.entity.PlayGround;
import org.demir.dormitory.entity.Reservation;
import org.demir.dormitory.entity.Student;
import org.demir.dormitory.exception.BadRequestException;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.ReservationRepository;
import org.demir.dormitory.service.*;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final StudentService studentService;
    private final PlayGroundService playGroundService;
    private final RabbitMQProducer rabbitMQProducer;
    private final SimpMessagingTemplate messagingTemplate;
    private final IdGeneratorService idGeneratorService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, StudentService studentService,
                                  PlayGroundService playGroundService, RabbitMQProducer rabbitMQProducer, SimpMessagingTemplate messagingTemplate, IdGeneratorService idGeneratorService) {
        this.reservationRepository = reservationRepository;
        this.studentService = studentService;
        this.playGroundService = playGroundService;
        this.rabbitMQProducer = rabbitMQProducer;
        this.messagingTemplate = messagingTemplate;
        this.idGeneratorService = idGeneratorService;
    }

    @Override
    public ReservationResponse saveReservation(ReservationRequest request) {
        if(!playGroundControl(request)){
            throw new BadRequestException("Play ground has already reservation!");
        }
        Reservation toSave = mapToReservation(request);
        Reservation reservation = reservationRepository.save(toSave);
        return mapToResponse(reservation);
    }

    public boolean playGroundControl(ReservationRequest request){
//        Reservation reservation=reservationRepository.findTop1ByPlayGroundIdOrderByIdDesc(request.playGroundId());
//        if(reservation!=null && reservation.getEndDate().isBefore(request.startDate())){
//            return false;
//        }
//        return true;
        PlayGround playGround= playGroundService.getPlayGroundById(request.playGroundId());
        if(playGround.isAvailable()==true ){
            return true;
        }
        return false;
    }

    @Override
    public void deleteReservation(Long reservationId) {
        Reservation reservation = findReservationById(reservationId);
        reservation.setDeleted(true);
        reservationRepository.save(reservation);
    }

    @Override
    public ReservationResponse getOneReservationById(Long reservationId) {
        Reservation reservation = findReservationById(reservationId);
        return mapToResponse(reservation);
    }

    @Override
    public List<ReservationResponse> getAllReservation() {
        List<Reservation> reservations = reservationRepository.findAllByIsDeletedFalse();
        return mapToResponseList(reservations);
    }

    @Override
    public ReservationResponse updateReservation(ReservationUpdateRequest request) {
        Reservation toUpdate = findReservationById(request.id());
        toUpdate.setStartDate(request.startDate());
        toUpdate.setEndDate(request.endDate());
        Reservation reservation = reservationRepository.save(toUpdate);
        return mapToResponse(reservation);
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        return findReservationById(reservationId);
    }

    @Override
    public ReservationResponse approveReservation(Long reservationId) {
        Reservation toUpdate = findReservationById(reservationId);
        if(!toUpdate.getPlayGround().isAvailable()){
            throw new BadRequestException("Play ground has already reservation!");
        }
        Student student=toUpdate.getStudent();
        String mail=student.getContactInfo().getEmail();

        rabbitMQProducer.sendMailAddressToQueue(mail);

        PlayGround playGround=toUpdate.getPlayGround();
        playGround.setAvailable(false);
        PlayGroundResponse playGroundResponse=playGroundService.savePlayGround(playGround);
        toUpdate.setApproved(true);

        messagingTemplate.convertAndSend("/topic/playground-status", playGroundResponse);

        Reservation reservation = reservationRepository.save(toUpdate);
        return mapToResponse(reservation);
    }

    @Override
    public List<ReservationResponse> getReservationsWhereApprovedFalse() {
        List<Reservation> reservationList = reservationRepository.findReservationByIsApprovedFalse();
        return mapToResponseList(reservationList);
    }

    @Override
    public List<ReservationResponse> getReservationsWhereApprovedTrue() {
        List<Reservation> reservationList = reservationRepository.findReservationByIsApprovedTrue();
        return mapToResponseList(reservationList);
    }

    @Override
    public List<Reservation> findByEndDateBeforeAndStatusTrue() {
        LocalDateTime now = LocalDateTime.now();
        return reservationRepository.findByEndDateBeforeAndStatusTrue(now);
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    private Reservation findReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation not found!"));
    }

    private Reservation mapToReservation(ReservationRequest request){
        Long id=idGeneratorService.generateNextSequenceId("reservation");
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setStudent(studentService.getStudentById(request.studentId()));
        reservation.setPlayGround(playGroundService.getPlayGroundById(request.playGroundId()));
        reservation.setStartDate(request.startDate());
        reservation.setApproved(false);
        reservation.setEndDate(request.endDate());
        return reservation;
    }

    private ReservationResponse mapToResponse(Reservation reservation){
        return new ReservationResponse(
                reservation.getId(),
                reservation.getUpdatedDate(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.isApproved(),
                playGroundService.getPlayGroundResponseById(reservation.getPlayGround().getId()),
                studentService.getOneStudentById(reservation.getStudent().getId())

        );
    }

    private List<ReservationResponse> mapToResponseList(List<Reservation> reservationList) {
        return reservationList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
