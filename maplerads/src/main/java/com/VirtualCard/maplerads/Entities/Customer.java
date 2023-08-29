package com.VirtualCard.maplerads.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Customer  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "customerId" , unique = true, nullable = false, columnDefinition = "CHAR(36)")
    @SerializedName("id")
    private String customerId;

    @Column(nullable = false)
    @JsonProperty("first_name")
    private String firstName;

    @Column(nullable = false)
    @JsonProperty("last_name")
    private String lastName;

    @Column(nullable = false)
    @SerializedName("email")
    private String email;

    @Column(nullable = false)
    @SerializedName("country")
    private String country;

    @Column(nullable = false)
    @SerializedName("tier")
    private int tier;

}
