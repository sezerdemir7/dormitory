package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.request.TeacherRequest;
import org.demir.dormitory.dto.request.TeacherUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.dto.response.TeacherResponse;
import org.demir.dormitory.entity.ContactInfo;
import org.demir.dormitory.entity.Image;
import org.demir.dormitory.entity.Student;
import org.demir.dormitory.entity.Teacher;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.TeacherRepository;
import org.demir.dormitory.service.ContactInfoService;
import org.demir.dormitory.service.ImageService;
import org.demir.dormitory.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ContactInfoService contactInfoService;
    private final ImageService imageService;

    public TeacherServiceImpl(TeacherRepository teacherRepository, ContactInfoService contactInfoService, ImageService imageService) {
        this.teacherRepository = teacherRepository;
        this.contactInfoService = contactInfoService;
        this.imageService = imageService;
    }

    @Override
    public TeacherResponse saveTeacher(TeacherRequest request) {
        Teacher toSave = mapToTeacher(request);
        Teacher teacher = teacherRepository.save(toSave);
        return mapToResponse(teacher);
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        Teacher teacher = findTeacherById(teacherId);
        teacher.setDeleted(true);
        teacherRepository.save(teacher);
    }

    @Override
    public TeacherResponse getTeacherByName(String teacherName) {
        Teacher teacher = teacherRepository.findByName(teacherName).orElseThrow(() ->
                new NotFoundException("Teacher not found!"));
        return mapToResponse(teacher);
    }

    @Override
    public List<TeacherResponse> getAllTeacher() {
        List<Teacher> teacherList = teacherRepository.findAll();
        return mapToResponseList(teacherList);
    }

    @Override
    public TeacherResponse updateTeacher(TeacherUpdateRequest request) {
        Teacher toUpdate = findTeacherById(request.id());
        toUpdate.setName(request.name());
        toUpdate.setSurname(request.surname());
        Teacher teacher = teacherRepository.save(toUpdate);
        return mapToResponse(teacher);
    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
        return findTeacherById(teacherId);
    }

    @Override
    public TeacherResponse getOneTeacherById(Long teacherId) {
        Teacher teacher=findTeacherById(teacherId);
        return mapToResponse(teacher);
    }

    @Override
    public ContactInfoResponse saveContactInfo(Long teacherId, ContactInfoRequest contactInfoRequest) {
        Teacher teacher=findTeacherById(teacherId);
        ContactInfo contactInfo=contactInfoService.saveContactInfo(contactInfoRequest);
        teacher.setContactInfo(contactInfo);
        teacherRepository.save(teacher);
        return contactInfoService.mapToResponse(contactInfo);
    }
    @Override
    public ImageResponse getOneTeacherImage(Long teacherId) {
        Teacher teacher = findTeacherById(teacherId);
        return mapToImageResponse(teacher,teacher.getImage());
    }

    @Override
    public ImageResponse saveTeacherImage(ImageRequest request) {
        Teacher toSave = findTeacherById(request.entityId());
        Image image = imageService.saveImage(request.base64Data());
        toSave.setImage(image);
        Teacher teacher = teacherRepository.save(toSave);
        return mapToImageResponse(teacher, image);

    }

    private ImageResponse mapToImageResponse(Teacher teacher, Image image) {
        return new ImageResponse(
                teacher.getId(),
                teacher.getName(),
                image.getId(),
                image.getBase64Data());
    }

    private Teacher findTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(() ->
                new NotFoundException("Teacher not found!"));
    }

    private Teacher mapToTeacher(TeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.setName(request.name());
        teacher.setSurname(request.surname());
        return teacher;
    }

    private TeacherResponse mapToResponse(Teacher teacher) {
        return new TeacherResponse(
                teacher.getId(),
                teacher.getName(),
                teacher.getSurname()
        );
    }

    private List<TeacherResponse> mapToResponseList(List<Teacher> teacherList) {
        return teacherList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
