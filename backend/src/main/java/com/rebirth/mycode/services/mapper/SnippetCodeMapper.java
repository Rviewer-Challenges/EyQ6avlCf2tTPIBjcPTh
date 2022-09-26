package com.rebirth.mycode.services.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebirth.mycode.domain.routines.FetchPageSnippetCodeSecure;
import com.rebirth.mycode.domain.routines.FetchSnippetCodeSecureJooqadapt;
import com.rebirth.mycode.domain.routines.InsertSnippetcode;
import com.rebirth.mycode.domain.routines.UpsertSnippetcode;
import com.rebirth.mycode.domain.udt.pojos.SnippetCodePage;
import com.rebirth.mycode.domain.udt.pojos.SnippetCodeSecure;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        imports = {LocalDateTime.class, SnippetCodeSecure.class}
)
public abstract class SnippetCodeMapper {

    @Autowired
    protected ObjectMapper objectMapper;

    @Mapping(target = "payload", expression = "java(this.objectMapper.readValue(fetchPageSnippetCodeSecure.getPayload(), SnippetCodeSecure[].class))")
    public abstract SnippetCodePage fetchSnippetCodeSecure2SnippetCodePage(FetchPageSnippetCodeSecure fetchPageSnippetCodeSecure) throws JsonProcessingException;


    public abstract SnippetCodeSecure fetchSnippetCodeSecureJooqadapt2SnippetCodePage(FetchSnippetCodeSecureJooqadapt fetchSnippetCodeSecureJooqadapt);

    @Mapping(source = "codeUidOut", target = "snippetCodeUid")
    @Mapping(source = "titleOut", target = "title")
    @Mapping(source = "descriptionOut", target = "description")
    @Mapping(source = "codeOut", target = "code")
    @Mapping(source = "languageNameOut", target = "languageName")
    @Mapping(source = "langVersionOut", target = "langVersion")
    @Mapping(source = "tagsOut", target = "tags")
    @Mapping(source = "createAtOut", target = "createAt")
    public abstract SnippetCodeSecure insertSnippetcodeToSnippetCodeSecure(InsertSnippetcode insertSnippetcode);


    @Mapping(source = "codeUidOut", target = "snippetCodeUid")
    @Mapping(source = "titleOut", target = "title")
    @Mapping(source = "descriptionOut", target = "description")
    @Mapping(source = "codeOut", target = "code")
    @Mapping(source = "languageNameOut", target = "languageName")
    @Mapping(source = "langVersionOut", target = "langVersion")
    @Mapping(source = "tagsOut", target = "tags")
    @Mapping(source = "createAtOut", target = "createAt")
    public abstract SnippetCodeSecure upsertSnippetcodeToSnippetCodeSecure(UpsertSnippetcode upsertSnippetcode);


}
