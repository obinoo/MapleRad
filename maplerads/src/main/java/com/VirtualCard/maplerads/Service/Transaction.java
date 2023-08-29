package com.VirtualCard.maplerads.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service

public class Transaction {

    @Value("${secret.key}")
    private String secret;

    public ResponseEntity<?> getTransaction() throws IOException, InterruptedException {

        String url = "https://sandbox.api.maplerad.com/v1/transactions";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secret);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers),String.class);

        System.out.println(response.getBody() + response.getStatusCode());

        return ResponseEntity.ok(response);

    }

}