package com.lpnu.iot.parking.structure.repositories;

import com.lpnu.iot.parking.resources.ClientCard;
import org.springframework.stereotype.Repository;

@Repository
public class ClientCardRepository extends CSVRepository<ClientCard> {
    public ClientCardRepository() {
        super("data/client_cards");
    }

    @Override
    protected ClientCard createNewResource() {
        return new ClientCard();
    }
}
