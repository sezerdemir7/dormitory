package org.demir.dormitory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.AccessAction;
import org.demir.dormitory.common.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AccessLog extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private AccessAction action;

}
