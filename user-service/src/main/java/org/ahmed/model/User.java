package org.ahmed.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@ToString
public class User extends AuditableEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private int age;

    private String email;

    private String phone;

    private String gender;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    @ToString.Exclude
    private Role role;

}
