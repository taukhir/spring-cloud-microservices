package org.ahmed.hibernate.models.dto;

import lombok.Data;

@Data
public class StudentDto {

    private String name;
    private AddressDto address; // Reference the AddressDto

}
