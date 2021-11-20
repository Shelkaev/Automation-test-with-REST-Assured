package com.ta.pojos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RequestUserPojo extends AbstractPojo {
    private String name;
    private String job;
    private final LocalDateTime createdAt = LocalDateTime.now().minusHours(4);


    @Override
    public Object[] toList() {
        return new Object[]{name, job};
    }
    public boolean isSameDate(LocalDateTime date){
        return createdAt.isBefore(date)
                && createdAt.plusSeconds(20).isAfter(date);
    }
}
