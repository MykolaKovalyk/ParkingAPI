package com.lpnu.iot.parking.structure.client;

import com.lpnu.iot.parking.resources.Client;
import com.lpnu.iot.parking.resources.ClientCard;
import com.lpnu.iot.parking.structure.client_card.ClientCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.Date;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientCardRepository clientCardRepository;


    public Client getClient(
            Long clientId
    ) {
        return clientRepository.findById(clientId);
    }

    public Client addClient(
            Long shopId,
            String name,
            String email,
            String phoneNumber
    ) {

        var currentDate = new Date();

        var newClientCard = new ClientCard(
                name + "'s user card",
                -1L,
                "UserCard",
                "",
                shopId,
                currentDate,
                (Date) currentDate.clone(),
                0);

        var newClient = new Client(name, email, phoneNumber, -1L);


        newClientCard.ownerClientId = clientRepository.save(newClient).id;
        newClient.cardId = clientCardRepository.save(newClientCard).id;

        return newClient;
    }
}
