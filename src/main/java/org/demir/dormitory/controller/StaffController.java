package org.demir.dormitory.controller;

import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.request.StaffRequest;
import org.demir.dormitory.dto.request.StaffUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.dto.response.StaffResponse;
import org.demir.dormitory.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/oneStaff")
    public ApiResponse<StaffResponse> getStaffById(@RequestParam Long id) {
        return new ApiResponse<>("Staff retrieved successfully.", staffService.getOneStaffById(id), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ApiResponse<StaffResponse> saveStaff(@RequestBody StaffRequest request) {
        return new ApiResponse<>("Staff saved successfully.", staffService.saveStaff(request), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ApiResponse<List<StaffResponse>> getAllStaff() {
        return new ApiResponse<>("Staff members retrieved successfully.", staffService.getAllStaff(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ApiResponse<StaffResponse> updateStaff(@RequestBody StaffUpdateRequest request) {
        return new ApiResponse<>("Staff updated successfully.", staffService.updateStaff(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return new ApiResponse<>("Staff deleted successfully.", null, HttpStatus.OK);
    }

    @PostMapping("/save/contact-info")
    public ApiResponse<ContactInfoResponse> saveContactInfo(@RequestParam Long staffId, @RequestBody ContactInfoRequest request) {
        return new ApiResponse<>("Contact info saved.", staffService.saveContactInfo(staffId, request), HttpStatus.CREATED);
    }

    @PostMapping("/save/image")
    public ApiResponse<ImageResponse> saveImage(@RequestBody ImageRequest request) {
        ImageResponse response = staffService.saveStaffImage(request);
        return new ApiResponse<>("Staff image uploaded.", response, HttpStatus.CREATED);
    }

    @GetMapping("/image")
    public ApiResponse<ImageResponse> getStaffImage(@RequestParam Long staffId) {
        return new ApiResponse<>("Staff image retrieved successfully.", staffService.getOneStaffImage(staffId), HttpStatus.OK);
    }

}
