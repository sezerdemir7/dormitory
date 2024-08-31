package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Manager;

import java.util.Optional;

public interface ManagerRepository extends BaseRepository<Manager, Long> {
    Optional<Manager> findByName(String managerName);
}
