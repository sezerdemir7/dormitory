package org.demir.dormitory.dto.request;

public record LessonUpdateRequest(Long id, String name, String description, int currentStudentCount,
                                  int maxStudentCount) {
}