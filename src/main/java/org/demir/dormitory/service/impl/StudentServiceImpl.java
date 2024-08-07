package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.request.StudentRequest;
import org.demir.dormitory.dto.request.StudentUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.dto.response.StudentResponse;
import org.demir.dormitory.entity.ContactInfo;
import org.demir.dormitory.entity.Image;
import org.demir.dormitory.entity.Room;
import org.demir.dormitory.entity.Student;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.StudentRepository;
import org.demir.dormitory.service.ContactInfoService;
import org.demir.dormitory.service.ImageService;
import org.demir.dormitory.service.RoomService;
import org.demir.dormitory.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ContactInfoService contactInfoService;
    private final RoomService roomService;
    private final ImageService imageService;

    public StudentServiceImpl(StudentRepository studentRepository, ContactInfoService contactInfoService, RoomService roomService, ImageService imageService) {
        this.studentRepository = studentRepository;
        this.contactInfoService = contactInfoService;
        this.roomService = roomService;
        this.imageService = imageService;
    }


    @Override
    public List<StudentResponse> getAllStudent() {
        List<Student> studentList = studentRepository.findAllByIsDeletedFalse();
        return mapToResponsList(studentList);
    }

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        Student toSave = mapToStudent(studentRequest);
        Room room = (Room) roomService.getRoomWithCapacityCheck(studentRequest.roomId());
        toSave.setRoom(room);
        room.setCurrentOccupancy(room.getCurrentOccupancy() + 1);
        roomService.saveRoom(room);
        Student student = studentRepository.save(toSave);
        return mapToResponse(student);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return findStudentById(studentId);
    }

    @Override
    public StudentResponse getOneStudentById(Long studentId) {
        Student student = findStudentById(studentId);
        return mapToResponse(student);
    }

    @Override
    public ContactInfoResponse saveContactInfo(Long studentId, ContactInfoRequest contactInfoRequest) {
        Student student = findStudentById(studentId);
        ContactInfo contactInfo = contactInfoService.saveContactInfo(contactInfoRequest);
        student.setContactInfo(contactInfo);
        studentRepository.save(student);
        return contactInfoService.mapToResponse(contactInfo);
    }

    @Override
    public StudentResponse updateStudent(StudentUpdateRequest request) {
        Student toUpdate = findStudentById(request.id());
        toUpdate.setName(request.name());
        toUpdate.setSurname(request.surname());
        Student student = studentRepository.save(toUpdate);
        return mapToResponse(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = findStudentById(id);
        student.setDeleted(true);
        studentRepository.save(student);
    }

    @Override
    public ContactInfo getInfo(Long studentId) {
        Student student = findStudentById(studentId);
        return student.getContactInfo();
    }

    @Override
    public ImageResponse getOneStudentImage(Long studentId) {
        Student student = findStudentById(studentId);
        return mapToImageResponse(student,student.getImage());
    }

    @Override
    public ImageResponse saveStudentImage(ImageRequest request) {
        Student toSave = findStudentById(request.entityId());
        Image image = imageService.saveImage(request.base64Data());
        toSave.setImage(image);
        Student student = studentRepository.save(toSave);
        return mapToImageResponse(student, image);

    }

    private ImageResponse mapToImageResponse(Student student, Image image) {
        return new ImageResponse(
                student.getId(),
                student.getName(),
                image.getId(),
                image.getBase64Data());
    }
    private Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() ->
                new NotFoundException("Student not found!."));
    }

    private Student mapToStudent(StudentRequest request) {
        Student student = new Student();
        student.setName(request.name());
        student.setSurname(request.surname());
        return student;
    }

    private StudentResponse mapToResponse(Student student) {
        StudentResponse response =
                new StudentResponse(
                        student.getId(),
                        student.getName(),
                        student.getSurname(),
                        student.getRoom().getId());
        return response;
    }


    private List<StudentResponse> mapToResponsList(List<Student> studentList) {
        List<StudentResponse> responseList = studentList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return responseList;
    }


}

