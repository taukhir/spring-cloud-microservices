package com.ahmed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ahmed.repo.booking.BookingRepo;
import com.ahmed.repo.user.UserRepo;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
//@ComponentScan(basePackages = "{com.ahmed.*}")
public class SpringMultiDatabases implements CommandLineRunner {

	@Autowired
	UserRepo userRepo;

	@Autowired
	BookingRepo bookingRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringMultiDatabases.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Testing repo started.......");
		System.out.println(userRepo.findAll());
		System.out.println("Testing repo ended.........");

	}

}
