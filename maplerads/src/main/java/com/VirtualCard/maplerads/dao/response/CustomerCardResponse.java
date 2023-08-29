package com.VirtualCard.maplerads.dao.response;

import com.VirtualCard.maplerads.Model.CardData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerCardResponse {

   private String status;
   private String message;
   private CardData data;
}
