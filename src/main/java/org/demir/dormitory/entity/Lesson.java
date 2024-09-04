package org.demir.dormitory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.demir.dormitory.listener.EntityListener;

@Entity
@Getter
@Setter
@EntityListeners(EntityListener.class)
public class Lesson extends BaseEntity {

    private String name;
    private String description;
    private int currentStudentCount=0;
    private int maxStudentCount;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;




}
