package com.lpnu.iot.parking.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client extends Resource {

    private String name;
    private String phoneNumber;
    private String email;
    private Long cardId;

    @Override
    public String[] getFieldValues() {
        return new String[]{
                Long.toString(getId()),
                name,
                phoneNumber,
                email,
                Long.toString(cardId)
        };
    }

    @Override
    public String[] getFieldNames() {
        return new String[]{
                "id",
                "name",
                "phoneNumber",
                "email",
                "cardId"
        };
    }

    @Override
    public void setFieldValues(String[] csv) {
        setId(Long.parseLong(csv[0]));
        name = csv[1];
        phoneNumber = csv[2];
        email = csv[3];
        cardId = Long.parseLong(csv[4]);
    }
}
