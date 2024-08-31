package org.demir.dormitory.entity;

import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@Document(collection = "halls")
public class Hall extends BaseEntity {

    private String name;
    private String location;
    private boolean isAvailable = true;
    private int capacity;

    @DBRef
    private Staff staff;
}
