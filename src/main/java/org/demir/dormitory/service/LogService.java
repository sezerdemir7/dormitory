package org.demir.dormitory.service;

import org.demir.dormitory.dto.response.LogResponse;

import java.util.List;

public interface LogService {

    void log(String entityName,Long entityId,String action);
    List<LogResponse> getLogsByEntityNameAndId(String entityName, Long entityId);
    List<LogResponse> getAllLog();
}
