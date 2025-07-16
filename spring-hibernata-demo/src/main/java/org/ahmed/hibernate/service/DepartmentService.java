package org.ahmed.hibernate.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.ahmed.hibernate.models.entitites.Department;
import org.ahmed.hibernate.repo.DepartmentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    @NonNull
    private DepartmentRepo departmentRepo;

    @Transactional(readOnly = true) // Good practice for read-only operations
    public List<Department> getDepartments(){
        return departmentRepo.findAll();
    }

    @Transactional // Ensure write operations are transactional
    public Department saveDepartment(String name){
        // Use the custom constructor for Department
        return departmentRepo.save(new Department(name));
    }

}
