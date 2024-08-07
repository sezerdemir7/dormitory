package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.HallRequest;
import org.demir.dormitory.dto.request.HallUpdateRequest;
import org.demir.dormitory.dto.response.HallResponse;
import org.demir.dormitory.entity.Employee;
import org.demir.dormitory.entity.Hall;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.HallRepository;
import org.demir.dormitory.service.EmployeeService;
import org.demir.dormitory.service.HallService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final EmployeeService employeeService;

    public HallServiceImpl(HallRepository hallRepository, EmployeeService employeeService) {
        this.hallRepository = hallRepository;
        this.employeeService = employeeService;
    }

    @Override
    public HallResponse saveHall(HallRequest request) {
        Hall toSave=mapToHall(request);
        Hall hall=hallRepository.save(toSave);
        return mapToResponse(hall);
    }

    @Override
    public void deleteHall(Long hallId) {
        Hall hall=findHallById(hallId);
        hall.setDeleted(true);
        hallRepository.save(hall);
    }

    @Override
    public HallResponse getHallByName(String hallName) {
        Hall hall=hallRepository.findByName(hallName).orElseThrow(() ->
                new NotFoundException("Hall not found!"));
        return null;
    }

    @Override
    public List<HallResponse> getAllHalls() {
        List<Hall> hallList=hallRepository.findAllByIsDeletedFalse();
        return mapToResponseList(hallList);
    }

    @Override
    public HallResponse updateHall(HallUpdateRequest request) {
        Hall toUpdate=findHallById(request.id());
        toUpdate.setName(request.name());
        toUpdate.setLocation(request.location());
        toUpdate.setCapacity(request.capacity());
        Hall hall=hallRepository.save(toUpdate);
        return mapToResponse(hall);
    }

    @Override
    public Hall getHallById(Long hallId) {
        return findHallById(hallId);
    }

    @Override
    public HallResponse getOneHallById(Long hallId) {
        Hall hall=findHallById(hallId);
        return mapToResponse(hall);
    }

    private Hall findHallById(Long hallId) {
        return hallRepository.findById(hallId).orElseThrow(() ->
                new NotFoundException("Hall not found!"));
    }

    private Hall mapToHall(HallRequest request) {
        Hall hall = new Hall();
        Employee employee=employeeService.getEmployeeById(request.employeeId());
        hall.setName(request.name());
        hall.setCapacity(request.capacity());
        hall.setLocation(request.location());
        hall.setEmployee(employee);
        return hall;
    }

    private HallResponse mapToResponse(Hall hall) {
        HallResponse response = new HallResponse(
                hall.getId(),
                hall.getName(),
                hall.getLocation(),
                hall.isAvailable(),
                hall.getCapacity(),
                hall.getEmployee().getId()

        );
        return response;
    }

    private List<HallResponse> mapToResponseList(List<Hall> hallList) {
        List<HallResponse> responseList = hallList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return responseList;
    }

}
