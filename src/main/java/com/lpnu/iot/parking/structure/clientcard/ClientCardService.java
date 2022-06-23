package com.lpnu.iot.parking.structure.clientcard;

import com.lpnu.iot.parking.resources.ClientCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientCardService {

    @Autowired
    private ClientCardRepository clientCardRepository;

    public ClientCard getClientCard(Long cardId) {
        var found = clientCardRepository.findById(cardId);
        if (found == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "client card with id " + cardId + "doesn't exist.");
        }

        return found;
    }
}
