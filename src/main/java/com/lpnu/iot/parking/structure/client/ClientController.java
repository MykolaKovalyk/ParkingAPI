package com.lpnu.iot.parking.structure.client;

import com.lpnu.iot.parking.resources.Client;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

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
            @RequestParam(required = false) String email,
            @RequestParam String phone
    ) {
        return clientService.addClient(
                shopId,
                name,
                email,
                phone
        );
    }
}
