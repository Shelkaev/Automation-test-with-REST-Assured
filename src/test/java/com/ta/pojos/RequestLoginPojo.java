package com.ta.pojos;

import lombok.Data;

@Data
public class RequestLoginPojo extends AbstractPojo{
    private String email;
    private String password;

    @Override
    public Object[] toList() {
        return new Object[]{email, password};
    }
}
