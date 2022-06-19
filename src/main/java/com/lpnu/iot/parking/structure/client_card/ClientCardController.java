package com.lpnu.iot.parking.structure.client_card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClientCardController {

    @Autowired
    ClientCardService clientCardService;
}
