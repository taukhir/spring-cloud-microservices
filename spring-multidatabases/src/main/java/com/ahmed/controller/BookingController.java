package com.ahmed.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmed.model.booking.Booking;
import com.ahmed.model.user.User;
import com.ahmed.repo.booking.BookingRepo;
import com.ahmed.repo.user.UserRepo;

import jakarta.annotation.PostConstruct;

@RestController
//@RequestMapping("/booking-service")
public class BookingController {

	private final UserRepo userRepo;

	private final BookingRepo bookingRepo;

	BookingController(BookingRepo bookingRepo, UserRepo userRepo) {
		this.bookingRepo = bookingRepo;
		this.userRepo = userRepo;
	}

	@PostMapping("/bookseat")
	public String bookSeat() {
		return "Successfully seats are booked";
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@PostConstruct
	public void addData2Db() {

		userRepo.saveAll(Stream.of(new User(1, "ahmed"), new User(2, "jhon")).collect(Collectors.toList()));
		bookingRepo
				.saveAll(Stream.of(new Booking(1, 1, "ahmed"), new Booking(2, 2, "jhon")).collect(Collectors.toList()));

	}

}
