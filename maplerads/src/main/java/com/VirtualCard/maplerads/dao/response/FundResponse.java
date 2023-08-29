package com.VirtualCard.maplerads.dao.response;

import com.VirtualCard.maplerads.Model.FundData;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FundResponse {

    private Boolean status;
    @NotEmpty
    private String message;
    @NotEmpty
    private FundData data;
}
