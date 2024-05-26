package com.ahmed.model.user;

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
@Table(name = "user")
public class User {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

}
