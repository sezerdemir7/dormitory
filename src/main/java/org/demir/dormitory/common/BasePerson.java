package org.demir.dormitory.common;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.demir.dormitory.entity.ContactInfo;
import org.demir.dormitory.entity.Image;

@MappedSuperclass
@Getter
@Setter
public abstract class BasePerson extends BaseEntity {

    private String name;

    private String surname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_info_id", referencedColumnName = "id")
    private ContactInfo contactInfo;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

}
