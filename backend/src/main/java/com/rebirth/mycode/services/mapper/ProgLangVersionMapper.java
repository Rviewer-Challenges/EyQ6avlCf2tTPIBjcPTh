package com.rebirth.mycode.services.mapper;

import com.rebirth.mycode.domain.tables.pojos.ProglangVersion;
import com.rebirth.mycode.domain.udt.pojos.ProglangVersionSecure;
import com.rebirth.mycode.domain.udt.pojos.ProglangVersionUpsert;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        imports = {LocalDateTime.class}
)
public interface ProgLangVersionMapper {

    @Mapping(target = "proglangVersionUid", ignore = true)
    @ToAuditor
    ProglangVersion proglangVersionUpsertToProglangVersion(ProglangVersionUpsert proglangVersionUpsert);


    @Mapping(source = "proglangVersionUid", target = "proglangVersionUid")
    @Mapping(source = "versionCode", target = "versionCode")
    @Mapping(source = "createAt", target = "createAt")
    ProglangVersionSecure proglangVersionToProglangVersionSecure(ProglangVersion proglangVersion);

    @Mapping(source = "versionCode", target = "versionCode")
    @Mapping(target = "proglangVersionUid", ignore = true)
    @Mapping(target = "isEnable", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "createBy", ignore = true)
    @Mapping(target = "version", expression = "java(proglangVersion.getVersion() == null ? 1 : proglangVersion.getVersion() + 1)")
    @Mapping(target = "modifyAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "modifyBy", constant = "SYSTEM")
    void updateProglangVersion(
            ProglangVersionUpsert proglangVersionUpsert,
            @MappingTarget ProglangVersion proglangVersion
    );


}
