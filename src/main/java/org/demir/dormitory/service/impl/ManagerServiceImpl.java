package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.request.ManagerRequest;
import org.demir.dormitory.dto.request.ManagerUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.dto.response.ManagerResponse;
import org.demir.dormitory.entity.*;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.ManagerRepository;
import org.demir.dormitory.service.ContactInfoService;
import org.demir.dormitory.service.IdGeneratorService;
import org.demir.dormitory.service.ImageService;
import org.demir.dormitory.service.ManagerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final ContactInfoService contactInfoService;
    private final ImageService imageService;
    private final IdGeneratorService idGeneratorService;

    public ManagerServiceImpl(ManagerRepository managerRepository, ContactInfoService contactInfoService, ImageService imageService, IdGeneratorService idGeneratorService) {
        this.managerRepository = managerRepository;
        this.contactInfoService = contactInfoService;
        this.imageService = imageService;
        this.idGeneratorService = idGeneratorService;
    }

    @Override
    public ManagerResponse saveManager(ManagerRequest request) {
        Manager toSave = mapToManager(request);
        Manager manager = managerRepository.save(toSave);
        return mapToResponse(manager);
    }

    @Override
    public void deleteManager(Long managerId) {
        Manager manager = findManagerById(managerId);
        manager.setDeleted(true);
        managerRepository.save(manager);
    }

    @Override
    public ManagerResponse getManagerByName(String managerName) {
        Manager manager = managerRepository.findByName(managerName).orElseThrow(() ->
                new NotFoundException("Manager not found!"));
        return mapToResponse(manager);
    }

    @Override
    public List<ManagerResponse> getAllManagers() {
        List<Manager> managerList = managerRepository.findAllByIsDeletedFalse();
        return mapToResponseList(managerList);
    }

    @Override
    public ManagerResponse updateManager(ManagerUpdateRequest request) {
        Manager toUpdate = findManagerById(request.id());
        toUpdate.setName(request.name());
        toUpdate.setSurname(request.surname());
        toUpdate.setPassword(request.password());
        Manager manager = managerRepository.save(toUpdate);
        return mapToResponse(manager);
    }

    @Override
    public ManagerResponse getOneManagerById(Long managerId) {
        Manager manager=findManagerById(managerId);
        return mapToResponse(manager);
    }

    @Override
    public ContactInfoResponse saveContactInfo(Long managerId, ContactInfoRequest contactInfoRequest) {
        Manager manager=findManagerById(managerId);
        ContactInfo contactInfo=contactInfoService.saveContactInfo(contactInfoRequest);
        manager.setContactInfo(contactInfo);
        managerRepository.save(manager);
        return contactInfoService.mapToResponse(contactInfo);
    }
    @Override
    public ImageResponse getOneManagerImage(Long studentId) {
        Manager manager = findManagerById(studentId);
        return mapToImageResponse(manager,manager.getImage());
    }

    @Override
    public ImageResponse saveManagerImage(ImageRequest request) {
        Manager toSave = findManagerById(request.entityId());
        Image image = imageService.saveImage(request.base64Data());
        toSave.setImage(image);
        Manager manager = managerRepository.save(toSave);
        return mapToImageResponse(manager, image);

    }

    private ImageResponse mapToImageResponse(Manager manager, Image image) {
        return new ImageResponse(
                manager.getId(),
                manager.getName(),
                image.getId(),
                image.getBase64Data());
    }

    private Manager findManagerById(Long managerId) {
        return managerRepository.findById(managerId).orElseThrow(() ->
                new NotFoundException("Manager not found!"));
    }

    private Manager mapToManager(ManagerRequest request) {
        Long id=idGeneratorService.generateNextSequenceId("manager");
        Manager manager = new Manager();
        manager.setId(id);
        manager.setName(request.name());
        manager.setSurname(request.surname());
        manager.setPassword(request.password());
        return manager;
    }

    private ManagerResponse mapToResponse(Manager manager) {
        return new ManagerResponse(
                manager.getId(),
                manager.getName(),
                manager.getSurname()
        );
    }

    private List<ManagerResponse> mapToResponseList(List<Manager> managerList) {
        return managerList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
