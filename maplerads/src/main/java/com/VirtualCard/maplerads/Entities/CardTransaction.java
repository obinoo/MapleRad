package com.VirtualCard.maplerads.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class CardTransaction{
    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fundCardId;

    @NotEmpty
    @Column(nullable = false)
    private String id;

    @NotEmpty
    private Instant createdAt;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_card_card_id")
    private CustomerCard customerCard;
}
