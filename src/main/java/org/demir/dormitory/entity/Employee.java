package org.demir.dormitory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BasePerson;
import org.demir.dormitory.listener.EntityListener;

@Entity
@Getter
@Setter
@EntityListeners(EntityListener.class)
public class Employee extends BasePerson{

    @OneToOne(mappedBy = "employee")
    private Hall hall;
}
