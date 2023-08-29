package com.VirtualCard.maplerads.Controller;

import com.VirtualCard.maplerads.Service.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/customers")

public class TransactionListController {

    @Autowired
    Transaction transaction;

    @RequestMapping(value ="/transactions", method = RequestMethod.GET)
    public Object getAllTransactions() throws IOException, InterruptedException {

        try {

            ResponseEntity<?> transactionData = transaction.getTransaction();
            return  ResponseEntity.ok(transactionData);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
