package com.VirtualCard.maplerads.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service

public class FreezeCardService {

    @Value("${secret.key}")
    private String secret;

    public void freezeCard(String id) throws IOException, InterruptedException {

        String url = "https://sandbox.api.maplerad.com/v1/issuing/" + id + "/freeze";

        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
       // requestFactory.setReadTimeout(600000);
        requestFactory.setConnectTimeout(600000);

         restTemplate.setRequestFactory(requestFactory);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(secret);

        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.PATCH
                , new HttpEntity<>(httpHeaders), String.class);

        System.out.println(response.getBody());

        new ResponseEntity<>(response.getStatusCode(), HttpStatus.OK);

    }


    public void unFreezing(String id) throws IOException, InterruptedException {

        String urls = "https://sandbox.api.maplerad.com/v1/issuing/" + id + "/unfreeze";

        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        // requestFactory.setReadTimeout(600000);
        requestFactory.setConnectTimeout(600000);

        restTemplate.setRequestFactory(requestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secret);

        ResponseEntity<?> response = restTemplate.exchange(urls, HttpMethod.PATCH
                , new HttpEntity<>(headers), String.class);

        System.out.println(response.getBody());

        new ResponseEntity<>(response.getStatusCode(), HttpStatus.OK);

    }
}