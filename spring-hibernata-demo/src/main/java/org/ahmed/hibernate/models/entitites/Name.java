package org.ahmed.hibernate.models.entitites;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Name {

    private String fName;
    private String lName;
}
