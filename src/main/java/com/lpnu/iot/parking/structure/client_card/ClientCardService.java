package com.lpnu.iot.parking.structure.client_card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientCardService {

    @Autowired
    ClientCardRepository clientCardRepository;
}
