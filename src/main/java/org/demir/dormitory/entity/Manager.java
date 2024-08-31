package org.demir.dormitory.entity;

import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BasePerson;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "managers")
public class Manager extends BasePerson {

    private String password;
}
