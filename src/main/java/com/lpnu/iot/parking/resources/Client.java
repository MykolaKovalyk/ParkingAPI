package com.lpnu.iot.parking.resources;

public class Client extends Resource {

    public String name;
    public String phoneNumber;
    public String email;
    public Long cardId;

    @Override
    public String[] toListOfStrings() {
        return new String[] {
                Long.toString(id),
                name,
                phoneNumber,
                email,
                Long.toString(cardId)
        };
    }

    @Override
    public void fromListOfStrings(String[] csv) {
        id = Long.parseLong(csv[0]);
        name = csv[1];
        phoneNumber = csv[2];
        email = csv[3];
        cardId = Long.parseLong(csv[4]);
    }
}
