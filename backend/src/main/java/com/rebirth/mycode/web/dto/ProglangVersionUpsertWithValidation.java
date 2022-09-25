package com.rebirth.mycode.web.dto;

import com.rebirth.mycode.domain.udt.pojos.ProglangVersionUpsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ProglangVersionUpsertWithValidation extends ProglangVersionUpsert {


    @Override
    @NotBlank
    @NotNull
    @Length(min = 1, max = 30)
    public String getVersionCode() {
        return super.getVersionCode();
    }

    @Override
    @NotNull
    public UUID getProgrammingLanguageUid() {
        return super.getProgrammingLanguageUid();
    }
}
