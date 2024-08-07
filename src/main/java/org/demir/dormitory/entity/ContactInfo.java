package org.demir.dormitory.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;

@Entity
@Getter
@Setter
public class ContactInfo extends BaseEntity {
    private String email;
    private String phoneNumber;
    private String address;
}
