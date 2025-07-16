package org.ahmed.hibernate.controller;

import lombok.RequiredArgsConstructor;
import org.ahmed.hibernate.models.entitites.Address;
import org.ahmed.hibernate.models.entitites.Student;
import org.ahmed.hibernate.models.dto.StudentDto;
import org.ahmed.hibernate.service.AddressService;
import org.ahmed.hibernate.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DataController {

    private final StudentService studentService;
    private final AddressService addressService;

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/student/{id}/with-address")
    public StudentDto getStudentWithAddress(@PathVariable Long id) {
       return studentService.getStudentWithAddressInitialized(id);
    }

    @GetMapping("/address")
    public List<Address> getAddresses(){
        return addressService.getAddresses();
    }
}
