package org.demir.dormitory.repository;

import org.demir.dormitory.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends BaseRepository<Employee,Long> {
    Optional<Employee> findByName(String employeeName);
}
