package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.response.PlayGroundResponse;
import org.demir.dormitory.entity.PlayGround;
import org.demir.dormitory.entity.Reservation;
import org.demir.dormitory.service.PlayGroundService;
import org.demir.dormitory.service.ReservationSchedulerService;
import org.demir.dormitory.service.ReservationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@EnableScheduling
public class ReservationSchedulerServiceImpl implements ReservationSchedulerService {

    private final PlayGroundService playGroundService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ReservationService reservationService;

    public ReservationSchedulerServiceImpl(PlayGroundService playGroundService, SimpMessagingTemplate messagingTemplate, ReservationService reservationService) {

        this.playGroundService = playGroundService;
        this.messagingTemplate = messagingTemplate;
        this.reservationService = reservationService;
    }

    @Scheduled(fixedRate = 600000)
    public void checkReservations() {

        List<Reservation> activeReservations = reservationService.findByEndDateBeforeAndStatusTrue();
        System.out.println("**************");
        for (Reservation reservation : activeReservations) {
            PlayGround playGround = reservation.getPlayGround();
            if (!playGround.isAvailable()) {
                playGround.setAvailable(true);
                reservation.setStatus(false);
                reservationService.save(reservation);
                PlayGroundResponse playGroundResponse=playGroundService.savePlayGround(playGround);
                messagingTemplate.convertAndSend("/topic/playground-status", playGroundResponse);
                System.out.println("Message sent for playground: " + playGround.getId());
            }
        }
    }
}
