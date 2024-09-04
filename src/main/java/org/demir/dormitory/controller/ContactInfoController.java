package org.demir.dormitory.controller;

import jakarta.validation.Valid;
import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.ContactInfoRequest;
import org.demir.dormitory.dto.request.ContactInfoUpdateRequest;
import org.demir.dormitory.dto.response.ContactInfoResponse;
import org.demir.dormitory.service.ContactInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/contact-info")
public class ContactInfoController {

    private final ContactInfoService contactInfoService;

    public ContactInfoController(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
    }

    @GetMapping("/oneContactInfo")
    public ApiResponse<ContactInfoResponse> getContactInfoById(@RequestParam Long id) {
        return new ApiResponse<>("Contact information retrieved successfully.", contactInfoService.getContactInfoById(id), HttpStatus.FOUND);
    }

//    @PostMapping("/save")
//    public ApiResponse<ContactInfoResponse> saveContactInfo(@RequestBody ContactInfoRequest request) {
//        return new ApiResponse<>("Contact information saved successfully.", contactInfoService.saveContactInfo(request), HttpStatus.CREATED);
//    }


    @PutMapping("/update")
    public ApiResponse<ContactInfoResponse> updateContactInfo(@RequestBody @Valid ContactInfoUpdateRequest request) {
        return new ApiResponse<>("Contact information updated successfully.", contactInfoService.updateContactInfo(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteContactInfo(@RequestParam Long id) {
        contactInfoService.deleteContactInfo(id);
        return new ApiResponse<>("Contact information deleted successfully.", null, HttpStatus.OK);
    }
}
