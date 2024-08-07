package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Cafeteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CafeteriaRepository extends JpaRepository<Cafeteria,Long> {

    Optional<Cafeteria> findByName(String cafeteriaName);

}
