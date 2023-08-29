package com.VirtualCard.maplerads.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class WalletCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wallet_id", nullable = false)
    private Long walletId;

    private Boolean status;
    @Column(nullable = false)
    private String message;

}
