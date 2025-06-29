package com.khan.code.taskify_rest_api.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterRequest {

    @NotEmpty(message = "First name is Mandatory")
    @Size(min = 3, max = 30, message = "First name is must be at least 30 characters")
    private String firstName;

    @NotEmpty(message = "Last name is Mandatory")
    @Size(min = 3, max = 30, message = "Last name is must be at least 30 characters")
    private String lastName;

    @NotEmpty(message = "Email is Mandatory")
    @Email(message = "Invalid Email format")
    private String email;

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 3, max = 30, message = "Password is must be at least 5 characters")
    private String password;
}
