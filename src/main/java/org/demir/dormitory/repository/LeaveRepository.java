package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Leave;

import java.util.List;

public interface LeaveRepository extends BaseRepository<Leave,Long> {
    List<Leave> findByIsApprovedFalseAndHasEndedFalse();
    List<Leave> findByIsApprovedTrueAndHasEndedFalse();
    List<Leave> findByHasEndedFalse();
    Leave findTopByHasEndedFalseAndStudentIdOrderByIdDesc(Long studentId);
    Leave findByHasEndedFalseAndStudentId(Long studentId);
    List<Leave> findByHasEndedTrueAndStudentId(Long studentId);
    boolean existsByHasEndedFalseAndStudentId(Long studentId);
    List<Leave> findAllByIsDeletedFalseAndHasEndedFalse();

}
