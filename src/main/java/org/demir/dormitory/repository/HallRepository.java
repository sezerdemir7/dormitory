package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HallRepository extends JpaRepository<Hall,Long> {
    Optional<Hall> findByName(String hallName);
}
