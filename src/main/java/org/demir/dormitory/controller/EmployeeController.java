package org.demir.dormitory.controller;

import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.EmployeeRequest;
import org.demir.dormitory.dto.request.EmployeeUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.EmployeeResponse;
import org.demir.dormitory.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/oneEmployee")
    public ApiResponse<EmployeeResponse> getEmployeeById(@RequestParam Long id) {
        return new ApiResponse<>("Employee retrieved successfully.",employeeService.getOneEmployeeById(id), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ApiResponse<EmployeeResponse> saveEmployee(@RequestBody EmployeeRequest request) {
        return new ApiResponse<>("Employee saved successfully.", employeeService.saveEmployee(request), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ApiResponse<List<EmployeeResponse>> getAllEmployees() {
        return new ApiResponse<>("Employees retrieved successfully.", employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ApiResponse<EmployeeResponse> updateEmployee(@RequestBody EmployeeUpdateRequest request) {
        return new ApiResponse<>( "Employee updated successfully.", employeeService.updateEmployee(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ApiResponse<>("Employee deleted successfully.", null, HttpStatus.OK);
    }

    @PostMapping("/save/contact-info")
    public ApiResponse<ContactInfoResponse> saveContactInfo(@RequestParam Long employeeId, @RequestBody ContactInfoRequest request){
        return new ApiResponse<>("student info saved",employeeService.saveContactInfo(employeeId,request),HttpStatus.CREATED);
    }
}
