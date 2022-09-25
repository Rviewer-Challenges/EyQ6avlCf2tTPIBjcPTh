package com.rebirth.mycode.services.mapper.components;

import org.springframework.stereotype.Component;

@Component
public class AuditorComponent {


    public Integer incressVersionByOne(Integer version) {
        return version + 1;
    }

}
