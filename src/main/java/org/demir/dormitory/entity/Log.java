package org.demir.dormitory.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;

@Entity
@Getter
@Setter
public class Log extends BaseEntity {

    private String entityName;
    private Long entityId;
    private String action;


}
