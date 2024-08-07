package org.demir.dormitory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;

@Entity
@Getter
@Setter
public class Image extends BaseEntity {


    @Column(length = 10485760)
    private String base64Data;


}
