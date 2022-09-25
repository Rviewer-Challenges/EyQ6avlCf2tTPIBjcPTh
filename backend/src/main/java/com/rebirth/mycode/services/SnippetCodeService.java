package com.rebirth.mycode.services;

import com.rebirth.mycode.domain.udt.pojos.SnippetCodePage;
import com.rebirth.mycode.domain.udt.pojos.SnippetCodeSecure;
import com.rebirth.mycode.domain.udt.pojos.SnippetCodeUpsert;

import java.util.UUID;

public interface SnippetCodeService
        extends BaseService<SnippetCodeSecure, SnippetCodeUpsert, SnippetCodeUpsert, UUID> {

    SnippetCodePage getByPage(int page, int size);


}
