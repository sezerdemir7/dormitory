package org.demir.dormitory.entity;

import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.demir.dormitory.entity.enumType.AccessAction;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "access_logs")
public class AccessLog extends BaseEntity {

    @DBRef
    private Student student;

    private LocalDateTime date;

    private AccessAction action;
}
