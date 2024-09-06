package org.demir.dormitory.service.impl;

import jakarta.persistence.EntityExistsException;
import org.demir.dormitory.dto.request.StaffRequest;
import org.demir.dormitory.dto.response.StaffResponse;
import org.demir.dormitory.entity.Staff;
import org.demir.dormitory.entity.enumType.StaffRole;
import org.demir.dormitory.exception.BadRequestException;
import org.demir.dormitory.repository.StaffRepository;
import org.demir.dormitory.service.ContactInfoService;
import org.demir.dormitory.service.ImageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StaffServiceImplTest {

    private StaffRepository staffRepository;
    private ContactInfoService contactInfoService;
    private ImageService imageService;
    private BCryptPasswordEncoder passwordEncoder;
    private StaffServiceImpl staffService;


    @BeforeEach
    void setUp() throws Exception {
        staffRepository= Mockito.mock(StaffRepository.class);
        contactInfoService= Mockito.mock(ContactInfoService.class);
        imageService= Mockito.mock(ImageService.class);
        passwordEncoder= Mockito.mock(BCryptPasswordEncoder.class);
        staffService= new StaffServiceImpl(staffRepository, contactInfoService, imageService, passwordEncoder);
    }


    @Test
    void testGetByUsername() {
        // Arrange
        String username = "johndoe";
        Staff staff = new Staff();
        staff.setUsername(username);
        staff.setId(1L);
        staff.setName("John Doe");

        when(staffRepository.findByUsername(username)).thenReturn(Optional.of(staff));


        Optional<Staff> result = staffService.getByUsername(username);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(staff.getUsername(), result.get().getUsername());
        assertEquals(staff.getId(), result.get().getId());
        assertEquals(staff.getName(), result.get().getName());

        verify(staffRepository).findByUsername(staff.getUsername());
    }

    @Test
    void testGetByUsernameNotFound() {
        String username = "notfound";
        when(staffRepository.findByUsername(username)).thenReturn(Optional.empty());
        Optional<Staff> result = staffService.getByUsername(username);
        assertTrue(result.isEmpty());
        verify(staffRepository).findByUsername(username);
    }




    @AfterEach
    void tearDown() {

    }
}