package org.demir.dormitory.service;

import org.demir.dormitory.dto.response.AccessLogResponse;

import java.util.List;

public interface AccessControlService {
    String checkIn(Long studentId);
    String checkOut(Long studentId);
    List<AccessLogResponse> getTop10CheckIn();
    List<AccessLogResponse> getTop10CheckOut();
}
