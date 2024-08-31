package org.demir.dormitory.entity;

import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Getter
@Setter
@Document(collection = "rooms")
public class Room extends BaseEntity {

    private int capacity;
    private int currentOccupancy;

    @DBRef
    private List<Student> students;
}
