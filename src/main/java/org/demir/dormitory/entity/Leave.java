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
public class Leave extends BaseEntity {


    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isApproved = false;

    @ManyToOne
    private Student student;


}
