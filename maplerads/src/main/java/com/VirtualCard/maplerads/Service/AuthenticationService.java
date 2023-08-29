package com.VirtualCard.maplerads.Service;


import com.VirtualCard.maplerads.Entities.Customer;
import com.VirtualCard.maplerads.Exception.EmailNotFound;
import com.VirtualCard.maplerads.Repository.CustomerRepository;
import com.VirtualCard.maplerads.advice.CustomExceptionHandler;
import com.VirtualCard.maplerads.Entities.Role;
import com.VirtualCard.maplerads.Entities.User;
import com.VirtualCard.maplerads.Exception.EmailExistsException;
import com.VirtualCard.maplerads.Repository.UserRepository;
import com.VirtualCard.maplerads.dao.request.CustomerRequest;
import com.VirtualCard.maplerads.dao.request.SignInRequest;
import com.VirtualCard.maplerads.dao.response.AuthenticationResponse;
import com.VirtualCard.maplerads.dao.response.CustomerResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

@Service

public class AuthenticationService {

    @Autowired
    UserRepository repository;

    @Autowired
     PasswordEncoder passwordEncoder;

    @Autowired
     AuthenticationManager authenticationManager;

    @Autowired
     JwtService jwtService;

    @Autowired
     CustomUserDetails customUserDetails;

    @Autowired(required = false)
    CustomerRepository customerRepository;

    @Autowired(required = false)
    Gson gson;

    @Value("${secret.key}")
    private String secretKey;


    public AuthenticationResponse register(CustomerRequest customerRequest) throws CustomExceptionHandler,
            EmailExistsException, IOException, InterruptedException {

        RestTemplate restTemplate = new RestTemplate();

        Optional<User> stored = repository.findByEmail(customerRequest.getEmail());

        if (stored.isPresent()){
            throw new EmailExistsException("Email already exists!!");
        }

        var user1 = User.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .password(passwordEncoder.encode(customerRequest.getPassword()))
                .role(Role.USER)
                .build();

      repository.save(user1);

        String url = "https://sandbox.api.maplerad.com/v1/customers/enroll";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secretKey);

        String requestBody = gson.toJson(customerRequest);

        System.out.println("my request - "+requestBody);

        CustomerResponse response = restTemplate.postForObject(url, new HttpEntity<>(requestBody, headers), CustomerResponse.class);

       // converting response body toJsonObject
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.valueToTree(response);
        Customer customers = mapResponseToEntity(jsonResponse);

        System.out.println(response);
        customerRepository.save(customers);

        new  ResponseEntity<>(response, HttpStatus.CREATED);

        return  AuthenticationResponse.builder()
                .build();

    }

//    public JsonObject convertResponseToJson(CustomerResponse response) {
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(response);
//        JsonParser jsonParser = new JsonParser();
//        return jsonParser.parse(jsonString).getAsJsonObject();
//    }

    public Customer mapResponseToEntity(JsonNode jsoResponse){
        JsonNode dataNode = jsoResponse.path("data");
        Customer customers = new Customer();

        customers.setCountry(dataNode.path("country").asText());
        customers.setEmail(dataNode.path("email").asText());
        customers.setCustomerId(dataNode.path("id").asText());
        customers.setFirstName(dataNode.path("first_name").asText());
        customers.setLastName(dataNode.path("last_name").asText());
        customers.setTier(dataNode.path("tier").asInt());
        return customers;
    }

    public AuthenticationResponse signIn(SignInRequest request) throws EmailNotFound {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                                     request.getPassword()));
//        var user1 = repository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new EmailNotFound("Invalid Email and Password!!"));
       final UserDetails userDetails = customUserDetails.loadUserByUsername(request.getEmail());
        var jwt = jwtService.generateToken(userDetails.getUsername());

        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
