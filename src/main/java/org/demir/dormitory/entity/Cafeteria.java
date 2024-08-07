package org.demir.dormitory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.demir.dormitory.common.EntityListener;

@Entity
@Getter
@Setter
@EntityListeners(EntityListener.class)
public class Cafeteria extends BaseEntity {

    private String name;
    private String location;

}
