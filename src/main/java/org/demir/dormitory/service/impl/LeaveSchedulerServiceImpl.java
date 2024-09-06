package org.demir.dormitory.service.impl;

import jakarta.transaction.Transactional;
import org.demir.dormitory.entity.Leave;
import org.demir.dormitory.repository.LeaveRepository;
import org.demir.dormitory.service.LeaveSchedulerService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@EnableScheduling
public class LeaveSchedulerServiceImpl implements LeaveSchedulerService {

    private final LeaveRepository leaveRepository;

    public LeaveSchedulerServiceImpl(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }


    @Scheduled(fixedRate = 43200000)
    @Transactional
    public void checkReservations() {
        List<Leave> leaves = leaveRepository.findByHasEndedFalse();
        LocalDateTime now = LocalDateTime.now();
        for (Leave leave : leaves) {
            if (leave.getEndDate() != null && leave.getEndDate().isBefore(now)) {
                if (!leave.isHasEnded()) {
                    leave.setHasEnded(true);
                    leaveRepository.save(leave);
                }
            }
        }
    }
}
