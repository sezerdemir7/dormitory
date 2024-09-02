package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Staff;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends BaseRepository<Staff,Long>{
    Optional<Staff> findByName(String staffName);

    Optional<Staff> findByUsername(String username);

    boolean existsByUsername(String username);
}
