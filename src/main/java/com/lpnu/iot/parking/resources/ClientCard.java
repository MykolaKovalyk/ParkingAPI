package com.lpnu.iot.parking.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientCard extends Resource {

    private Long ownerClientId;
    private String cardType;
    private String details;
    private Long shopOfRetrievalId;
    private Date dateOfRetrieval;
    private Date lastUsage;
    private Integer timesUsed;

    @Override
    public String[] getFieldValues() {
        return new String[]{
                Long.toString(getId()),
                Long.toString(ownerClientId),
                cardType,
                details,
                Long.toString(shopOfRetrievalId),
                DATE_FORMAT.format(dateOfRetrieval),
                DATE_FORMAT.format(lastUsage),
                Integer.toString(timesUsed)
        };
    }

    @Override
    public String[] getFieldNames() {
        return new String[]{
                "id",
                "cardName",
                "ownerClientId",
                "cardType",
                "details",
                "shopOfRetrievalId",
                "dateOfRetrieval",
                "lastUsage",
                "timesUsed",
        };
    }

    @Override
    public void setFieldValues(String[] csv) {
        setId(Long.parseLong(csv[0]));
        ownerClientId = Long.parseLong(csv[1]);
        cardType = csv[2];
        details = csv[3];
        shopOfRetrievalId = Long.parseLong(csv[4]);

        try {
            dateOfRetrieval = DATE_FORMAT.parse(csv[5]);
            lastUsage = DATE_FORMAT.parse(csv[6]);
        } catch (ParseException e) {
            System.out.println("Parsing datetime failed!");
        }

        timesUsed = Integer.parseInt(csv[7]);
    }
}
