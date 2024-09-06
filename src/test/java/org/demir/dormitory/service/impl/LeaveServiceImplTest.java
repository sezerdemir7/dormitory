package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.LeaveRequest;
import org.demir.dormitory.dto.request.LeaveUpdateRequest;
import org.demir.dormitory.dto.response.LeaveResponse;
import org.demir.dormitory.entity.Leave;
import org.demir.dormitory.entity.Student;
import org.demir.dormitory.exception.BadRequestException;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.LeaveRepository;
import org.demir.dormitory.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LeaveServiceImplTest {

    private LeaveRepository leaveRepository;
    private StudentService studentService;
    private LeaveServiceImpl leaveService;

    @BeforeEach
    void setUp() {
        leaveRepository= Mockito.mock(LeaveRepository.class);
        studentService= Mockito.mock(StudentServiceImpl.class);
        leaveService= new LeaveServiceImpl(leaveRepository,studentService);
    }


    @Test
    void saveLeave_ShouldThrowBadRequestException_WhenLeaveAlreadyExists() {
        LeaveRequest leaveRequest = new LeaveRequest( LocalDateTime.now(), LocalDateTime.now().plusDays(5),1L);

        when(leaveRepository.existsByHasEndedFalseAndStudentId(leaveRequest.studentId())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> leaveService.saveLeave(leaveRequest));
    }

    @Test
    void deleteLeave_ShouldMarkLeaveAsDeleted() {
        Leave leave = new Leave();
        leave.setId(1L);
        leave.setDeleted(false);

        when(leaveRepository.findByIdAndIsDeletedFalse(leave.getId())).thenReturn(Optional.of(leave));

        leaveService.deleteLeave(leave.getId());

        assertTrue(leave.isDeleted());
        verify(leaveRepository).save(leave);
    }
    @Test
    void deleteLeave_ShouldThrowNotFoundException_WhenLeaveNotFound() {
        when(leaveRepository.findByIdAndIsDeletedFalse(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> leaveService.deleteLeave(1L));
    }







    @AfterEach
    void tearDown() {}
}