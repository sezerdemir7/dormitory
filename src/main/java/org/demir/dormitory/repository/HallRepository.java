package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Hall;

import java.util.Optional;

public interface HallRepository extends BaseRepository<Hall, Long> {
    Optional<Hall> findByName(String hallName);
}
