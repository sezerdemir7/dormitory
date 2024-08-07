package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.EmployeeRequest;
import org.demir.dormitory.dto.request.EmployeeUpdateRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.EmployeeResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.entity.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse saveEmployee(EmployeeRequest saveEmployeeRequest);

    void deleteEmployee(Long employeeId);

    EmployeeResponse getEmployeeByName(String employeeName);

    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse updateEmployee(EmployeeUpdateRequest employeeUpdateRequest);

    Employee getEmployeeById(Long employeeId);

    EmployeeResponse getOneEmployeeById(Long employeeId);

    ContactInfoResponse saveContactInfo(Long employeeId, ContactInfoRequest contactInfoRequest);

    ImageResponse saveEmployeeImage(ImageRequest request);

    ImageResponse getOneEmployeeImage(Long employeeId);
}
