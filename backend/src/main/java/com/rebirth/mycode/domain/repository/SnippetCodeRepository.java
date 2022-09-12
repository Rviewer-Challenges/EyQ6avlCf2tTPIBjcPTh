package com.rebirth.mycode.domain.repository;

import com.rebirth.mycode.domain.models.SnippetCode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SnippetCodeRepository extends CrudRepository<SnippetCode, Long> {

    @Query("SELECT sc0 FROM SnippetCode sc0 where sc0.uuid=:uuid")
    SnippetCode findByUuid(@Param("uuid") UUID uuid);

    @Query("SELECT sc0 FROM SnippetCode sc0 inner join sc0.tags t0 where (sc0.title like %:title%) and (sc0.language.name=:langName) and (t0.name in :tags)")
    List<SnippetCode> findCodeWithFilter(@Param("title") String title, @Param("langName") String langName, @Param("tags") List<String> tags);


}
