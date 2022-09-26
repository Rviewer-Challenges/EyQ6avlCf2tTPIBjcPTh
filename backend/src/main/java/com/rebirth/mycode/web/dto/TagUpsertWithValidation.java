package com.rebirth.mycode.web.dto;

import com.rebirth.mycode.domain.udt.pojos.TagUpsert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TagUpsertWithValidation extends TagUpsert {

    @Override
    @NotNull
    @NotBlank
    @Size(max = 255, min = 1)
    public String getName() {
        return super.getName();
    }
}
