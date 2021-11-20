package com.ta.pojos;

import lombok.Data;

@Data
public class ResponseRegistrationPojo  extends AbstractPojo {
    private int id;
    private String token;

    @Override
    public Object[] toList() {
        return new Object[] {id, token};
    }
}
