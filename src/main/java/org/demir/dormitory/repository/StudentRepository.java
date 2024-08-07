package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends BaseRepository<Student,Long> {


}
