package org.demir.dormitory.entity;

import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BasePerson;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Getter
@Setter
@Document(collection = "students")
public class Student extends BasePerson {

    @DBRef
    private List<Reservation> reservations;

    @DBRef
    private List<Leave> leaves;

    @DBRef
    private List<AccessLog> accessLogs;

    @DBRef
    private Room room;
}
