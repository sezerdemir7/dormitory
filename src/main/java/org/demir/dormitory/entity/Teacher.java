package org.demir.dormitory.entity;

import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BasePerson;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@Document(collection = "teachers")
public class Teacher extends BasePerson {

    @DBRef
    private Lesson lesson;
}
