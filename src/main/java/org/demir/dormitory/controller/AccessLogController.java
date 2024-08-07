package org.demir.dormitory.controller;

import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.response.AccessLogResponse;
import org.demir.dormitory.service.AccessControlService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/access-log")
public class AccessLogController {

    private final AccessControlService accessControlService;

    public AccessLogController(AccessControlService accessControlService) {
        this.accessControlService = accessControlService;
    }

    @PostMapping("/check-in")
    public ApiResponse<String> checkIn(@RequestParam Long studentId) {
        String result = accessControlService.checkIn(studentId);
        return new ApiResponse<>("Check-in successfully.",result, HttpStatus.OK);
    }

    @PostMapping("/check-out")
    public ApiResponse<String> checkOut(@RequestParam Long studentId) {
        String result = accessControlService.checkOut(studentId);
        return new ApiResponse<>("Check-out successfully.",result, HttpStatus.OK);
    }

    @GetMapping("/top10/check-in")
    public ApiResponse<List<AccessLogResponse>> top10CheckIn() {
        List<AccessLogResponse> response=accessControlService.getTop10CheckIn();
        return   new ApiResponse<>("Top 10 CHECK-In.",response, HttpStatus.OK);
    }

    @GetMapping("/top10/check-out")
    public ApiResponse<List<AccessLogResponse>> top10Checkout() {
        List<AccessLogResponse> response=accessControlService.getTop10CheckOut();
        return   new ApiResponse<>("Top 10 CHECK-Out.",response, HttpStatus.OK);
    }
}
