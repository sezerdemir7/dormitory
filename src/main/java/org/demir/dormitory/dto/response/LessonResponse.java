package org.demir.dormitory.dto.response;


public record LessonResponse(Long id, String name, String description, int currentStudentCount,
                             int maxStudentCount,TeacherResponse teacherResponse) {
}