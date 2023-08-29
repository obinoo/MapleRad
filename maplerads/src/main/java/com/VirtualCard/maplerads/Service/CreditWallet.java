package com.VirtualCard.maplerads.Service;


import com.VirtualCard.maplerads.Repository.WalletCreditRepo;
import com.VirtualCard.maplerads.dao.request.CreditWalletRequest;
import com.VirtualCard.maplerads.dao.response.CreditWalletResponse;
import com.VirtualCard.maplerads.Entities.WalletCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;


@Service
public class CreditWallet {

    @Autowired
    WalletCreditRepo walletCreditRepo;

    @Value("${secret.key}")
    private String secret;

    public void creditWalletTest(CreditWalletRequest creditWalletRequest) throws IOException, InterruptedException {

        final String CREDIT = "https://sandbox.api.maplerad.com/v1/test/wallet/credit";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secret);

        ResponseEntity<CreditWalletResponse> response = restTemplate.exchange(CREDIT, HttpMethod.POST,
                new HttpEntity<>(creditWalletRequest, headers), CreditWalletResponse.class);

        System.out.println(response.getBody());
        WalletCredit walletCredit = converting(Objects.requireNonNull(response.getBody()));
        walletCreditRepo.save(walletCredit);

        System.out.println(walletCredit);
        new ResponseEntity<>(response.getStatusCode(), HttpStatus.OK);

    }

    public WalletCredit converting(CreditWalletResponse creditWalletResponse){

        WalletCredit walletCredit = new WalletCredit();

        walletCredit.setStatus(creditWalletResponse.getStatus());
        walletCredit.setMessage(creditWalletResponse.getMessage());

        return walletCredit;
    }
}
