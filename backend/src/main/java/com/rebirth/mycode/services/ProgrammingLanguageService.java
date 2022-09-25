package com.rebirth.mycode.services;

import com.rebirth.mycode.domain.udt.pojos.ProgrammingLanguageSecure;
import com.rebirth.mycode.domain.udt.pojos.ProgrammingLanguageUpsert;

import java.util.UUID;

public interface ProgrammingLanguageService
        extends BaseService<ProgrammingLanguageSecure, ProgrammingLanguageUpsert, ProgrammingLanguageUpsert, UUID> {

    interface Constants {
        String CACHE_KEY_COLLECTION = "listOfProgrammingLanguage";
        String CACHE_KEY = "programmingLanguage";
    }

}
