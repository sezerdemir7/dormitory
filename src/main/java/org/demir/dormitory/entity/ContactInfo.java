package org.demir.dormitory.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.demir.dormitory.common.BaseEntity;

@Entity
@Getter
@Setter
public class ContactInfo extends BaseEntity {

    private String email;
    private String phoneNumber;
    private String address;
    private boolean verified = false;

}
