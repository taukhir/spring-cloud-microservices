package org.ahmed.hibernate.models.entitites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.HashSet;
import java.util.Set;

@DynamicInsert
@DynamicUpdate
@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    private Name name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")  // FK column in Student table
    private Address address;

    // This is the "Many" side of the ManyToOne relationship with Department
    // @ManyToOne owns the foreign key in the 'student' table (department_id)
    // FetchType.LAZY is often the default, but explicitly declaring it is good practice.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id") // The foreign key column in the 'student' table
    @ToString.Exclude // Prevent infinite recursion in Lombok's toString()
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "student_course", // Name of the join table
            joinColumns = @JoinColumn(name = "student_id"), // Column in join table referring to Student
            inverseJoinColumns = @JoinColumn(name = "course_id") // Column in join table referring to Course
    )
    private Set<Course> courses = new HashSet<>();


}
