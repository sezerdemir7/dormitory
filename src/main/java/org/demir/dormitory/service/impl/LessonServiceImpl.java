package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.LessonRequest;
import org.demir.dormitory.dto.request.LessonUpdateRequest;
import org.demir.dormitory.dto.response.LessonResponse;
import org.demir.dormitory.dto.response.TeacherResponse;
import org.demir.dormitory.entity.Lesson;
import org.demir.dormitory.entity.Teacher;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.LessonRepository;
import org.demir.dormitory.service.LessonService;
import org.demir.dormitory.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final TeacherService  teacherService;

    public LessonServiceImpl(LessonRepository lessonRepository, TeacherService teacherService) {
        this.lessonRepository = lessonRepository;
        this.teacherService = teacherService;
    }

    @Override
    public LessonResponse saveLesson(LessonRequest request) {
        Teacher teacher=teacherService.getTeacherById(request.teacherId());
        Lesson toSave = mapToLesson(request);
        toSave.setTeacher(teacher);
        Lesson lesson = lessonRepository.save(toSave);
        return mapToResponse(lesson);
    }

    @Override
    public void deleteLesson(Long lessonId) {
        Lesson lesson = findLessonById(lessonId);
        lessonRepository.delete(lesson);
    }

    @Override
    public LessonResponse getLessonByName(String lessonName) {
        Lesson lesson = lessonRepository.findByName(lessonName).orElseThrow(() ->
                new NotFoundException("Lesson not found!"));
        return mapToResponse(lesson);
    }

    @Override
    public List<LessonResponse> getAllLessons() {
        List<Lesson> lessonList = lessonRepository.findAll();
        return mapToResponseList(lessonList);
    }

    @Override
    public LessonResponse updateLesson(LessonUpdateRequest request) {
        Lesson toUpdate = findLessonById(request.id());
        toUpdate.setName(request.name());
        toUpdate.setDescription(request.description());
        Lesson lesson = lessonRepository.save(toUpdate);
        return mapToResponse(lesson);
    }

    @Override
    public Lesson getLessonById(Long lessonId) {
        return findLessonById(lessonId);
    }

    @Override
    public LessonResponse getOneLessonById(Long lessonId) {
        Lesson lesson=findLessonById(lessonId);
        return mapToResponse(lesson);
    }

    private Lesson findLessonById(Long lessonId) {
        return lessonRepository.findById(lessonId).orElseThrow(() ->
                new NotFoundException("Lesson not found!"));
    }

    private Lesson mapToLesson(LessonRequest request) {
        Lesson lesson = new Lesson();
        lesson.setName(request.name());
        lesson.setDescription(request.description());
        return lesson;
    }

    private LessonResponse mapToResponse(Lesson lesson) {
        return new LessonResponse(
                lesson.getId(),
                lesson.getName(),
                lesson.getDescription(),
                lesson.getCurrentStudentCount(),
                lesson.getMaxStudentCount(),
                new TeacherResponse(
                        lesson.getTeacher().getId(),
                        lesson.getTeacher().getName(),
                        lesson.getTeacher().getSurname())
        );
    }

    private List<LessonResponse> mapToResponseList(List<Lesson> lessonList) {
        return lessonList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
