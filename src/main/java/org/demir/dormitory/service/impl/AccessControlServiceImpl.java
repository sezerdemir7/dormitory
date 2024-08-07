package org.demir.dormitory.service.impl;

import org.demir.dormitory.common.AccessAction;
import org.demir.dormitory.dto.response.AccessLogResponse;
import org.demir.dormitory.entity.AccessLog;
import org.demir.dormitory.entity.Leave;
import org.demir.dormitory.entity.Student;
import org.demir.dormitory.exception.BadRequestException;
import org.demir.dormitory.repository.AccessLogRepository;
import org.demir.dormitory.service.AccessControlService;
import org.demir.dormitory.service.LeaveService;
import org.demir.dormitory.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccessControlServiceImpl implements AccessControlService {

    private final AccessLogRepository accessLogRepository;
    private final StudentService studentService;
    private final LeaveService leaveService;


    public AccessControlServiceImpl(AccessLogRepository accessLogRepository, StudentService studentService, LeaveService leaveService) {
        this.accessLogRepository = accessLogRepository;
        this.studentService = studentService;
        this.leaveService = leaveService;
    }

    @Override
    public String checkIn(Long studentId) {
        Student student=studentService.getStudentById(studentId);

        Leave leave= leaveService.getLeaveByStudentId(student.getId());

        if(leave==null || leave.getEndDate().isBefore(LocalDateTime.now())){
            AccessLog log=new AccessLog();
            log.setStudent(student);
            log.setAction(AccessAction.CHECKIN);
            log.setDate(LocalDateTime.now());

            accessLogRepository.save(log);

            return "Check-IN successful for student: " + studentId;
        }
        else {
            return String.valueOf(new BadRequestException("You need to cancel the existing leave before checking in."));
        }


    }

    @Override
    public String checkOut(Long studentId) {

        Student student=studentService.getStudentById(studentId);

        AccessLog log=new AccessLog();
        log.setStudent(student);
        log.setAction(AccessAction.CHECKOUT);
        log.setDate(LocalDateTime.now());

        accessLogRepository.save(log);


        return "Check-OUT successful for student: " + studentId;
    }

    @Override
    public List<AccessLogResponse> getTop10CheckIn() {
        List<AccessLog> accessLogList=accessLogRepository.findTop10ByActionOrderByIdDesc(AccessAction.CHECKIN);
        return mapToReponseList(accessLogList);
    }
    @Override
    public List<AccessLogResponse> getTop10CheckOut() {
        List<AccessLog> accessLogList=accessLogRepository.findTop10ByActionOrderByIdDesc(AccessAction.CHECKOUT);
        return mapToReponseList(accessLogList);
    }

    private AccessLogResponse mapToReponse(AccessLog accessLog){
        AccessLogResponse response=new AccessLogResponse(
                accessLog.getId(),
                accessLog.getDate(),
                accessLog.getAction(),
                accessLog.getStudent().getId()
        );
        return response;
    }

    private List<AccessLogResponse> mapToReponseList(List<AccessLog> accessLogList){
        return accessLogList.stream()
                .map(this::mapToReponse).
                toList();
    }
}
