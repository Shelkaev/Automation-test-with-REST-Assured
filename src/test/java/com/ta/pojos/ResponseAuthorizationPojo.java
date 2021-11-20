package com.ta.pojos;

import lombok.Data;

@Data
public class ResponseAuthorizationPojo extends AbstractPojo  {
    private String token;

    @Override
    public Object[] toList() {
        return new Object[]{token};
    }
}
