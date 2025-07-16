package org.ahmed.hibernate.repo;

import org.ahmed.hibernate.models.entitites.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
}
