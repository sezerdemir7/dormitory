package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.ReservationRequest;
import org.demir.dormitory.dto.request.ReservationUpdateRequest;
import org.demir.dormitory.dto.response.ReservationResponse;
import org.demir.dormitory.entity.PlayGround;
import org.demir.dormitory.entity.Reservation;
import org.demir.dormitory.entity.Student;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.ReservationRepository;
import org.demir.dormitory.service.PlayGroundService;
import org.demir.dormitory.service.RabbitMQProducer;
import org.demir.dormitory.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    private ReservationRepository reservationRepository;
    private ReservationServiceImpl reservationService;
    private StudentService studentService;
    private PlayGroundService playGroundService;
    private RabbitMQProducer rabbitMQProducer;
    private SimpMessagingTemplate messagingTemplate;

    @BeforeEach
    void setUp() {
        reservationRepository = Mockito.mock(ReservationRepository.class);
        reservationService = new ReservationServiceImpl(reservationRepository,null,null,null,null);
    }




    @Test
    void deleteReservation() {
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        reservationService.deleteReservation(reservationId);

        assertTrue(reservation.isDeleted());
        verify(reservationRepository).save(reservation);
    }

    @Test
    void getReservationById_Success() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);

        when(reservationRepository.findById(reservation.getId())).thenReturn(Optional.of(reservation));

        Reservation result = reservationService.getReservationById(reservation.getId());

        assertEquals(reservation.getId(), result.getId());

        verify(reservationRepository).findById(reservation.getId());
    }

    @Test
    void getReservationById_NotFound() {
        Long id = 1L;

        when(reservationRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            reservationService.getReservationById(id);
        });

        assertEquals("Reservation not found!", exception.getMessage());
        verify(reservationRepository).findById(id);
    }





}
