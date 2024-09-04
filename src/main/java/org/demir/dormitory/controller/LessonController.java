package org.demir.dormitory.controller;

import jakarta.validation.Valid;
import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.LessonRequest;
import org.demir.dormitory.dto.request.LessonUpdateRequest;
import org.demir.dormitory.dto.response.LessonResponse;
import org.demir.dormitory.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/lesson")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/oneLesson")
    public ApiResponse<LessonResponse> getLessonById(@RequestParam Long id) {
        return new ApiResponse<>("Lesson retrieved successfully.", lessonService.getOneLessonById(id), HttpStatus.FOUND);
    }

    @GetMapping("/lesson")
    public ApiResponse<List<LessonResponse>> getLessonByName(@RequestParam String name) {
        return new ApiResponse<>("Lesson retrieved successfully.", lessonService.getOneLessonByName(name), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ApiResponse<LessonResponse> saveLesson(@RequestBody @Valid LessonRequest request) {
        return new ApiResponse<>("Lesson saved successfully.", lessonService.saveLesson(request), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ApiResponse<List<LessonResponse>> getAllLessons() {
        return new ApiResponse<>("Lessons retrieved successfully.", lessonService.getAllLessons(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ApiResponse<LessonResponse> updateLesson(@RequestBody @Valid LessonUpdateRequest request) {
        return new ApiResponse<>("Lesson updated successfully.", lessonService.updateLesson(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteLesson(@RequestParam Long id) {
        lessonService.deleteLesson(id);
        return new ApiResponse<>("Lesson deleted successfully.", null, HttpStatus.OK);
    }
}
