package com.lpnu.iot.parking.resources;

import java.text.ParseException;
import java.util.Date;

public class ClientCard extends Resource {

    public String cardName;
    public Long ownerClientId;
    public String cardType;
    public String details;
    public Long shopOfRetrievalId;
    public Date dateOfRetrieval;
    public Date lastUsage;
    public Integer timesUsed;

    @Override
    public String[] toListOfStrings() {
        return new String[] {
                Long.toString(id),
                Long.toString(ownerClientId),
                cardType,
                details,
                Long.toString(shopOfRetrievalId),
                dateFormat.format(dateOfRetrieval),
                dateFormat.format(lastUsage),
                Integer.toString(timesUsed)
        };
    }

    @Override
    public void fromListOfStrings(String[] csv) {
        id = Long.parseLong(csv[0]);
        ownerClientId = Long.parseLong(csv[1]);
        cardType = csv[2];
        details = csv[3];
        shopOfRetrievalId = Long.parseLong(csv[4]);

        try {
            dateOfRetrieval = dateFormat.parse(csv[5]);
            lastUsage = dateFormat.parse(csv[6]);
        } catch(ParseException e) {
            System.out.println("Parsing datetime failed!");
        }

        timesUsed = Integer.parseInt(csv[7]);
    }
}
