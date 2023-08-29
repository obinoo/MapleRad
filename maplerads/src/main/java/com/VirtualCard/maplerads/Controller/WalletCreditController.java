package com.VirtualCard.maplerads.Controller;

import com.VirtualCard.maplerads.Service.CreditWallet;
import com.VirtualCard.maplerads.dao.request.CreditWalletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/customers")

public class WalletCreditController {

    @Autowired
    CreditWallet creditWallet;

    @PostMapping("/wallet/credit")
    public ResponseEntity<?> creditWalletTest(@Valid @RequestBody CreditWalletRequest creditWalletRequest) throws IOException,
            InterruptedException {
        creditWallet.creditWalletTest(creditWalletRequest);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
