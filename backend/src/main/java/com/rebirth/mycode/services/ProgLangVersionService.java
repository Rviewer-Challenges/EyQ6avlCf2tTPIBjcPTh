package com.rebirth.mycode.services;

import com.rebirth.mycode.domain.udt.pojos.ProglangVersionSecure;
import com.rebirth.mycode.domain.udt.pojos.ProglangVersionUpsert;

import java.util.List;
import java.util.UUID;

public interface ProgLangVersionService
        extends BaseService<ProglangVersionSecure, ProglangVersionUpsert, ProglangVersionUpsert, UUID> {

    interface Constants {
        String CACHE_KEY_COLLECTION = "listOfProglangVersion";
        String CACHE_KEY_COLLECTION_BY_LANGUAGE = "listOfVersionByLanguage";

        String CACHE_KEY = "proglangVersion";
    }

    List<ProglangVersionSecure> findByLanguage(UUID languageUUID);


}
