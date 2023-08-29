package com.VirtualCard.maplerads.Service;

import com.VirtualCard.maplerads.Entities.*;
import com.VirtualCard.maplerads.Repository.CustomerCardRepository;
import com.VirtualCard.maplerads.Repository.CustomerRepository;
import com.VirtualCard.maplerads.Repository.FundRepository;
import com.VirtualCard.maplerads.dao.request.CustomerCardRequest;
import com.VirtualCard.maplerads.dao.request.FundRequest;
import com.VirtualCard.maplerads.dao.response.CustomerCardResponse;
import com.VirtualCard.maplerads.dao.response.FundResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service

public class CustomerCardService {

    @Value("${secret.key}")
    private String secret;

    @Autowired
    CustomerCardRepository customerCardRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    FundRepository fundRepository;

    @Autowired
    Gson gson;

    public String createCard(CustomerCardRequest customerCardRequest) {

        String reference = null;
        try {
            String customerId = customerRepository.findByCustomerId();

//           boolean hasExistingCard = Boolean.parseBoolean(customerCardRepository.existsByCustomerId(customerId));
//            if (hasExistingCard) {
//                return ("Customer already has a card.");
//            }

            if (customerCardRequest != null) {
                customerCardRequest.setCustomerId(customerId);
            }

            String uri = "https://sandbox.api.maplerad.com/v1/issuing";

            final RestTemplate restTemplate = new RestTemplate();
            //solved my redirecting issue.
            final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            final org.apache.http.client.HttpClient httpClient = HttpClientBuilder.create()
                    .setRedirectStrategy(new LaxRedirectStrategy())
                    .build();

            restTemplate.setRequestFactory(factory);

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(secret);

            String requestBody = gson.toJson(customerCardRequest);

            System.out.println("my request - " + requestBody);


            CustomerCardResponse response = restTemplate.postForObject(uri,
                    new HttpEntity<>(requestBody, headers), CustomerCardResponse.class);

            System.out.println(response);

            assert response != null;
            reference = response.getData().getReference();
            System.out.println("my reference" + reference);

            new ResponseEntity<>("success", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            new ResponseEntity<>("Error, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return reference;
    }



    public ResponseEntity<String> fundCard(FundRequest fundRequest) throws IOException, InterruptedException {

        String id = getCardIdFromDatabase();

        String url = "https://sandbox.api.maplerad.com/v1/issuing/ " + id + "/fund";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secret);


        System.out.println("my request - "+fundRequest);

        ResponseEntity<FundResponse> response = restTemplate.exchange(url, HttpMethod.POST
                , new HttpEntity<>(fundRequest, headers), FundResponse.class);

        System.out.println(response.getBody());

        CardTransaction fund = MapResponseToEntity(Objects.requireNonNull(response.getBody()));
        fundRepository.save(fund);

        System.out.println(fund);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    public CardTransaction MapResponseToEntity(FundResponse fundResponse) {

        CardTransaction fundCard = new CardTransaction();

        fundCard.setId(fundResponse.getData().getId());
        fundCard.setCreatedAt(Instant.now());

        return fundCard;
    }

    private String getCardIdFromDatabase() {

        Optional<CustomerCard> latestCardOptional = customerCardRepository.findTopByOrderByCreatedAtDesc();
        if (latestCardOptional.isPresent()) {
            return latestCardOptional.get().getReference();
        } else {
            throw new RuntimeException("No card found in the database.");
        }
    }

}