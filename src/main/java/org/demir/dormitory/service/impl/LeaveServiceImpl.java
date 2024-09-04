package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.LeaveRequest;
import org.demir.dormitory.dto.request.LeaveUpdateRequest;
import org.demir.dormitory.dto.response.LeaveResponse;
import org.demir.dormitory.dto.response.StudentResponse;
import org.demir.dormitory.entity.Leave;
import org.demir.dormitory.entity.Student;
import org.demir.dormitory.exception.BadRequestException;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.LeaveRepository;
import org.demir.dormitory.service.LeaveService;
import org.demir.dormitory.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final StudentService studentService;


    public LeaveServiceImpl(LeaveRepository leaveRepository, StudentService studentService) {
        this.leaveRepository = leaveRepository;
        this.studentService = studentService;
    }

    @Override
    public LeaveResponse saveLeave(LeaveRequest request) {
        if(leaveControl(request.studentId())){
            throw  new BadRequestException("Leave already exists");
        }
        Leave toSave = mapToLeave(request);
        Leave leave = leaveRepository.save(toSave);
        return mapToResponse(leave);
    }
    private boolean leaveControl(Long studentId){
       return leaveRepository.existsByHasEndedFalseAndStudentId(studentId);
    }

    @Override
    public void deleteLeave(Long leaveId) {
        Leave leave = findLeaveById(leaveId);
        leave.setDeleted(true);
        leaveRepository.save(leave);
    }


    @Override
    public List<LeaveResponse> getAllLeave() {
        List<Leave> leaveList = leaveRepository.findAllByIsDeletedFalseAndHasEndedFalse();
        return mapToResponseList(leaveList);
    }

    @Override
    public LeaveResponse updateLeave(LeaveUpdateRequest request) {
        Leave toUpdate = findLeaveById(request.id());
        toUpdate.setEndDate(request.endDate());
        Leave leave = leaveRepository.save(toUpdate);
        return mapToResponse(leave);
    }

    @Override
    public Leave getLeaveById(Long leaveId) {
        return findLeaveById(leaveId);
    }

    @Override
    public LeaveResponse getOneLeaveById(Long leaveId) {
        Leave leave = findLeaveById(leaveId);
        return mapToResponse(leave);
    }

    @Override
    public Leave getLeaveByStudentId(Long studentId) {
        Leave leave=leaveRepository.findByHasEndedFalseAndStudentId(studentId);
        return leave;
    }

    @Override
    public LeaveResponse approveLeave(Long id) {
        Leave toUpdate = findLeaveById(id);
        toUpdate.setApproved(true);
        Leave leave = leaveRepository.save(toUpdate);
        return mapToResponse(leave);
    }

    @Override
    public List<LeaveResponse> getLeavesWhereApprovedTrue() {
        List<Leave> leaveList=leaveRepository.findByIsApprovedTrueAndHasEndedFalse();
        return mapToResponseList(leaveList);
    }

    @Override
    public List<LeaveResponse> getLeavesWhereApprovedFalse() {
        List<Leave> leaveList=leaveRepository.findByIsApprovedFalseAndHasEndedFalse();
        return mapToResponseList(leaveList);
    }

    private Leave findLeaveById(Long leaveId) {
        return leaveRepository.findByIdAndIsDeletedFalse(leaveId)
                .orElseThrow(() -> new NotFoundException("Leave not found!"));
    }

    private Leave mapToLeave(LeaveRequest request) {
        Leave leave = new Leave();
        leave.setStartDate(request.startDate());
        leave.setEndDate(request.endDate());
        leave.setStudent(findStudentById(request.studentId()));
        return leave;
    }

    private LeaveResponse mapToResponse(Leave leave) {
        return new LeaveResponse(
                leave.getId(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.isApproved(),
                mapStudentToResponse(leave.getStudent())
        );
    }

    private List<LeaveResponse> mapToResponseList(List<Leave> leaveList) {
        return leaveList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private Student findStudentById(Long studentId) {
        return studentService.getStudentById(studentId);
    }

    private StudentResponse mapStudentToResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getRoom().getId()
        );
    }
}
