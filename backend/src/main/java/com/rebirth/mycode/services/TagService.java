package com.rebirth.mycode.services;

import com.rebirth.mycode.domain.udt.pojos.TagUpsert;
import com.rebirth.mycode.domain.udt.pojos.TagSecure;

import java.util.UUID;

public interface TagService extends BaseService<TagSecure, TagUpsert, TagUpsert, UUID> {

    interface Constants {
        String CACHE_KEY_COLLECTION = "listOfTags";
        String CACHE_KEY = "tag";
    }
}
