package com.lpnu.iot.parking.structure.client;

import com.lpnu.iot.parking.structure.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClientController {

    @Autowired
    ClientService clientService;
}
