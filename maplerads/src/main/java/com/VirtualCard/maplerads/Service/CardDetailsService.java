package com.VirtualCard.maplerads.Service;

import com.VirtualCard.maplerads.Entities.Customer;
import com.VirtualCard.maplerads.Entities.CustomerCard;
import com.VirtualCard.maplerads.Repository.CustomerCardRepository;
import com.VirtualCard.maplerads.Repository.CustomerRepository;
import com.VirtualCard.maplerads.dao.request.CustomerCardRequest;
import com.VirtualCard.maplerads.dao.response.CardDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

@Service

public class CardDetailsService {

    @Autowired
    CustomerCardRepository customerCardRepository;
    @Autowired
    CustomerCardService customerCardService;
    @Autowired
    CustomerRepository customerRepository;

    @Value("${secret.key}")
    private String secret;

    public ResponseEntity<?> getCardDetails() throws IOException, InterruptedException {
        CustomerCardRequest customerCardRequest = new CustomerCardRequest();
        customerCardRequest.setType("VIRTUAL");
        customerCardRequest.setAutoApprove(true);
        customerCardRequest.setCurrency("USD");
        customerCardRequest.setBrand("MASTERCARD");

        String id = customerCardService.createCard(customerCardRequest);
        String url = "https://sandbox.api.maplerad.com/v1/issuing/" + id;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity <CardDetailsResponse> response1 = restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(headers),
                CardDetailsResponse.class);

            CustomerCard getCard = mapToEntity(Objects.requireNonNull(response1.getBody()));
            System.out.println(getCard);

            customerCardRepository.save(getCard);

        return ResponseEntity.ok(getCard);

    }

    public CustomerCard mapToEntity(CardDetailsResponse customerCardDetails) {
        CustomerCardRequest customerCardRequest = new CustomerCardRequest();
        customerCardRequest.setType("VIRTUAL");
        customerCardRequest.setAutoApprove(true);
        customerCardRequest.setCurrency("USD");
        customerCardRequest.setBrand("MASTERCARD");
        String reference = customerCardService.createCard(customerCardRequest);

        CustomerCard customerCard = new CustomerCard();

        customerCard.setId(customerCardDetails.getData().getId());
        customerCard.setCardNumber(customerCardDetails.getData().getCardNumber());
        customerCard.setMaskedPan(customerCardDetails.getData().getMaskedPan());
        customerCard.setExpiry(customerCardDetails.getData().getExpiry());
        customerCard.setCvv(customerCardDetails.getData().getCvv());
        customerCard.setType(customerCardDetails.getData().getType());
        customerCard.setIssuer(customerCardDetails.getData().getIssuer());
        customerCard.setName(customerCardDetails.getData().getName());
        customerCard.setReference(reference);
        customerCard.setCreatedAt(Instant.now());

        return customerCard;
    }
    }