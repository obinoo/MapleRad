package com.VirtualCard.maplerads.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDatas {

    @NotEmpty
    private String id;
    @NotEmpty
    @JsonProperty("first_name")
    private String firstName;
    @NotEmpty
    @JsonProperty("last_name")
    private String lastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String country;
    private String status;
    @NotEmpty
    private int tier;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
}