package org.demir.dormitory.dto.request;

import org.demir.dormitory.entity.enumType.StaffRole;

import java.util.Set;

public record StaffRequest(String name, String surname, String username,
                           String password,  Set<StaffRole> authorities) {
}
