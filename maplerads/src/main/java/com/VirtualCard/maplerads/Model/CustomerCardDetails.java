package com.VirtualCard.maplerads.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerCardDetails {

    @NotEmpty
    private String id;
    @NotEmpty
    @JsonProperty("name")
    private String name;
    @NotEmpty
    @JsonProperty("card_number")
    private String cardNumber;
    @NotEmpty
    @JsonProperty("masked_pan")
    private String maskedPan;
    @NotEmpty
    private String expiry;
    @NotEmpty
    private String cvv;
    @NotEmpty
    private String status;
    @NotEmpty
    private String type;
    @NotEmpty
    private String issuer;
    @NotEmpty
    private String currency;
    @NotEmpty
    private double balance;
    @NotEmpty
    @JsonProperty("balance_updated_at")
    private String balanceUpdatedAt;
    @NotEmpty
    @JsonProperty("autoApprove")
    private boolean auto_approve;
    @NotEmpty
    private Addresses address;
    @NotEmpty
    @JsonProperty("created_at")
    private String createdAt;
    @NotEmpty
    @JsonProperty("updated_at")
    private String updatedAt;

}
