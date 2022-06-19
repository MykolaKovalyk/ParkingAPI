package com.lpnu.iot.parking.resources;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter
@Setter
public abstract class Resource {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");


    private Long id = -1L;

    public abstract String[] toArrayOfStrings();
    public abstract void fromArrayOfStrings(String[] csv);
}
