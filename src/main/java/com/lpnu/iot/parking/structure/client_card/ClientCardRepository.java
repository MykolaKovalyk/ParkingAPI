package com.lpnu.iot.parking.structure.client_card;

import com.lpnu.iot.parking.resources.ClientCard;
import com.lpnu.iot.parking.structure.CSVRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ClientCardRepository extends CSVRepository<ClientCard> {
    @Override
    protected ClientCard createNewResource() {
        return new ClientCard();
    }

    public ClientCardRepository() {
        super("data/client_cards.csv");
    }
}
