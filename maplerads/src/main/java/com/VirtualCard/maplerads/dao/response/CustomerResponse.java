package com.VirtualCard.maplerads.dao.response;

import com.VirtualCard.maplerads.Model.CustomerDatas;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomerResponse {

    @Column(insertable = false, updatable = false)

    private Boolean status;
    private String message;
    private CustomerDatas data;

}
