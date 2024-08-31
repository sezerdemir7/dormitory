package org.demir.dormitory.repository;

import org.demir.dormitory.entity.AccessLog;
import org.demir.dormitory.entity.enumType.AccessAction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccessLogRepository extends MongoRepository<AccessLog, Long> {

    List<AccessLog> findTop10ByActionOrderByIdDesc(AccessAction action);


}
