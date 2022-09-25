package com.rebirth.mycode.services.mapper;

import com.rebirth.mycode.domain.tables.pojos.Tag;
import com.rebirth.mycode.domain.udt.pojos.TagSecure;
import com.rebirth.mycode.domain.udt.pojos.TagUpsert;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        imports = {LocalDateTime.class}
)
public interface TagMapper {

    @ToAuditor
    @Mapping(target = "tagUid", ignore = true)
    @Mapping(source = "name", target = "tagName")
    Tag tagUpsertToTag(TagUpsert tagUpsert);

    @Mapping(source = "tagUid", target = "tagUid")
    @Mapping(source = "tagName", target = "name")
    TagSecure tagToTagSecure(Tag tag);

    @Mapping(source = "name", target = "tagName")
    @Mapping(target = "tagUid", ignore = true)
    @Mapping(target = "isEnable", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "createBy", ignore = true)
    @Mapping(target = "version", expression = "java(tag.getVersion() == null ? 1 : tag.getVersion() + 1)")
    @Mapping(target = "modifyAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "modifyBy", constant = "SYSTEM")
    void updateTag(TagUpsert tagUpsert, @MappingTarget Tag tag);

}
