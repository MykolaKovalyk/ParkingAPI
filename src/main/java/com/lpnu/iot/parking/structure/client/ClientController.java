package com.lpnu.iot.parking.structure.client;

import com.lpnu.iot.parking.resources.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(path =
    "clients/{clientId}")
    public Client getClient(
            @PathVariable Long clientId
    ) {
        return clientService.getClient(clientId);
    }

    @PostMapping(path =
    "shops/{shopId}/clients/add/{name}")
    public Client addClient(
            @PathVariable Long shopId,
            @PathVariable String name,
            @RequestParam String email,
            @RequestParam String phoneNumber
    ) {
        return clientService.addClient(
                shopId,
                name,
                email,
                phoneNumber
        );
    }
}
