package com.lpnu.iot.parking.structure.client;

import com.lpnu.iot.parking.resources.Client;
import com.lpnu.iot.parking.resources.ClientCard;
import com.lpnu.iot.parking.structure.clientcard.ClientCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientCardRepository clientCardRepository;


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
    )  throws Exception {

        var currentDate = new Date();

        var newClientCard = new ClientCard(
                name + "'s user card",
                clientRepository.getIdSequence() + 1,
                "UserCard",
                "",
                shopId,
                currentDate,
                (Date) currentDate.clone(),
                0);

        var newClient = new Client(
                name,
                email,
                phoneNumber,
                clientCardRepository.getIdSequence() + 1);


        clientRepository.save(newClient);
        clientCardRepository.save(newClientCard);

        clientRepository.writeDataToFile();
        clientCardRepository.writeDataToFile();

        return newClient;
    }
}
