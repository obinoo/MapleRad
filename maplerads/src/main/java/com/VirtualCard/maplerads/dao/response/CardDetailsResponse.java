package com.VirtualCard.maplerads.dao.response;

import com.VirtualCard.maplerads.Model.CustomerCardDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class CardDetailsResponse {

    private Boolean status;
    private String message;
    private CustomerCardDetails data;

}
