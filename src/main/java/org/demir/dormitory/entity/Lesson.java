package org.demir.dormitory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.demir.dormitory.common.EntityListener;

@Entity
@Getter
@Setter
@EntityListeners(EntityListener.class)
public class Lesson extends BaseEntity {

    private String name;
    private String description;
    private int currentStudentCount=0;
    private int maxStudentCount;

    @OneToOne(mappedBy = "lesson")
    private Teacher teacher;




}
