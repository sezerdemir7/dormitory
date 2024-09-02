package org.demir.dormitory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.demir.dormitory.common.BasePerson;
import org.demir.dormitory.entity.enumType.StaffRole;
import org.demir.dormitory.listener.EntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EntityListeners(EntityListener.class)
public class Staff extends BasePerson implements UserDetails {

    @Column(unique = true)
    private String username;

    private String password;


    @OneToOne(mappedBy = "staff")
    private Hall hall;


    private boolean accountNonExpired;
    private boolean isEnabled;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    @ElementCollection(targetClass = StaffRole.class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<StaffRole> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

}