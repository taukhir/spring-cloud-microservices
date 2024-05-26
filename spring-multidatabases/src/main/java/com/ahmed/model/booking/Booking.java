package com.ahmed.model.booking;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "seat")
	private int seatNumber;

	@Column(name = "user")
	private String userName;

}
