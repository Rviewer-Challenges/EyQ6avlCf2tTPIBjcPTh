package com.rebirth.mycode.web.dto;

import com.rebirth.mycode.domain.udt.pojos.ProgrammingLanguageUpsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProgrammingLanguageUpsertWithValidation extends ProgrammingLanguageUpsert {

    @Override
    @NotBlank
    @NotNull
    @Length(min = 1, max = 30)
    public String getProgrammingLanguageName() {
        return super.getProgrammingLanguageName();
    }
}
