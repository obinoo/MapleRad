package com.VirtualCard.maplerads.dao.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WithdrawRequest {

    private int amount;
}
