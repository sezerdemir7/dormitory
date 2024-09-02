package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ContactInfoUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.entity.ContactInfo;

public interface ContactInfoService {

    ContactInfo saveContactInfo(ContactInfoRequest contactInfoRequest);
    void deleteContactInfo(Long contactInfoId);
    ContactInfoResponse getContactInfoById(Long contactInfoId);
    ContactInfoResponse updateContactInfo(ContactInfoUpdateRequest request);
    ContactInfoResponse mapToResponse(ContactInfo contactInfo);
    ContactInfo getByMail( String mail);

    void verifyMail(String mail);
}
