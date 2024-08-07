package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Garden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GardenRepository extends BaseRepository<Garden,Long> {
   Optional<Garden> findByName(String gardenName);
}
