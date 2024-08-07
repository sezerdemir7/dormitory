package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends BaseRepository<Reservation,Long> {

    List<Reservation> findReservationByIsApprovedFalse();

    List<Reservation> findReservationByIsApprovedTrue();

    Reservation findTop1ByPlayGroundIdOrderByIdDesc(Long playGroundId);


    List<Reservation> findByEndDateBeforeAndStatusTrue(LocalDateTime now);
}
