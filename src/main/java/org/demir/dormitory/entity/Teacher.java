package org.demir.dormitory.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BasePerson;
import org.demir.dormitory.listener.EntityListener;

import java.util.Set;

@Entity
@EntityListeners(EntityListener.class)
@Getter
@Setter
public class Teacher extends BasePerson {

    @OneToMany(mappedBy = "teacher")
    private Set<Lesson> lessons;

}
