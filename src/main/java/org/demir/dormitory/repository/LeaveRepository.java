package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Leave;

import java.util.List;

public interface LeaveRepository extends BaseRepository<Leave, Long> {
    Leave findTop1ByStudentIdOrderByIdDesc(Long studentId);

    List<Leave> findLeaveByIsApprovedFalse();

    List<Leave> findLeaveByIsApprovedTrue();
}
