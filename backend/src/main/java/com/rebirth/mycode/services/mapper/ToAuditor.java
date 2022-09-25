package com.rebirth.mycode.services.mapper;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "createAt", expression = "java(LocalDateTime.now())")
@Mapping(target = "modifyAt", expression = "java(LocalDateTime.now())")
@Mapping(target = "createBy", constant = "SYSTEM")
@Mapping(target = "modifyBy", constant = "SYSTEM")
@Mapping(target = "version", constant = "1")
@Mapping(target = "isEnable", expression = "java(Boolean.TRUE)")
public @interface ToAuditor {
}
