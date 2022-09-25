package com.rebirth.mycode.services.mapper;


import com.rebirth.mycode.domain.tables.pojos.ProgrammingLanguage;
import com.rebirth.mycode.domain.udt.pojos.ProgrammingLanguageSecure;
import com.rebirth.mycode.domain.udt.pojos.ProgrammingLanguageUpsert;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        imports = {LocalDateTime.class}
)
public interface ProgrammingLanguageMapper {

    @Mapping(target = "programmingLanguageUid", ignore = true)
    @ToAuditor
    ProgrammingLanguage programmingLanguageDtoToProgrammingLanguage(ProgrammingLanguageUpsert programmingLanguageUpsert);


    @Mapping(source = "programmingLanguageUid", target = "programmingLanguageUid")
    @Mapping(source = "programmingLanguageName", target = "programmingLanguageName")
    @Mapping(source = "createAt", target = "createAt")
    ProgrammingLanguageSecure programmingLanguage2ProgrammingLanguageSecure(ProgrammingLanguage programmingLanguageUpsert);

    @Mapping(source = "programmingLanguageName", target = "programmingLanguageName")
    @Mapping(target = "programmingLanguageUid", ignore = true)
    @Mapping(target = "isEnable", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "createBy", ignore = true)
    @Mapping(target = "version", expression = "java(programmingLanguage.getVersion() == null ? 1 : programmingLanguage.getVersion() + 1)")
    @Mapping(target = "modifyAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "modifyBy", constant = "SYSTEM")
    void updateProgrammingLanguage(
            ProgrammingLanguageUpsert programmingLanguageUpsert,
            @MappingTarget ProgrammingLanguage programmingLanguage
    );

}
