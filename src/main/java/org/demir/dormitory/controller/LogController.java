package org.demir.dormitory.controller;

import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.response.LogResponse;
import org.demir.dormitory.entity.Log;
import org.demir.dormitory.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/log")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/oneLog")
    public ApiResponse<List<LogResponse>> getLogs(@RequestParam String entityName, @RequestParam Long entityId){
        List<LogResponse> logs = logService.getLogsByEntityNameAndId(entityName, entityId);
        return new ApiResponse<>("Logs retrieved successfully.", logs, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ApiResponse<List<LogResponse>> getAllLogs(){
        return new ApiResponse<>("Logs retrieved successfully.",logService.getAllLog(),HttpStatus.OK);
    }

}
