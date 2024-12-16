package org.ahmed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    /**
     * @Transient
     * -> Mark fields as @Transient to exclude them from persistence entirely,
     * ensuring they are not audited or logged.
     * -> If you want the field to be excluded from database persistence,
     * but it can still be used in APIs.
     * -> Example: Temporary fields that are used only in-memory calculations or helper properties.
     *
     *  @JsonIgnore
     *  -> If the field needs to be persisted in the database
     *  but should not be exposed in API responses or requests.
     *  -> Example: Sensitive fields like passwords or internal flags.
     */
    @JsonIgnore
    @Transient
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    @ToString.Exclude
    private Role role;

}
