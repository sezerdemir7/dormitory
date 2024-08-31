package org.demir.dormitory.repository;

import org.demir.dormitory.entity.PlayGround;

import java.util.Optional;

public interface PlayGroundRepository extends BaseRepository<PlayGround, Long> {
    Optional<PlayGround> findByName(String playGroundName);

}
