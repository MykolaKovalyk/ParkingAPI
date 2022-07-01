package com.lpnu.iot.parking.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter
@Setter
public abstract class Resource {

    protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private Long id = 0L;

    @JsonIgnore
    public abstract String[] getFieldValues();

    @JsonIgnore
    public abstract String[] getFieldNames();

    @JsonIgnore
    public abstract void setFieldValues(String[] csv);
}
