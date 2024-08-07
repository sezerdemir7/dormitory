package org.demir.dormitory.controller;

import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.request.ManagerRequest;
import org.demir.dormitory.dto.request.ManagerUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.dto.response.ManagerResponse;
import org.demir.dormitory.service.ManagerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("v1/manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/oneManager")
    public ApiResponse<ManagerResponse> getManagerById(@RequestParam Long id) {
        return new ApiResponse<>("Manager retrieved successfully.", managerService.getOneManagerById(id), FOUND);
    }

    @PostMapping("/save")
    public ApiResponse<ManagerResponse> saveManager(@RequestBody ManagerRequest request) {
        return new ApiResponse<>("Manager saved successfully.", managerService.saveManager(request), CREATED);
    }

    @GetMapping("/all")
    public ApiResponse<List<ManagerResponse>> getAllManagers() {
        return new ApiResponse<>("Managers retrieved successfully.", managerService.getAllManagers(), OK);
    }

    @PutMapping("/update")
    public ApiResponse<ManagerResponse> updateManager(@RequestBody ManagerUpdateRequest request) {
        return new ApiResponse<>("Manager updated successfully.", managerService.updateManager(request), OK);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
        return new ApiResponse<>("Manager deleted successfully.", null, OK);
    }
    @PostMapping("/save/contact-info")
    public ApiResponse<ContactInfoResponse> saveContactInfo(@RequestParam Long managerId, @RequestBody ContactInfoRequest request){
        return new ApiResponse<>("student info saved",managerService.saveContactInfo(managerId,request), CREATED);
    }

    @PostMapping("/save/image")
    public ApiResponse<ImageResponse> saveImage(@RequestBody ImageRequest request){
        ImageResponse response=managerService.saveManagerImage(request);
        return new ApiResponse<>("Manager image uploaded.",response, CREATED);
    }
    @GetMapping("/image")
    public ApiResponse<ImageResponse> getManagerImage(@RequestParam Long studentId){
        return new ApiResponse<>("Manager image retrieved successfully.",managerService.getOneManagerImage(studentId), OK);
    }
}
