package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends BaseRepository<Manager,Long> {
    Optional<Manager> findByName(String managerName);
}
