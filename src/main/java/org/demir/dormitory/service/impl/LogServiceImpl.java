package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.response.LogResponse;
import org.demir.dormitory.entity.Log;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.LogRepository;
import org.demir.dormitory.service.LogService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {


    @Lazy
    private final LogRepository logRepository;

    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void log(String entityName,Long entityId,String action){
        Log log=new Log();
        log.setEntityName(entityName);
        log.setEntityId(entityId);
        log.setAction(action);

        logRepository.save(log);
    }

    @Override
    public List<LogResponse> getLogsByEntityNameAndId(String entityName, Long entityId) {
        List<Log> logs=logRepository.findByEntityNameAndEntityId(entityName, entityId).orElseThrow(()->
                new NotFoundException("No log found for entityName="+entityName+" and entityId="+entityId));
        return mapToResponseList(logs);
    }

    @Override
    public List<LogResponse> getAllLog() {
        List<Log> logList=logRepository.findAll();
        return mapToResponseList(logList);
    }


    private LogResponse mapToResponse(Log log){
        return new LogResponse(
                log.getId(),
                log.getCreatedDate(),
                log.getEntityName(),
                log.getEntityId(),
                log.getAction());
    }

    private List<LogResponse> mapToResponseList(List<Log> logList){
        return logList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
