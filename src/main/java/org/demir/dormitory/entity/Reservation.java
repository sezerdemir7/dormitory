package org.demir.dormitory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.demir.dormitory.common.EntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(EntityListener.class)
public class Reservation extends BaseEntity {

    @ManyToOne
    private PlayGround playGround;

    @ManyToOne
    private Student student;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private boolean isApproved=false;
    private boolean status=true;
}
