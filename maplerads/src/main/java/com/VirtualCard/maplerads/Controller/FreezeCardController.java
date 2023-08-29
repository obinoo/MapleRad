package com.VirtualCard.maplerads.Controller;

import com.VirtualCard.maplerads.Service.FreezeCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/customers")

public class FreezeCardController {

    @Autowired
    FreezeCardService freeze;

    @PatchMapping("/{id}/freeze")
    public void freezeCard(@PathVariable Map<String, String> parameter) throws IOException, InterruptedException {
        try {

            String id = parameter.get("id");
            freeze.freezeCard(id);
            new ResponseEntity<>("Card disabled", HttpStatus.PROCESSING);
        }catch (Exception e){
            e.printStackTrace();

            new ResponseEntity<>("Card not Frozen", HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/{id}/unfreeze")
    public void unFreezeCard(@PathVariable("id") String parameters) throws IOException, InterruptedException {
        try {

            freeze.unFreezing(parameters);
            new ResponseEntity<>("Card enabled", HttpStatus.PROCESSING);
        }catch (Exception e){
            e.printStackTrace();

            new ResponseEntity<>("Card still disabled", HttpStatus.BAD_REQUEST);
        }
    }
}
