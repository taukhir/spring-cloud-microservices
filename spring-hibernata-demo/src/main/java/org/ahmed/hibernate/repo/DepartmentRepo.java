package org.ahmed.hibernate.repo;

import org.ahmed.hibernate.models.entitites.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
