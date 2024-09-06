package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.entity.ContactInfo;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.ContactInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
class ContactInfoServiceImplTest {

    private ContactInfoRepository contactInfoRepository;
    private ContactInfoServiceImpl contactInfoService;

    @BeforeEach
    void setUp() {
        contactInfoRepository = Mockito.mock(ContactInfoRepository.class);
        contactInfoService = new ContactInfoServiceImpl(contactInfoRepository);
    }

    @Test
    void saveContactInfo() {
        ContactInfoRequest request = new
                ContactInfoRequest("test@test.com", "0123456785", "test address");

        ContactInfo toSave = new ContactInfo();
        toSave.setEmail(request.email());
        toSave.setPhoneNumber(request.phoneNumber());
        toSave.setAddress(request.address());

        ContactInfo savedInfo = new ContactInfo();
        savedInfo.setId(1L);
        savedInfo.setEmail(toSave.getEmail());
        savedInfo.setPhoneNumber(toSave.getPhoneNumber());
        savedInfo.setAddress(toSave.getAddress());

        when(contactInfoRepository.save(toSave)).thenReturn(savedInfo);

        ContactInfo response = contactInfoService.saveContactInfo(request);

        assertEquals(savedInfo.getId(),response.getId());
        assertEquals(savedInfo.getEmail(),response.getEmail());
        assertEquals(savedInfo.getPhoneNumber(),response.getPhoneNumber());
        assertEquals(savedInfo.getAddress(),response.getAddress());

        verify(contactInfoRepository).save(toSave);

    }

    @Test
    void deleteContactInfo_Success() {

        Long contactInfoId = 1L;
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setId(contactInfoId);
        contactInfo.setDeleted(false);

        when(contactInfoRepository.findById(contactInfoId)).thenReturn(Optional.of(contactInfo));

        contactInfoService.deleteContactInfo(contactInfoId);

        assertTrue(contactInfo.isDeleted());
        verify(contactInfoRepository).findById(contactInfoId);
        verify(contactInfoRepository).save(contactInfo);
    }

    @Test
    void deleteContactInfo_NotFound() {

        Long contactInfoId = 1L;

        when(contactInfoRepository.findById(contactInfoId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            contactInfoService.deleteContactInfo(contactInfoId);
        });

        assertEquals("ContactIfo not found!", exception.getMessage());

        verify(contactInfoRepository).findById(contactInfoId);

    }
    @Test
    void getByMail_Success() {
        String email = "test@test.com";
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setEmail(email);

        when(contactInfoRepository.findByEmail(email)).thenReturn(contactInfo);

        ContactInfo result = contactInfoService.getByMail(email);

        assertEquals(contactInfo.getEmail(), result.getEmail());

        verify(contactInfoRepository).findByEmail(email);
    }

    @Test
    void getByMail_NotFound() {
        String email = "test@test.com";

        when(contactInfoRepository.findByEmail(email)).thenReturn(null);

        ContactInfo result = contactInfoService.getByMail(email);

        assertNull(result);

        verify(contactInfoRepository).findByEmail(email);
    }

    @Test
    void verifyMail_Success() {
        String email = "test@test.com";
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setEmail(email);
        contactInfo.setVerified(false);

        when(contactInfoRepository.findByEmail(email)).thenReturn(contactInfo);

        contactInfoService.verifyMail(email);

        assertTrue(contactInfo.isVerified());
        verify(contactInfoRepository).save(contactInfo);
    }

    @Test
    void verifyMail_NotFound() {
        String email = "test@test.com";

        when(contactInfoRepository.findByEmail(email)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> {
            contactInfoService.verifyMail(email);
        });

        verify(contactInfoRepository).findByEmail(email);
    }


    @AfterEach
    void tearDown() {
    }
}