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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    private StudentRepository studentRepository;
    private ContactInfoService contactInfoService;
    private RoomService roomService;
    private ImageService imageService;
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        studentRepository = Mockito.mock(StudentRepository.class);
        contactInfoService = Mockito.mock(ContactInfoService.class);
        roomService = Mockito.mock(RoomService.class);
        imageService = Mockito.mock(ImageService.class);
        studentService = new StudentServiceImpl(studentRepository, contactInfoService, roomService, imageService);
    }

    @Test
    void getAllStudent() {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John");
        student1.setSurname("Doe");
        student1.setRoom(new Room());

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane");
        student2.setSurname("Doe");
        student2.setRoom(new Room());

        List<Student> studentList = List.of(student1, student2);

        when(studentRepository.findAllByIsDeletedFalse()).thenReturn(studentList);

        List<StudentResponse> responseList = studentService.getAllStudent();

        assertEquals(2, responseList.size());
        assertEquals(student1.getId(), responseList.get(0).id());
        assertEquals(student2.getId(), responseList.get(1).id());

        verify(studentRepository).findAllByIsDeletedFalse();
    }

    @Test
    void saveStudent() {
        StudentRequest request = new StudentRequest("John", "Doe", 1L);

        Room room = new Room();
        room.setId(1L);
        room.setCapacity(10);
        room.setCurrentOccupancy(5);

        Student student = new Student();
        student.setName(request.name());
        student.setSurname(request.surname());
        student.setRoom(room);

        when(roomService.getRoomWithCapacityCheck(request.roomId())).thenReturn(room);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentResponse response = studentService.saveStudent(request);

        assertEquals(student.getName(), response.name());
        assertEquals(student.getSurname(), response.surname());
        assertEquals(student.getRoom().getId(), response.roomId());

        verify(roomService).getRoomWithCapacityCheck(request.roomId());
        verify(studentRepository).save(student);
    }

    @Test
    void getStudentById() {
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(studentId);

        assertEquals(studentId, result.getId());

        verify(studentRepository).findById(studentId);
    }



    @Test
    void getOneStudentById_NotFound() {
        Long studentId = 1L;

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            studentService.getOneStudentById(studentId);
        });

        assertEquals("Student not found!.", exception.getMessage());
        verify(studentRepository).findById(studentId);
    }

    @Test
    void deleteStudent() {
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        studentService.deleteStudent(studentId);

        assertTrue(student.isDeleted());
        verify(studentRepository).save(student);
    }

    @Test
    void getInfo() {
        Long studentId = 1L;
        Student student = new Student();
        ContactInfo contactInfo = new ContactInfo();
        student.setContactInfo(contactInfo);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        ContactInfo result = studentService.getInfo(studentId);

        assertEquals(contactInfo, result);
        verify(studentRepository).findById(studentId);
    }

    @Test
    void getOneStudentImage() {
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);
        Image image = new Image();
        image.setBase64Data("base64ImageData");
        student.setImage(image);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        ImageResponse response = studentService.getOneStudentImage(studentId);

        assertEquals(studentId, response.id());
        assertEquals(image.getBase64Data(), response.base64Data());

        verify(studentRepository).findById(studentId);
    }

    @Test
    void saveStudentImage() {
        Long studentId = 1L;
        ImageRequest request = new ImageRequest(studentId, "base64ImageData");
        Student student = new Student();
        student.setId(studentId);

        Image image = new Image();
        image.setBase64Data("base64ImageData");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(imageService.saveImage(request.base64Data())).thenReturn(image);
        when(studentRepository.save(student)).thenReturn(student);

        ImageResponse response = studentService.saveStudentImage(request);

        assertEquals(studentId, response.id());
        assertEquals(image.getBase64Data(), response.base64Data());

        verify(studentRepository).findById(studentId);
        verify(imageService).saveImage(request.base64Data());
        verify(studentRepository).save(student);
    }
}
