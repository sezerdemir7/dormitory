package org.demir.dormitory.controller;

import jakarta.validation.Valid;
import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.LeaveRequest;
import org.demir.dormitory.dto.request.LeaveUpdateRequest;
import org.demir.dormitory.dto.response.LeaveResponse;
import org.demir.dormitory.dto.response.ReservationResponse;
import org.demir.dormitory.service.LeaveService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("v1/leave")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/oneLeave")
    public ApiResponse<LeaveResponse> getLeaveById(@RequestParam Long id) {
        return new ApiResponse<>("Leave retrieved successfully.", leaveService.getOneLeaveById(id), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ApiResponse<LeaveResponse> saveLeave(@RequestBody @Valid LeaveRequest request) {
        return new ApiResponse<>("Leave saved successfully.", leaveService.saveLeave(request), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ApiResponse<List<LeaveResponse>> getAllLeaves() {
        return new ApiResponse<>("Leaves retrieved successfully.", leaveService.getAllLeave(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ApiResponse<LeaveResponse> updateLeave(@RequestBody @Valid LeaveUpdateRequest request) {
        return new ApiResponse<>("Leave updated successfully.", leaveService.updateLeave(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteLeave(@RequestParam Long id) {
        leaveService.deleteLeave(id);
        return new ApiResponse<>("Leave deleted successfully.", null, HttpStatus.OK);
    }
    @PutMapping("/approve/{id}")
    public ApiResponse<LeaveResponse> approveReservation(@PathVariable Long id) {
        LeaveResponse response = leaveService.approveLeave(id);
        return new ApiResponse<>("Leave approved successfully.", response, HttpStatus.OK);
    }
    @GetMapping("/approved")
    public ApiResponse<List<LeaveResponse>> getApprovedReservations() {
        List<LeaveResponse> responseList = leaveService.getLeavesWhereApprovedTrue();
        return new ApiResponse<>("Approved reservations retrieved successfully.", responseList, HttpStatus.OK);
    }

    @GetMapping("/unapproved")
    public ApiResponse<List<LeaveResponse>> getUnapprovedReservations() {
        List<LeaveResponse> responseList = leaveService.getLeavesWhereApprovedFalse();
        return new ApiResponse<>("Unapproved reservations retrieved successfully.", responseList, HttpStatus.OK);
    }
}
