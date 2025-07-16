package org.ahmed.hibernate.service;

import lombok.RequiredArgsConstructor;
import org.ahmed.hibernate.models.entitites.Address;
import org.ahmed.hibernate.models.dto.AddressDto;
import org.ahmed.hibernate.models.entitites.Department;
import org.ahmed.hibernate.models.entitites.Student;
import org.ahmed.hibernate.models.dto.StudentDto;
import org.ahmed.hibernate.repo.DepartmentRepo;
import org.ahmed.hibernate.repo.StudentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepository;
    private final DepartmentRepo departmentRepo;

    @Transactional(readOnly = true) // Ensure session is open
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // New method to save student and assign to department
    @Transactional
    public Student saveStudentWithDepartment(Student student, Long departmentId) {
        // Fetch the Department by ID
        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + departmentId));

        student.setDepartment(department); // Set the department on the student
        return studentRepository.save(student);
    }

    // New method to fetch student with initialized address
    @Transactional(readOnly = true)
    public StudentDto getStudentWithAddressInitialized(Long id) {
        Student student = studentRepository.findById(id)
                .orElse(null); // Or throw NotFoundException

        if (student == null) {
            return null;
        }

        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getName());

        // Accessing student.getAddress() here will initialize the proxy if it's lazy
        // because this code is within a @Transactional method.
        Address address = student.getAddress();
        if (address != null) {
            AddressDto addressDto = new AddressDto();
            addressDto.setState(address.getState());
            addressDto.setCity(address.getCity());
            studentDto.setAddress(addressDto);
        }

        return studentDto;
    }
}
