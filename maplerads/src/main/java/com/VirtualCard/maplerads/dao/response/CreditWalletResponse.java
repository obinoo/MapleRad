package com.VirtualCard.maplerads.dao.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreditWalletResponse {

    private Boolean status;
    private String message;
}
