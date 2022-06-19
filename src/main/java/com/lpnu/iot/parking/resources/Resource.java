package com.lpnu.iot.parking.resources;

import java.text.SimpleDateFormat;

public abstract class Resource {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");


    public Long id = -1L;

    public abstract String[] toListOfStrings();
    public abstract void fromListOfStrings(String[] csv);
}
