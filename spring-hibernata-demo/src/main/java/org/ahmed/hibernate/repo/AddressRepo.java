package org.ahmed.hibernate.repo;

import org.ahmed.hibernate.models.entitites.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
}
