package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.LessonRequest;
import org.demir.dormitory.dto.request.LessonUpdateRequest;
import org.demir.dormitory.dto.response.LessonResponse;
import org.demir.dormitory.entity.Lesson;

import java.util.List;

public interface LessonService {

    LessonResponse saveLesson(LessonRequest saveLessonRequest);

    void deleteLesson(Long lessonId);

    LessonResponse getLessonByName(String lessonName);

    List<LessonResponse> getAllLessons();

    LessonResponse updateLesson(LessonUpdateRequest lessonUpdateRequest);

    Lesson getLessonById(Long lessonId);
    LessonResponse getOneLessonById(Long lessonId);

    List<LessonResponse> getOneLessonByName(String name);
}
