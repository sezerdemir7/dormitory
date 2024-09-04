package org.demir.dormitory.controller;

import jakarta.validation.Valid;
import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.request.TeacherRequest;
import org.demir.dormitory.dto.request.TeacherUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.dto.response.TeacherResponse;
import org.demir.dormitory.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/oneTeacher")
    public ApiResponse<TeacherResponse> getTeacherById(@RequestParam Long id) {
        return new ApiResponse<>("Teacher retrieved successfully.", teacherService.getOneTeacherById(id), FOUND);
    }

    @PostMapping("/save")
    public ApiResponse<TeacherResponse> saveTeacher(@RequestBody @Valid TeacherRequest request) {
        return new ApiResponse<>("Teacher saved successfully.", teacherService.saveTeacher(request), CREATED);
    }

    @GetMapping("/all")
    public ApiResponse<List<TeacherResponse>> getAllTeachers() {
        return new ApiResponse<>("Teachers retrieved successfully.", teacherService.getAllTeacher(), OK);
    }

    @PutMapping("/update")
    public ApiResponse<TeacherResponse> updateTeacher(@RequestBody @Valid TeacherUpdateRequest request) {
        return new ApiResponse<>("Teacher updated successfully.", teacherService.updateTeacher(request), OK);
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteTeacher(@RequestParam Long id) {
        teacherService.deleteTeacher(id);
        return new ApiResponse<>("Teacher deleted successfully.", null, OK);
    }

    @PostMapping("/save/contact-info")
    public ApiResponse<ContactInfoResponse> saveContactInfo(@RequestParam Long teacherId, @RequestBody ContactInfoRequest request){
        return new ApiResponse<>("student info saved",teacherService.saveContactInfo(teacherId,request), CREATED);
    }
    @PostMapping("/save/image")
    public ApiResponse<ImageResponse> saveImage(@RequestBody ImageRequest request){
        ImageResponse response=teacherService.saveTeacherImage(request);
        return new ApiResponse<>("Teacher image uploaded.",response, CREATED);
    }
    @GetMapping("/image")
    public ApiResponse<ImageResponse> getTeacherImage(@RequestParam Long studentId){
        return new ApiResponse<>("Teacher image retrieved successfully.",teacherService.getOneTeacherImage(studentId), OK);
    }
}
