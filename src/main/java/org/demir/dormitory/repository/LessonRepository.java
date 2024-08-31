package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends BaseRepository<Lesson,Long> {
    Optional<Lesson> findByName(String lessonName);
    List<Lesson> findByNameContainingIgnoreCase(String name);
}
