package org.demir.dormitory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BaseEntity;
import org.demir.dormitory.listener.EntityListener;
import org.demir.dormitory.common.PlayGroundType;

import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(EntityListener.class)
public class PlayGround extends BaseEntity {

    private String name;
    private PlayGroundType type;
    private boolean isAvailable = true;

    @OneToMany(mappedBy = "playGround")
    @JsonIgnore
    private List<Reservation> reservations;

}
