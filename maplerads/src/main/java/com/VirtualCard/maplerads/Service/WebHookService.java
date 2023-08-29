package com.VirtualCard.maplerads.Service;

import org.springframework.stereotype.Service;

import java.util.Base64;


@Service

public class WebHookService {


    public void getWebhookSignature(String svixId, String svixTimestamp, String body){

        String secret = "";
        String signedContent = svixId + "." + svixTimestamp + "." + body;

//        byte[] secretBytes = Base64.getDecoder().decode(secret.split('_')[1], )
    }

}