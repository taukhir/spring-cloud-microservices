package org.ahmed.hibernate;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.ahmed.hibernate.models.entitites.Address;
import org.ahmed.hibernate.models.entitites.Department;
import org.ahmed.hibernate.models.entitites.Student;
import org.ahmed.hibernate.service.DepartmentService;
import org.ahmed.hibernate.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringHibernateDemoApplication {

	private final StudentService studentService;
	private final DepartmentService departmentService;

	@PostConstruct
	public void init() {
		// 1. Create Departments first
		List<Department> departments = new ArrayList<>();
		departments.add(departmentService.saveDepartment("Computer Science")); // ID 1
		departments.add(departmentService.saveDepartment("Electrical Engineering")); // ID 2
		departments.add(departmentService.saveDepartment("Mechanical Engineering")); // ID 3

		// 2. Create Students and assign them to Departments
		IntStream.rangeClosed(1, 15).forEach(i -> { // Let's create more students, 15 total
			Address address = new Address();
			address.setCity("Blore-" + i);
			address.setState("State-" + i); // Added street for better address realism
			// Note: If Address is also bidirectional with Student, you might need address.setStudent(student) here.

			Student student = new Student();
			student.setName("Name-" + i);
			student.setAddress(address); // Set the address

			// Assign students to departments in a round-robin fashion
			// (i-1) % departments.size() ensures we cycle through available departments
			Department assignedDepartment = departments.get((i - 1) % departments.size());

			studentService.saveStudentWithDepartment(student, assignedDepartment.getId());

		});

	}

	public static void main(String[] args) {
	SpringApplication.run(SpringHibernateDemoApplication.class, args);
	}

}
