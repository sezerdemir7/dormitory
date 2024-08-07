package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Optional<Teacher> findByName(String teacherName);
}
