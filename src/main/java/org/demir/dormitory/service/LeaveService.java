package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.LeaveRequest;
import org.demir.dormitory.dto.request.LeaveUpdateRequest;
import org.demir.dormitory.dto.response.LeaveResponse;
import org.demir.dormitory.entity.Leave;

import java.util.List;

public interface LeaveService {

    LeaveResponse saveLeave(LeaveRequest saveLeaveRequest);

    void deleteLeave(Long leaveId);

    List<LeaveResponse> getAllLeave();

    LeaveResponse updateLeave(LeaveUpdateRequest leaveUpdateRequest);

    Leave getLeaveById(Long leaveId);
    LeaveResponse getOneLeaveById(Long leaveId);

    Leave getLeaveByStudentId(Long studentId);

    LeaveResponse approveLeave(Long id);

    List<LeaveResponse> getLeavesWhereApprovedTrue();

    List<LeaveResponse> getLeavesWhereApprovedFalse();
}
