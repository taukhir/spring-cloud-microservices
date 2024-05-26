package org.ahmed.repository;

import org.ahmed.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom method to delete employees by their IDs
    void deleteEmployeesByIdIn(List<Long> ids);
}
