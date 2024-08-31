package org.demir.dormitory.repository;

import org.demir.dormitory.entity.ContactInfo;

public interface ContactInfoRepository extends BaseRepository<ContactInfo, Long> {
    ContactInfo findByEmail(String email);
}
