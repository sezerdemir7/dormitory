package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.request.StaffRequest;
import org.demir.dormitory.dto.request.StaffUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.dto.response.StaffResponse;
import org.demir.dormitory.entity.Staff;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface StaffService extends UserDetailsService {

    Optional<Staff> getByUsername(String username);

    boolean checkMail(String username,String mail);

    UserDetails loadUserByUsername(String username);

    Staff createStaff(StaffRequest request);

    StaffResponse saveStaff(StaffRequest request);

    void deleteStaff(Long staffId);

    StaffResponse getStaffByName(String staffName);

    List<StaffResponse> getAllStaff();

    StaffResponse updateStaff(StaffUpdateRequest request);

    StaffResponse getOneStaffById(Long staffId);

    Staff getStaffById(Long staffId);

    ContactInfoResponse saveContactInfo(Long staffId, ContactInfoRequest contactInfoRequest);

    ImageResponse getOneStaffImage(Long staffId);

    ImageResponse saveStaffImage(ImageRequest request);



}
