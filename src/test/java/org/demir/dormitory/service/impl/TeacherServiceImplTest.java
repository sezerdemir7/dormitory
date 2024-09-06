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
import org.demir.dormitory.entity.Teacher;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.TeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class TeacherServiceImplTest {
    private TeacherRepository teacherRepository;
    private TeacherServiceImpl teacherService;
    private ImageServiceImpl imageService;
    private ContactInfoServiceImpl contactInfoService;

    private Teacher teacher;
    private ContactInfo contactInfo;
    private Image image;

    @BeforeEach
    void setUp() {
        teacherRepository= Mockito.mock(TeacherRepository.class);
        imageService= Mockito.mock(ImageServiceImpl.class);
        contactInfoService= Mockito.mock(ContactInfoServiceImpl.class);
        teacherService=new TeacherServiceImpl(teacherRepository,contactInfoService,imageService);

        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("John");
        teacher.setSurname("Doe");

        contactInfo = new ContactInfo();
        contactInfo.setId(1L);

        image = new Image();
        image.setId(1L);
        image.setBase64Data("base64data");
    }
    @Test
    public void testSaveTeacher() {
        TeacherRequest request = new TeacherRequest("John", "Doe");
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        TeacherResponse response = teacherService.saveTeacher(request);

        assertNotNull(response);
        assertEquals(teacher.getId(), response.id());
        assertEquals(teacher.getName(), response.name());
        assertEquals(teacher.getSurname(), response.surname());
        verify(teacherRepository, times(1)).save(any(Teacher.class));
    }

    @Test
    public void testDeleteTeacher() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        teacherService.deleteTeacher(1L);

        verify(teacherRepository, times(1)).save(teacher);
        assertTrue(teacher.isDeleted());
    }

    @Test
    public void testGetTeacherByName() {
        when(teacherRepository.findByName("John")).thenReturn(Optional.of(teacher));

        TeacherResponse response = teacherService.getTeacherByName("John");

        assertNotNull(response);
        assertEquals(teacher.getId(), response.id());
        assertEquals(teacher.getName(), response.name());
        assertEquals(teacher.getSurname(), response.surname());
    }

    @Test
    public void testGetAllTeacher() {
        Teacher teacher2 = new Teacher();
        teacher2.setId(2L);
        teacher2.setName("Jane");
        teacher2.setSurname("Smith");

        List<Teacher> teachers = Arrays.asList(teacher, teacher2);
        when(teacherRepository.findAllByIsDeletedFalse()).thenReturn(teachers);

        List<TeacherResponse> responses = teacherService.getAllTeacher();

        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertEquals(teacher.getId(), responses.get(0).id());
        assertEquals(teacher2.getId(), responses.get(1).id());
    }

    @Test
    public void testUpdateTeacher() {
        TeacherUpdateRequest request = new TeacherUpdateRequest(1L, "John", "Doe");
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        TeacherResponse response = teacherService.updateTeacher(request);

        assertNotNull(response);
        assertEquals(teacher.getId(), response.id());
        assertEquals("John", response.name());
        assertEquals("Doe", response.surname());
    }

    @Test
    public void testGetTeacherById() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        Teacher response = teacherService.getTeacherById(1L);

        assertNotNull(response);
        assertEquals(teacher.getId(), response.getId());
    }

    @Test
    public void testGetOneTeacherById() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        TeacherResponse response = teacherService.getOneTeacherById(1L);

        assertNotNull(response);
        assertEquals(teacher.getId(), response.id());
        assertEquals(teacher.getName(), response.name());
        assertEquals(teacher.getSurname(), response.surname());
    }



    @Test
    public void testGetOneTeacherImage() {
        teacher.setImage(image);
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        ImageResponse response = teacherService.getOneTeacherImage(1L);

        assertNotNull(response);
        assertEquals(teacher.getId(), response.id());
        assertEquals(image.getBase64Data(), response.base64Data());
    }

    @Test
    public void testSaveTeacherImage() {
        ImageRequest request = new ImageRequest(1L, "base64data");
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(imageService.saveImage("base64data")).thenReturn(image);
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        ImageResponse response = teacherService.saveTeacherImage(request);

        assertNotNull(response);
        assertEquals(image.getId(), response.imageId());
        assertEquals(image.getBase64Data(), response.base64Data());
    }

    @Test
    public void testFindTeacherByIdThrowsException() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            teacherService.getTeacherById(1L);
        });

        assertEquals("Teacher not found!", exception.getMessage());
    }

    @AfterEach
    void tearDown() {
    }
}