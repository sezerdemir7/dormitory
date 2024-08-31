package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ImageRequest;
import org.demir.dormitory.dto.request.StaffRequest;
import org.demir.dormitory.dto.request.StaffUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.dto.response.ImageResponse;
import org.demir.dormitory.dto.response.StaffResponse;
import org.demir.dormitory.entity.ContactInfo;
import org.demir.dormitory.entity.Image;
import org.demir.dormitory.entity.Staff;
import org.demir.dormitory.exception.BadRequestException;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.StaffRepository;
import org.demir.dormitory.service.ContactInfoService;
import org.demir.dormitory.service.IdGeneratorService;
import org.demir.dormitory.service.ImageService;
import org.demir.dormitory.service.StaffService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService, UserDetailsService {

    private final StaffRepository staffRepository;
    private final ContactInfoService contactInfoService;
    private final ImageService imageService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final IdGeneratorService idGeneratorService;

    public StaffServiceImpl(StaffRepository staffRepository, ContactInfoService contactInfoService,
                            ImageService imageService, BCryptPasswordEncoder passwordEncoder, IdGeneratorService idGeneratorService) {
        this.staffRepository = staffRepository;
        this.contactInfoService = contactInfoService;
        this.imageService = imageService;
        this.passwordEncoder = passwordEncoder;
        this.idGeneratorService = idGeneratorService;
    }


    public Optional<Staff> getByUsername(String username) {
        return staffRepository.findByUsername(username);
    }

    public Staff createUser(StaffRequest request) {
        Staff newStaff = new Staff();

        newStaff.setName(request.name());
        newStaff.setUsername(request.username());
        newStaff.setSurname(request.surname());
        newStaff.setPassword(passwordEncoder.encode(request.password()));
        newStaff.setAuthorities(request.authorities());
        newStaff.setAccountNonExpired(true);
        newStaff.setCredentialsNonExpired(true);
        newStaff.setEnabled(true);
        newStaff.setAccountNonLocked(true);
        return staffRepository.save(newStaff);


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Staff> user = staffRepository.findByUsername(username);
        return user.orElseThrow(() -> new BadRequestException("User not found"));
    }


    @Override
    public StaffResponse saveStaff(StaffRequest request) {
        Staff toSave = mapToStaff(request);
        Staff staff = staffRepository.save(toSave);
        return mapToResponse(staff);
    }

    @Override
    public void deleteStaff(Long staffId) {
        Staff staff = findStaffById(staffId);
        staff.setDeleted(true);
        staffRepository.save(staff);
    }

    @Override
    public StaffResponse getStaffByName(String staffName) {
        Staff staff = staffRepository.findByName(staffName).orElseThrow(() ->
                new NotFoundException("Staff not found!"));
        return mapToResponse(staff);
    }

    @Override
    public List<StaffResponse> getAllStaff() {
        List<Staff> staffList = staffRepository.findAllByIsDeletedFalse();
        return mapToResponseList(staffList);
    }

    @Override
    public StaffResponse updateStaff(StaffUpdateRequest request) {
        Staff toUpdate = findStaffById(request.id());
        toUpdate.setName(request.name());
        toUpdate.setSurname(request.surname());
        toUpdate.setUsername(request.username());
        toUpdate.setAuthorities(request.authorities());
        Staff staff = staffRepository.save(toUpdate);
        return mapToResponse(staff);
    }

    @Override
    public StaffResponse getOneStaffById(Long staffId) {
        Staff staff = findStaffById(staffId);
        return mapToResponse(staff);
    }

    @Override
    public ContactInfoResponse saveContactInfo(Long staffId, ContactInfoRequest contactInfoRequest) {
        Staff staff = findStaffById(staffId);
        ContactInfo contactInfo = contactInfoService.saveContactInfo(contactInfoRequest);
        staff.setContactInfo(contactInfo);
        staffRepository.save(staff);
        return contactInfoService.mapToResponse(contactInfo);
    }

    @Override
    public ImageResponse getOneStaffImage(Long staffId) {
        Staff staff = findStaffById(staffId);
        return mapToImageResponse(staff, staff.getImage());
    }

    @Override
    public ImageResponse saveStaffImage(ImageRequest request) {
        Staff toSave = findStaffById(request.entityId());
        Image image = imageService.saveImage(request.base64Data());
        toSave.setImage(image);
        Staff staff = staffRepository.save(toSave);
        return mapToImageResponse(staff, image);
    }

    @Override
    public Staff getStaffById(Long staffId) {
        return findStaffById(staffId);
    }

    @Override
    public boolean getByMail(String username, String mail) {
        Optional<Staff> staff = staffRepository.findByUsername(username);
        ContactInfo contactInfo = contactInfoService.getByMail(mail);

        if (staff.get().getContactInfo() == contactInfo) {
            return true;
        }
        return false;
    }

    private ImageResponse mapToImageResponse(Staff staff, Image image) {
        return new ImageResponse(
                staff.getId(),
                staff.getName(),
                image.getId(),
                image.getBase64Data());
    }

    private Staff findStaffById(Long staffId) {
        return staffRepository.findById(staffId).orElseThrow(() ->
                new NotFoundException("Staff not found!"));
    }

    private Staff mapToStaff(StaffRequest request) {
        Long id=idGeneratorService.generateNextSequenceId("staff");
        Staff staff = new Staff();
        staff.setId(id);
        staff.setName(request.name());
        staff.setSurname(request.surname());
        staff.setUsername(request.username());
        staff.setAuthorities(request.authorities());
        staff.setPassword(request.password());
        return staff;
    }

    private StaffResponse mapToResponse(Staff staff) {
        return new StaffResponse(
                staff.getId(),
                staff.getName(),
                staff.getSurname(),
                staff.getUsername(),
                staff.getAuthorities()
        );
    }

    private List<StaffResponse> mapToResponseList(List<Staff> staffList) {
        return staffList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}

