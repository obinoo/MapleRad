package com.VirtualCard.maplerads.dao.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SignInRequest {

    @NotEmpty(message = "Input email")
    @Email(message = "Provide a valid email")
    private String email;

    @NotEmpty(message = "Input password")
    private String password;
}
