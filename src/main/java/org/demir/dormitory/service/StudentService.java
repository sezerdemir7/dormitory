package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.request.StudentRequest;
import org.demir.dormitory.dto.request.StudentUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.dto.response.StudentResponse;
import org.demir.dormitory.entity.ContactInfo;
import org.demir.dormitory.entity.Student;

import java.util.List;

public interface StudentService {



    List<StudentResponse> getAllStudent();

    StudentResponse saveStudent(StudentRequest studentRequest);

    Student getStudentById(Long studentId);
    StudentResponse getOneStudentById(Long studentId);

    StudentResponse updateStudent(StudentUpdateRequest request);

    void deleteStudent(Long studentId);

    ContactInfo getInfo(Long studentId);

    ContactInfoResponse saveContactInfo(Long studentId,ContactInfoRequest contactInfoRequest);

    ImageResponse saveStudentImage(ImageRequest request);

    ImageResponse getOneStudentImage(Long studentId);
}
