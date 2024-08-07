package org.demir.dormitory.dto.request;

public record LessonRequest(String name, String description, int currentStudentCount,
                            int maxStudentCount,Long teacherId)  {
}