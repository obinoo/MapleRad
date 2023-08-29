package com.VirtualCard.maplerads.dao.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreditWalletRequest {

    private int amount;
    private String currency;
}
