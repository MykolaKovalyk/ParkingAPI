package com.lpnu.iot.parking.structure.client;

import com.lpnu.iot.parking.resources.Client;
import com.lpnu.iot.parking.structure.CSVRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository extends CSVRepository<Client> {
    @Override
    protected Client createNewResource() {
        return new Client();
    }

    public ClientRepository() {
        super("data/clients");
    }
}
