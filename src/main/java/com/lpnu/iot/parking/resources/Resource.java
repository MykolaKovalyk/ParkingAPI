package com.lpnu.iot.parking.resources;

public abstract class Resource {
    public long id = -1L;

    public abstract String[] toListOfStrings();
    public abstract void fromListOfStrings(String[] csv);
}
