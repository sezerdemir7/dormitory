package org.demir.dormitory.dto.request;

import org.demir.dormitory.entity.enumType.StaffRole;

import java.util.Set;

public record StaffUpdateRequest(Long id, String name, String surname, String username, Set<StaffRole> authorities) {
}
