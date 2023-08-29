package com.VirtualCard.maplerads.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Addresses {

    private String street;
    private String city;
    private String state;
    @JsonProperty("postal_code")
    private String postalCode;
    private String country;


}
