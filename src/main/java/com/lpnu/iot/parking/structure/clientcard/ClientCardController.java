package com.lpnu.iot.parking.structure.clientcard;

import com.lpnu.iot.parking.resources.ClientCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientCardController {

    @Autowired
    private ClientCardService clientCardService;

    @GetMapping(path = "client-cards/{cardId}")
    public ClientCard getClientCard(@PathVariable Long cardId) {
        return clientCardService.getClientCard(cardId);
    }
}
