package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Teacher;

import java.util.Optional;

public interface TeacherRepository extends BaseRepository<Teacher, Long> {
    Optional<Teacher> findByName(String teacherName);
}
