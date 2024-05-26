package com.ahmed.repo.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahmed.model.booking.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

}
