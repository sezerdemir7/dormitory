package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Cafeteria;

import java.util.List;
import java.util.Optional;

public interface CafeteriaRepository extends BaseRepository<Cafeteria,Long> {

    Optional<Cafeteria> findByName(String cafeteriaName);



}
