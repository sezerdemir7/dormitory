package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.ReservationRequest;
import org.demir.dormitory.dto.request.ReservationUpdateRequest;
import org.demir.dormitory.dto.response.ReservationResponse;
import org.demir.dormitory.entity.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    ReservationResponse saveReservation(ReservationRequest request);

    void deleteReservation(Long reservationId);

    ReservationResponse getOneReservationById(Long reservationId);

    List<ReservationResponse> getAllReservation();

    ReservationResponse updateReservation(ReservationUpdateRequest request);

    Reservation getReservationById(Long reservationId);

    ReservationResponse approveReservation(Long reservationId);

    List<ReservationResponse> getReservationsWhereApprovedFalse();

    List<ReservationResponse> getReservationsWhereApprovedTrue();


    List<Reservation> findByEndDateBeforeAndStatusTrue();

    void save(Reservation reservation);
}
