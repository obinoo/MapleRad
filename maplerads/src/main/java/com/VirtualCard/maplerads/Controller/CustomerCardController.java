package com.VirtualCard.maplerads.Controller;

import com.VirtualCard.maplerads.Repository.CustomerCardRepository;
import com.VirtualCard.maplerads.Repository.CustomerRepository;
import com.VirtualCard.maplerads.Service.CustomerCardService;
import com.VirtualCard.maplerads.dao.request.CustomerCardRequest;
import com.VirtualCard.maplerads.dao.request.FundRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/customers")
public class CustomerCardController {

    @Autowired(required = false)
    CustomerCardService customerCardService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerCardRepository customerCardRepository;

    @PostMapping("/customerCard")
    public ResponseEntity<?> createCard(@Valid @RequestBody(required = false) CustomerCardRequest customerCardRequest) throws IOException, InterruptedException {
        try {

            String fund = customerCardService.createCard(customerCardRequest);

            return ResponseEntity.ok(fund);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/fund")
    public ResponseEntity<?> fundCard(@Valid @RequestBody
                                                  FundRequest fundRequest) {
        try {

            ResponseEntity<String> fund = customerCardService.fundCard(fundRequest);
            return new ResponseEntity<>(fund, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}