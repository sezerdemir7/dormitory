package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Log;

import java.util.List;
import java.util.Optional;

public interface LogRepository extends BaseRepository<Log, Long> {
    Optional<List<Log>> findByEntityNameAndEntityId(String entityName, Long entityId);

}
