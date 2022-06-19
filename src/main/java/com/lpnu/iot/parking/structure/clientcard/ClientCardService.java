package com.lpnu.iot.parking.structure.clientcard;

import com.lpnu.iot.parking.resources.ClientCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientCardService {

    @Autowired
    private ClientCardRepository clientCardRepository;

    public ClientCard getClientCard(Long cardId) {
        return clientCardRepository.findById(cardId);
    }
}
