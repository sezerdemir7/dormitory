package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.LessonRequest;
import org.demir.dormitory.dto.request.LessonUpdateRequest;
import org.demir.dormitory.dto.response.LessonResponse;
import org.demir.dormitory.entity.Lesson;
import org.demir.dormitory.entity.Teacher;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.LessonRepository;
import org.demir.dormitory.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LessonServiceImplTest {

    private LessonRepository lessonRepository;
    private TeacherService teacherService;
    private LessonServiceImpl lessonService;

    @BeforeEach
    void setUp() {
        lessonRepository = Mockito.mock(LessonRepository.class);
        teacherService = Mockito.mock(TeacherService.class);
        lessonService = new LessonServiceImpl(lessonRepository, teacherService);
    }

    @Test
    void saveLesson() {
        LessonRequest request = new LessonRequest("Math", "Basic math class", 0, 30, 1L);
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        Lesson toSave = new Lesson();
        toSave.setName(request.name());
        toSave.setDescription(request.description());
        toSave.setCurrentStudentCount(request.currentStudentCount());
        toSave.setMaxStudentCount(request.maxStudentCount());
        toSave.setTeacher(teacher);

        Lesson savedLesson = new Lesson();
        savedLesson.setId(1L);
        savedLesson.setName(toSave.getName());
        savedLesson.setDescription(toSave.getDescription());
        savedLesson.setCurrentStudentCount(toSave.getCurrentStudentCount());
        savedLesson.setMaxStudentCount(toSave.getMaxStudentCount());
        savedLesson.setTeacher(toSave.getTeacher());

        when(teacherService.getTeacherById(request.teacherId())).thenReturn(teacher);
        when(lessonRepository.save(toSave)).thenReturn(savedLesson);

        LessonResponse response = lessonService.saveLesson(request);

        assertEquals(savedLesson.getId(), response.id());
        assertEquals(savedLesson.getName(), response.name());
        assertEquals(savedLesson.getDescription(), response.description());
        assertEquals(savedLesson.getCurrentStudentCount(), response.currentStudentCount());
        assertEquals(savedLesson.getMaxStudentCount(), response.maxStudentCount());

        verify(teacherService).getTeacherById(request.teacherId());
        verify(lessonRepository).save(toSave);
    }

    @Test
    void deleteLesson() {
        Long lessonId = 1L;
        Lesson lesson = new Lesson();
        lesson.setId(lessonId);

        when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));

        lessonService.deleteLesson(lessonId);

        assertTrue(lesson.isDeleted());
        verify(lessonRepository).save(lesson);
    }

    @Test
    void getLessonByName_Success() {
        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setName("Math");

        when(lessonRepository.findByName(lesson.getName())).thenReturn(Optional.of(lesson));

        LessonResponse response = lessonService.getLessonByName("Math");

        assertEquals(lesson.getId(), response.id());
        assertEquals(lesson.getName(), response.name());

        verify(lessonRepository).findByName(lesson.getName());
    }

    @Test
    void getLessonByName_NotFound() {
        String name = "NonExistentLesson";

        when(lessonRepository.findByName(name)).thenReturn(Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            lessonService.getLessonByName(name);
        });
        assertEquals("Lesson not found!", exception.getMessage());
        verify(lessonRepository).findByName(name);
    }

    @Test
    void getAllLessons() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        lesson1.setName("Math");
        Lesson lesson2 = new Lesson();
        lesson2.setId(2L);
        lesson2.setName("Science");
        List<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson1);
        lessonList.add(lesson2);

        when(lessonRepository.findAllByIsDeletedFalse()).thenReturn(lessonList);

        List<LessonResponse> responseList = lessonService.getAllLessons();

        assertEquals(lessonList.size(), responseList.size());
        for (int i = 0; i < lessonList.size(); i++) {
            LessonResponse response = responseList.get(i);
            Lesson lesson = lessonList.get(i);

            assertEquals(lesson.getId(), response.id());
            assertEquals(lesson.getName(), response.name());
            assertEquals(lesson.getDescription(), response.description());
        }

        verify(lessonRepository).findAllByIsDeletedFalse();
    }

    @Test
    void updateLesson() {
        LessonUpdateRequest request = new LessonUpdateRequest(1L, "Updated Name", "Updated Description", 5, 25);

        Lesson lesson = new Lesson();
        lesson.setId(request.id());
        lesson.setName("Old Name");
        lesson.setDescription("Old Description");

        Lesson updatedLesson = new Lesson();
        updatedLesson.setId(lesson.getId());
        updatedLesson.setName(request.name());
        updatedLesson.setDescription(request.description());
        updatedLesson.setCurrentStudentCount(request.currentStudentCount());
        updatedLesson.setMaxStudentCount(request.maxStudentCount());

        when(lessonRepository.findById(request.id())).thenReturn(Optional.of(lesson));
        when(lessonRepository.save(updatedLesson)).thenReturn(updatedLesson);

        LessonResponse response = lessonService.updateLesson(request);

        assertEquals(updatedLesson.getId(), response.id());
        assertEquals(updatedLesson.getName(), response.name());
        assertEquals(updatedLesson.getDescription(), response.description());
        assertEquals(updatedLesson.getCurrentStudentCount(), response.currentStudentCount());
        assertEquals(updatedLesson.getMaxStudentCount(), response.maxStudentCount());

        verify(lessonRepository).findById(request.id());
        verify(lessonRepository).save(updatedLesson);
    }

    @Test
    void getLessonById_Success() {
        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setName("Math");

        when(lessonRepository.findById(lesson.getId())).thenReturn(Optional.of(lesson));

        Lesson result = lessonService.getLessonById(lesson.getId());

        assertEquals(lesson.getId(), result.getId());
        assertEquals(lesson.getName(), result.getName());

        verify(lessonRepository).findById(lesson.getId());
    }

    @Test
    void getLessonById_NotFound() {
        Long id = 1L;

        when(lessonRepository.findById(id)).thenReturn(Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            lessonService.getOneLessonById(id);
        });
        assertEquals("Lesson not found!", exception.getMessage());
        verify(lessonRepository).findById(id);
    }

}
