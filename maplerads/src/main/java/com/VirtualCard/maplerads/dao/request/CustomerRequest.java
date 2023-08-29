package com.VirtualCard.maplerads.dao.request;

import com.VirtualCard.maplerads.Model.Address;
import com.VirtualCard.maplerads.Model.Identity;
import com.VirtualCard.maplerads.Model.Phone;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomerRequest {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabets")
    @SerializedName("first_name")
    private String firstName;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabets")
    @SerializedName("last_name")
    private String lastName;

    @NotEmpty(message = "Your Email")
    @Email(message = "Provide a valid email")
    private String email;

    @NotEmpty
    @Size(min = 8, message = "Password should contain at least 8 characters")
    private String password;

    @NotEmpty
    @SerializedName("identification_number")
    private String identificationNumber;

    @NotEmpty
    @Column(nullable = false)
    private String dob;
    private Phone phone;
    private Address address;
    private Identity identity;
}
