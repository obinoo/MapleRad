package com.VirtualCard.maplerads.Controller;

import com.VirtualCard.maplerads.Service.CardDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")

public class cardDetailsController {

    @Autowired
    private CardDetailsService cardDetailsService;

    @GetMapping("/cardDetails")
    public ResponseEntity<?> getCardDetail() {

        try {
            ResponseEntity<?> details = cardDetailsService.getCardDetails();
            return ResponseEntity.ok(details);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
