package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ContactInfoUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.entity.ContactInfo;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.ContactInfoRepository;
import org.demir.dormitory.service.ContactInfoService;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;

    public ContactInfoServiceImpl(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }

    @Override
    public ContactInfo saveContactInfo(ContactInfoRequest contactInfoRequest) {
        ContactInfo toSave=mapToContactInfo(contactInfoRequest);
        return contactInfoRepository.save(toSave);
    }

    @Override
    public void deleteContactInfo(Long contactInfoId) {
        ContactInfo contactInfo=contactInfoRepository.findById(contactInfoId).orElseThrow(()->
                new NotFoundException("ContactIfo not found!"));
        contactInfo.setDeleted(true);
        contactInfoRepository.save(contactInfo);
    }


    @Override
    public ContactInfoResponse getContactInfoById(Long contactInfoId) {
        ContactInfo contactInfo=contactInfoRepository.findById(contactInfoId).orElseThrow(()->
                new NotFoundException("ContactInfo not found!"));
        return mapToResponse(contactInfo);
    }

    @Override
    public ContactInfoResponse updateContactInfo(ContactInfoUpdateRequest request) {
        ContactInfo toUpdate=contactInfoRepository.findById(request.id()).orElseThrow(()->
                new NotFoundException("ContactInfo not found!"));

        toUpdate.setAddress(request.address());
        toUpdate.setEmail(request.email());
        toUpdate.setPhoneNumber(request.phoneNumber());

        ContactInfo contactInfo=contactInfoRepository.save(toUpdate);
        return mapToResponse(contactInfo);
    }

    public ContactInfoResponse mapToResponse(ContactInfo contactInfo){

        ContactInfoResponse contactInfoResponse=
                new ContactInfoResponse(contactInfo.getId(),
                        contactInfo.getEmail(),
                        contactInfo.getPhoneNumber(),
                        contactInfo.getPhoneNumber());
        return contactInfoResponse;

    }
    private ContactInfo mapToContactInfo(ContactInfoRequest request){
        ContactInfo contactInfo=new ContactInfo();
        contactInfo.setEmail(request.email());
        contactInfo.setAddress(request.address());
        contactInfo.setPhoneNumber(request.phoneNumber());
        return contactInfo;
    }
}
