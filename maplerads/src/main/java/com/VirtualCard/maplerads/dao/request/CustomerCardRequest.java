package com.VirtualCard.maplerads.dao.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerCardRequest {

    @SerializedName("customer_id")
    private String customerId;
    private String currency;
    private String type;
    @SerializedName("auto_approve")
    private Boolean autoApprove;
    private String brand;


}
