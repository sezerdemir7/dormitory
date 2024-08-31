package org.demir.dormitory.entity;

import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "contact_info")
public class ContactInfo extends BaseEntity {

    private String email;
    private String phoneNumber;
    private String address;

}
