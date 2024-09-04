package org.demir.dormitory.controller;

import jakarta.validation.Valid;
import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.ReservationRequest;
import org.demir.dormitory.dto.request.ReservationUpdateRequest;
import org.demir.dormitory.dto.response.ReservationResponse;
import org.demir.dormitory.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/oneReservation")
    public ApiResponse<ReservationResponse> getReservationById(@RequestParam Long id) {
        return new ApiResponse<>("Reservation retrieved successfully.", reservationService.getOneReservationById(id), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ApiResponse<ReservationResponse> saveReservation(@RequestBody ReservationRequest request) {
        return new ApiResponse<>("Reservation saved successfully.", reservationService.saveReservation(request), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ApiResponse<List<ReservationResponse>> getAllReservations() {
        return new ApiResponse<>("Reservations retrieved successfully.", reservationService.getAllReservation(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ApiResponse<ReservationResponse> updateReservation(@RequestBody @Valid ReservationUpdateRequest request) {
        return new ApiResponse<>("Reservation updated successfully.", reservationService.updateReservation(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteReservation(@RequestParam Long id) {
        reservationService.deleteReservation(id);
        return new ApiResponse<>("Reservation deleted successfully.", null, HttpStatus.OK);
    }

    @PutMapping("/approve/{id}")
    public ApiResponse<ReservationResponse> approveReservation(@PathVariable Long id) {
        ReservationResponse response = reservationService.approveReservation(id);
        return new ApiResponse<>("Reservation approved successfully.", response, HttpStatus.OK);
    }

    @GetMapping("/approved")
    public ApiResponse<List<ReservationResponse>> getApprovedReservations() {
        List<ReservationResponse> responseList = reservationService.getReservationsWhereApprovedTrue();
        return new ApiResponse<>("Approved reservations retrieved successfully.", responseList, HttpStatus.OK);
    }

    @GetMapping("/unapproved")
    public ApiResponse<List<ReservationResponse>> getUnapprovedReservations() {
        List<ReservationResponse> responseList = reservationService.getReservationsWhereApprovedFalse();
        return new ApiResponse<>("Unapproved reservations retrieved successfully.", responseList, HttpStatus.OK);
    }
}
