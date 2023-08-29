package com.VirtualCard.maplerads.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customerCard")

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false,updatable = false,nullable = false)
    private Long cardId;

    private String reference;

    @Column(nullable = false)
    private String id;
    @JsonProperty("name")
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @JsonProperty("card_number")
    private String cardNumber;
    @Column(nullable = false)
    @JsonProperty("masked_pan")
    private String maskedPan;
    @Column(nullable = false)
    private String expiry;
    @Column(nullable = false)
    private String cvv;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String issuer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_customer_id", referencedColumnName = "customerId")
    private Customer customer;
    @Column(nullable = false)
    private Instant createdAt;

}
