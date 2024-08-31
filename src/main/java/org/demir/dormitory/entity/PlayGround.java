package org.demir.dormitory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.demir.dormitory.entity.enumType.PlayGroundType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Getter
@Setter
@Document(collection = "playgrounds")
public class PlayGround extends BaseEntity {

    private String name;
    private PlayGroundType type;
    private boolean isAvailable = true;

    @DBRef
    @JsonIgnore
    private List<Reservation> reservations;
}
