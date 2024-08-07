package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.EmployeeRequest;
import org.demir.dormitory.dto.request.EmployeeUpdateRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.EmployeeResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.entity.ContactInfo;
import org.demir.dormitory.entity.Employee;
import org.demir.dormitory.entity.Image;
import org.demir.dormitory.entity.Student;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.EmployeeRepository;
import org.demir.dormitory.service.ContactInfoService;
import org.demir.dormitory.service.EmployeeService;
import org.demir.dormitory.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ContactInfoService  contactInfoService;
    private final ImageService imageService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ContactInfoService contactInfoService, ImageService imageService) {
        this.employeeRepository = employeeRepository;
        this.contactInfoService = contactInfoService;
        this.imageService = imageService;
    }


    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest request) {
        Employee toSave=mapToEmployee(request);
        Employee employee=employeeRepository.save(toSave);
        return mapToResponse(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee=findEmployeeById(employeeId);
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeResponse getEmployeeByName(String employeeName) {
        Employee employee=employeeRepository.findByName(employeeName).orElseThrow(() ->
                new NotFoundException("Employee not found!"));
        return mapToResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employeeList=employeeRepository.findAllByIsDeletedFalse();
        return mapToResponseList(employeeList);
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeUpdateRequest request) {
        Employee toUpdate=findEmployeeById(request.id());
        toUpdate.setName(request.name());
        toUpdate.setSurname(request.surname());
        Employee employee=employeeRepository.save(toUpdate);
        return mapToResponse(employee);
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return findEmployeeById(employeeId);
    }

    @Override
    public EmployeeResponse getOneEmployeeById(Long employeeId) {
        Employee employee=findEmployeeById(employeeId);
        return mapToResponse(employee);
    }
    @Override
    public ContactInfoResponse saveContactInfo(Long employeeId, ContactInfoRequest contactInfoRequest) {
        Employee employee = findEmployeeById(employeeId);
        ContactInfo contactInfo=contactInfoService.saveContactInfo(contactInfoRequest);
        employee.setContactInfo(contactInfo);
        employeeRepository.save(employee);
        return contactInfoService.mapToResponse(contactInfo);
    }
    @Override
    public ImageResponse getOneEmployeeImage(Long employeeId) {
        Employee employee = findEmployeeById(employeeId);
        return mapToImageResponse(employee,employee.getImage());
    }

    @Override
    public ImageResponse saveEmployeeImage(ImageRequest request) {
        Employee toSave = findEmployeeById(request.entityId());
        Image image = imageService.saveImage(request.base64Data());
        toSave.setImage(image);
        Employee employee = employeeRepository.save(toSave);
        return mapToImageResponse(employee, image);

    }

    private ImageResponse mapToImageResponse(Employee employee, Image image) {
        return new ImageResponse(
                employee.getId(),
                employee.getName(),
                image.getId(),
                image.getBase64Data());
    }


    private Employee findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() ->
                new NotFoundException("Employee not found!"));
    }

    private Employee mapToEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.name());
        employee.setSurname(request.surname());
        return employee;
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getSurname()
        );
        return response;
    }
    private List<EmployeeResponse> mapToResponseList(List<Employee> employeeList) {
        List<EmployeeResponse> responseList = employeeList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return responseList;
    }
}
