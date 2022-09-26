package com.rebirth.mycode.web.dto;

import com.rebirth.mycode.domain.udt.pojos.ProgrammingLanguageUpsert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProgrammingLanguageUpsertWithValidation extends ProgrammingLanguageUpsert {

    @Override
    @NotBlank
    @NotNull
    @Size(min = 1, max = 30)
    public String getProgrammingLanguageName() {
        return super.getProgrammingLanguageName();
    }
}
