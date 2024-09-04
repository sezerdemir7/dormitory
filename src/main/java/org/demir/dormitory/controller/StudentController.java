package org.demir.dormitory.controller;

import jakarta.validation.Valid;
import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.request.StudentRequest;
import org.demir.dormitory.dto.request.StudentUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.dto.response.StudentResponse;
import org.demir.dormitory.entity.ContactInfo;
import org.demir.dormitory.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/oneStudent")
    public ApiResponse<StudentResponse> getStudentById (@RequestParam Long id){
        return new ApiResponse<>( "Student retrieved successfully.", studentService.getOneStudentById(id), FOUND);
    }

    @PostMapping("/save")
    public ApiResponse<StudentResponse> saveStudent(@RequestBody StudentRequest request){
        return new ApiResponse<>("Student saved successfully.", studentService.saveStudent(request), CREATED);
    }

    @GetMapping("/all")
    public ApiResponse<List<StudentResponse>> getAllStudents(){
        return new ApiResponse<>( "Students retrieved successfully.", studentService.getAllStudent(), OK);
    }

    @PutMapping("/update")
    public ApiResponse<StudentResponse> updateStudent(@RequestBody @Valid StudentUpdateRequest request){
        return new ApiResponse<>("Student updated successfully.", studentService.updateStudent(request), OK);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return new ApiResponse<>("Student deleted successfully.", null, OK);
    }

    @GetMapping("/contact-info")
    public ApiResponse<ContactInfo> getContactInfo(@RequestParam Long studentId){
        return new ApiResponse<>("student info",studentService.getInfo(studentId), OK);
    }

    @PostMapping("/save/contact-info")
    public ApiResponse<ContactInfoResponse> saveContactInfo(@RequestParam Long studentId,@RequestBody ContactInfoRequest request){
        return new ApiResponse<>("student info saved",studentService.saveContactInfo(studentId,request), CREATED);
    }

    @PostMapping("/save/image")
    public ApiResponse<ImageResponse> saveImage(@RequestBody ImageRequest request){
        ImageResponse response=studentService.saveStudentImage(request);
        return new ApiResponse<>("Student image uploaded.",response, CREATED);
    }
    @GetMapping("/image")
    public ApiResponse<ImageResponse> getStudentImage(@RequestParam Long studentId){
        return new ApiResponse<>("Student image retrieved successfully.",studentService.getOneStudentImage(studentId), OK);
    }
}
