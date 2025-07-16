package org.ahmed.hibernate.models.entitites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true) // Added orphanRemoval
    private List<Student> students = new ArrayList<>();

    // Custom constructor for convenience when creating a new Department
    public Department(String name) {
        this.name = name;
        this.students = new ArrayList<>(); // Initialize to avoid NullPointerException
    }
}
