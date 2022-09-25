package com.rebirth.mycode.services.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebirth.mycode.domain.routines.FetchPageSnippetCodeSecure;
import com.rebirth.mycode.domain.routines.FetchSnippetCodeSecureJooqadapt;
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


}
