package org.demir.dormitory.entity;

import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "logs")
public class Log extends BaseEntity {

    private String entityName;
    private Long entityId;
    private String action;
}
