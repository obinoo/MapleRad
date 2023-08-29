package com.VirtualCard.maplerads.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {

    @PostMapping("/webhook")
    public void webhook(){

    }
}
