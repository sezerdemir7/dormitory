package org.demir.dormitory.entity;

import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "leaves")
public class Leave extends BaseEntity {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isApproved = false;

    @DBRef
    private Student student;
}
