package com.VirtualCard.maplerads.Model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CardData {

    @NotEmpty
    private String reference;

}