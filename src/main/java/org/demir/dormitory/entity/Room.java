package org.demir.dormitory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.demir.dormitory.listener.EntityListener;

import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(EntityListener.class)
public class Room extends BaseEntity {

    private int capacity;
    private int currentOccupancy;

    @OneToMany(mappedBy = "room")
    private List<Student> students;


}
