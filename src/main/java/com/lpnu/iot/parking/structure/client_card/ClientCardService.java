package com.lpnu.iot.parking.structure.client_card;

import com.lpnu.iot.parking.resources.ClientCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ClientCardService {

    @Autowired
    ClientCardRepository clientCardRepository;

    public ClientCard getClientCard(Long cardId) {
        return clientCardRepository.findById(cardId);
    }
}
