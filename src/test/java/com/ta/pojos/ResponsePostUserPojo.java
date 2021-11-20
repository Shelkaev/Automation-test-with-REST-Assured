package com.ta.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import com.ta.utils.DateDeserializer;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePostUserPojo extends AbstractPojo  {
    private String name;
    private String job;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime createdAt;

    @Override
    public Object[] toList() {
        return new Object[] {name, job};
    }
}
