package org.demir.dormitory.entity;

import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@Document(collection = "lessons")
public class Lesson extends BaseEntity {

    private String name;
    private String description;
    private int currentStudentCount = 0;
    private int maxStudentCount;

    @DBRef
    private Teacher teacher;
}
