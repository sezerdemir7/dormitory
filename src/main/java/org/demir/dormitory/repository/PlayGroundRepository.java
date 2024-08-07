package org.demir.dormitory.repository;

import org.demir.dormitory.entity.PlayGround;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayGroundRepository extends JpaRepository<PlayGround,Long> {
    Optional<PlayGround> findByName(String playGroundName);

}
