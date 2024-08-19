package org.demir.dormitory.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BasePerson;
import org.demir.dormitory.listener.EntityListener;

@Entity
@EntityListeners(EntityListener.class)
@Getter
@Setter
public class Teacher extends BasePerson {

    @OneToOne
    private Lesson lesson;

}
