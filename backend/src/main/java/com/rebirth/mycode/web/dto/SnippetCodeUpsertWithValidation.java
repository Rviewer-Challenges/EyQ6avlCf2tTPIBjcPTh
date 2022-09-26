package com.rebirth.mycode.web.dto;

import com.rebirth.mycode.domain.udt.pojos.SnippetCodeUpsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class SnippetCodeUpsertWithValidation extends SnippetCodeUpsert {

    @Override
    @Size(min = 1, max = 100)
    @NotBlank
    @NotNull
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    @Size(min = 1, max = 300)
    @NotBlank
    @NotNull
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    @NotBlank
    @NotNull
    @Size(min = 1, max = 3000)
    public String getCode() {
        return super.getCode();
    }

    @Override
    @NotNull
    public UUID getProglangVersionUid() {
        return super.getProglangVersionUid();
    }


}
