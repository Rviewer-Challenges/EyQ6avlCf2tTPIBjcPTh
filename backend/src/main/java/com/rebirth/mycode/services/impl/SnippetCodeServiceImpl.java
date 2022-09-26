package com.rebirth.mycode.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rebirth.mycode.domain.Routines;
import com.rebirth.mycode.domain.routines.FetchPageSnippetCodeSecure;
import com.rebirth.mycode.domain.routines.FetchSnippetCodeSecureJooqadapt;
import com.rebirth.mycode.domain.routines.InsertSnippetcode;
import com.rebirth.mycode.domain.routines.UpsertSnippetcode;
import com.rebirth.mycode.domain.tables.daos.SnippetCodeDao;
import com.rebirth.mycode.domain.tables.pojos.SnippetCode;
import com.rebirth.mycode.domain.udt.pojos.SnippetCodePage;
import com.rebirth.mycode.domain.udt.pojos.SnippetCodeSecure;
import com.rebirth.mycode.domain.udt.pojos.SnippetCodeUpsert;
import com.rebirth.mycode.exceptions.EntityDoesntExistException;
import com.rebirth.mycode.exceptions.JsonParsingException;
import com.rebirth.mycode.services.SnippetCodeService;
import com.rebirth.mycode.services.mapper.SnippetCodeMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.rebirth.mycode.domain.tables.SnippetCodeTag.SNIPPET_CODE_TAG;
import static com.rebirth.mycode.domain.tables.SnippetCode.SNIPPET_CODE;

import java.util.UUID;

@Service
public class SnippetCodeServiceImpl implements SnippetCodeService {

    private final Configuration configuration;
    private final SnippetCodeMapper snippetCodeMapper;
    private final SnippetCodeDao snippetCodeDao;


    @Autowired
    public SnippetCodeServiceImpl(Configuration configuration, SnippetCodeMapper snippetCodeMapper, SnippetCodeDao snippetCodeDao) {
        this.configuration = configuration;
        this.snippetCodeMapper = snippetCodeMapper;
        this.snippetCodeDao = snippetCodeDao;
    }

    @Override
    public SnippetCodeSecure findById(UUID uuid) {
        this.findByUUIDRaw(uuid);
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
        InsertSnippetcode insertSnippetcode = Routines.insertSnippetcode(this.configuration, "SYSTEM", object.getTitle(), object.getDescription(), object.getCode(), object.getProglangVersionUid(), object.getTagsUuids());
        return this.snippetCodeMapper.insertSnippetcodeToSnippetCodeSecure(insertSnippetcode);
    }

    @Override
    public SnippetCodeSecure update(SnippetCodeUpsert object, UUID uuid) {
        UpsertSnippetcode upsertSnippetcode = Routines.upsertSnippetcode(this.configuration, uuid, "SYSTEM", object.getDescription(), object.getCode(), object.getProglangVersionUid(), object.getTagsUuids());
        return this.snippetCodeMapper.upsertSnippetcodeToSnippetCodeSecure(upsertSnippetcode);
    }

    @Override
    public void delete(UUID uuid) {
        SnippetCode snippetCode = this.findByUUIDRaw(uuid);

        @NotNull DSLContext dsl = this.configuration.dsl();
        int delete = dsl.deleteFrom(SNIPPET_CODE_TAG).where(SNIPPET_CODE_TAG.SNIPPET_CODE_UID.eq(snippetCode.getSnippetCodeUid())).execute();
        this.snippetCodeDao.delete(snippetCode);
    }

    private SnippetCode findByUUIDRaw(UUID uuid) {
        this.verifyIfExistEntity(uuid);
        return this.snippetCodeDao.findById(uuid);
    }

    private void verifyIfExistEntity(UUID uuid) throws EntityDoesntExistException {
        DSLContext ctx = this.snippetCodeDao.ctx();
        SelectConditionStep<Record1<Integer>> countQuery = ctx.selectCount().from(SNIPPET_CODE).where(SNIPPET_CODE.IS_ENABLE.eq(true).and(SNIPPET_CODE.SNIPPET_CODE_UID.eq(uuid)));
        @Nullable Integer doesntExists = countQuery.fetchOne(0, Integer.class);
        if (doesntExists != null && doesntExists == 0) {
            throw new EntityDoesntExistException(uuid, "Tag");
        }
    }

}
