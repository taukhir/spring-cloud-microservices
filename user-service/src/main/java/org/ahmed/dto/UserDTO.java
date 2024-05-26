package org.ahmed.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 2)
    private String firstName;

    @NotBlank(message = "Name is required")
    private String lastName;

    @Min(value = 18, message = "Min age is 18")
    @Max(value = 65, message = "Max age is 65")
    private int age;

    @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    private String email;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message = "Mobile number is invalid")
    private String phone;

    private String gender;

    private String address;

    private String roleName;

}
