package org.demir.dormitory.common;

import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.entity.ContactInfo;
import org.demir.dormitory.entity.Image;
import org.springframework.data.mongodb.core.mapping.DBRef;


@Getter
@Setter
public abstract class BasePerson extends BaseEntity {

    private String name;

    private String surname;

    @DBRef
    private ContactInfo contactInfo;

    @DBRef
    private Image image;

}
