package org.ahmed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public class Role extends AuditableEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Role is required")
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<User> users;

}
