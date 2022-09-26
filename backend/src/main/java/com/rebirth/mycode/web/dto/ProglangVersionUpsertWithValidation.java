package com.rebirth.mycode.web.dto;

import com.rebirth.mycode.domain.udt.pojos.ProglangVersionUpsert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class ProglangVersionUpsertWithValidation extends ProglangVersionUpsert {


    @Override
    @NotBlank
    @NotNull
    @Size(min = 1, max = 30)
    public String getVersionCode() {
        return super.getVersionCode();
    }

    @Override
    @NotNull
    public UUID getProgrammingLanguageUid() {
        return super.getProgrammingLanguageUid();
    }
}
