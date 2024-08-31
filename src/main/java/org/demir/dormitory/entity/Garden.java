package org.demir.dormitory.entity;

import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "gardens")
public class Garden extends BaseEntity {

    private String name;
    private String location;
    private boolean isAvailable = true;
}
