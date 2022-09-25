package com.rebirth.mycode.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rebirth.mycode.domain.Routines;
import com.rebirth.mycode.domain.routines.FetchPageSnippetCodeSecure;
import com.rebirth.mycode.domain.routines.FetchSnippetCodeSecureJooqadapt;
import com.rebirth.mycode.domain.routines.InsertSnippetcode;
import com.rebirth.mycode.domain.udt.pojos.SnippetCodePage;
import com.rebirth.mycode.domain.udt.pojos.SnippetCodeSecure;
import com.rebirth.mycode.domain.udt.pojos.SnippetCodeUpsert;
import com.rebirth.mycode.domain.udt.records.SnippetCodeSecureRecord;
import com.rebirth.mycode.exceptions.JsonParsingException;
import com.rebirth.mycode.services.SnippetCodeService;
import com.rebirth.mycode.services.mapper.SnippetCodeMapper;
import org.jooq.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SnippetCodeServiceImpl implements SnippetCodeService {

    private final Configuration configuration;
    private final SnippetCodeMapper snippetCodeMapper;

    @Autowired
    public SnippetCodeServiceImpl(Configuration configuration, SnippetCodeMapper snippetCodeMapper) {
        this.configuration = configuration;
        this.snippetCodeMapper = snippetCodeMapper;
    }

    @Override
    public SnippetCodeSecure findById(UUID uuid) {
        FetchSnippetCodeSecureJooqadapt fetchSnippetCodeSecureJooqadapt = Routines.fetchSnippetCodeSecureJooqadapt(this.configuration, uuid);
        return this.snippetCodeMapper.fetchSnippetCodeSecureJooqadapt2SnippetCodePage(fetchSnippetCodeSecureJooqadapt);
    }

    @Override
    public SnippetCodePage getByPage(int page, int size) {
        FetchPageSnippetCodeSecure pageResponse = Routines.fetchPageSnippetCodeSecure(this.configuration, page, size);
        try {
            return this.snippetCodeMapper.fetchSnippetCodeSecure2SnippetCodePage(pageResponse);
        } catch (JsonProcessingException e) {
            throw new JsonParsingException(e, pageResponse.getPayload());
        }
    }

    @Override
    public SnippetCodeSecure insert(SnippetCodeUpsert object) {
        InsertSnippetcode newSnippetcode = Routines.insertSnippetcode(this.configuration, "SYSTEM", object.getTitle(), object.getDescription(), object.getCode(), object.getProglangVersionUid(), object.getTagsUuids());
        return new SnippetCodeSecure()
                .setSnippetCodeUid(newSnippetcode.getCodeUidOut())
                .setTitle(newSnippetcode.getTitleOut())
                .setDescription(newSnippetcode.getDescriptionOut())
                .setCode(newSnippetcode.getCodeOut())
                .setLanguageName(newSnippetcode.getLanguageNameOut())
                .setLangVersion(newSnippetcode.getLangVersionOut())
                .setTags(newSnippetcode.getTagsOut())
                .setCreateAt(newSnippetcode.getCreateAtOut());
    }

    @Override
    public SnippetCodeSecure update(SnippetCodeUpsert object, UUID uuid) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }


}
