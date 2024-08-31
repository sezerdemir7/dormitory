package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends BaseRepository<Employee, Long> {
    Optional<Employee> findByName(String employeeName);
}
