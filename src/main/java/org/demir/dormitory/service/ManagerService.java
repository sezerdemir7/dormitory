package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.request.ManagerRequest;
import org.demir.dormitory.dto.request.ManagerUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.dto.response.ManagerResponse;
import org.demir.dormitory.entity.Manager;

import java.util.List;

public interface ManagerService {

    ManagerResponse saveManager(ManagerRequest saveManagerRequest);

    void deleteManager(Long managerId);

    ManagerResponse getManagerByName(String managerName);

    List<ManagerResponse> getAllManagers();

    ManagerResponse updateManager(ManagerUpdateRequest managerUpdateRequest);

    ManagerResponse getOneManagerById(Long managerId);

    ContactInfoResponse saveContactInfo(Long managerId, ContactInfoRequest contactInfoRequest);
    ImageResponse saveManagerImage(ImageRequest request);

    ImageResponse getOneManagerImage(Long managerId);
}
