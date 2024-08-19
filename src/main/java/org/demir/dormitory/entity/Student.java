package org.demir.dormitory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BasePerson;
import org.demir.dormitory.listener.EntityListener;

import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(EntityListener.class)
public class Student extends BasePerson {

    @OneToMany(mappedBy = "student")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "student")
    private List<Leave> leaves;

    @OneToMany(mappedBy = "student")
    private List<AccessLog> accessLogs;

    @ManyToOne
    private Room room;



}
