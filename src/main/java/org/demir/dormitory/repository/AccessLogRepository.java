package org.demir.dormitory.repository;

import org.demir.dormitory.entity.enumType.AccessAction;
import org.demir.dormitory.entity.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessLogRepository extends JpaRepository<AccessLog,Long> {

    List<AccessLog> findTop10ByActionOrderByIdDesc(AccessAction action);


}
