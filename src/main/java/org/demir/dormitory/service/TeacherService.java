package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.request.TeacherRequest;
import org.demir.dormitory.dto.request.TeacherUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.dto.response.TeacherResponse;
import org.demir.dormitory.entity.Teacher;

import java.util.List;

public interface TeacherService {

    TeacherResponse saveTeacher(TeacherRequest saveTeacherRequest);

    void deleteTeacher(Long teacherId);

    TeacherResponse getTeacherByName(String teacherName);

    List<TeacherResponse> getAllTeacher();

    TeacherResponse updateTeacher(TeacherUpdateRequest teacherUpdateRequest);

    Teacher getTeacherById(Long teacherId);

    TeacherResponse getOneTeacherById(Long teacherId);

    ContactInfoResponse saveContactInfo(Long teacherId, ContactInfoRequest contactInfoRequest);

    ImageResponse saveTeacherImage(ImageRequest request);

    ImageResponse getOneTeacherImage(Long studentId);
}
