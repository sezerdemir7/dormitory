package org.demir.dormitory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.demir.dormitory.listener.EntityListener;

@Entity
@Getter
@Setter
@EntityListeners(EntityListener.class)
public class Hall extends BaseEntity {

    private String name;
    private String location;
    private boolean isAvailable = true;
    private int capacity;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
}
