package com.ta.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResourcePojo extends AbstractPojo {
    private int id;
    private String name;
    private int year;
    private String color;
    @JsonProperty("pantone_value")
    private String pantoneValue;

    @Override
    public Object[] toList() {
        return new Object[] {id, name, year, color, pantoneValue};
    }
}
