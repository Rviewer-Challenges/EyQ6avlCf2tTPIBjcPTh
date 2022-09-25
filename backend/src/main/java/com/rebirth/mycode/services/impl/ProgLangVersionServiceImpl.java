package com.rebirth.mycode.services.impl;

import com.rebirth.mycode.domain.tables.daos.ProglangVersionDao;
import com.rebirth.mycode.domain.tables.pojos.ProglangVersion;
import com.rebirth.mycode.domain.udt.pojos.ProglangVersionSecure;
import com.rebirth.mycode.domain.udt.pojos.ProglangVersionUpsert;
import com.rebirth.mycode.exceptions.EntityDoesntExistException;
import com.rebirth.mycode.services.ProgLangVersionService;
import com.rebirth.mycode.services.mapper.ProgLangVersionMapper;
import org.jetbrains.annotations.Nullable;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.rebirth.mycode.domain.tables.ProglangVersion.PROGLANG_VERSION;

@Service
public class ProgLangVersionServiceImpl implements ProgLangVersionService {

    private final ProglangVersionDao proglangVersionDao;
    private final ProgLangVersionMapper mapper;

    @Autowired
    public ProgLangVersionServiceImpl(ProglangVersionDao proglangVersionDao, ProgLangVersionMapper mapper) {
        this.proglangVersionDao = proglangVersionDao;
        this.mapper = mapper;
    }

    @Override
    @Cacheable(value = Constants.CACHE_KEY, key = "#uuid.toString()")
    public ProglangVersionSecure findById(UUID uuid) {
        ProglangVersion proglangversion = this.findByUUIDRaw(uuid);
        return this.mapper.proglangVersionToProglangVersionSecure(proglangversion);
    }

    @Override
    @Cacheable(value = Constants.CACHE_KEY_COLLECTION_BY_LANGUAGE, key = "#languageUUID.toString()")
    public List<ProglangVersionSecure> findByLanguage(UUID languageUUID) {
        return this.proglangVersionDao.fetchByProgrammingLanguageUid(languageUUID)
                .stream().map(this.mapper::proglangVersionToProglangVersionSecure)
                .toList();
    }

    @Override
    @Cacheable(value = Constants.CACHE_KEY_COLLECTION)
    public List<ProglangVersionSecure> findAll() {
        return this.proglangVersionDao
                .fetchByIsEnable(Boolean.TRUE)
                .stream().map(this.mapper::proglangVersionToProglangVersionSecure)
                .toList();
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = Constants.CACHE_KEY_COLLECTION, allEntries = true),
                    @CacheEvict(value = Constants.CACHE_KEY_COLLECTION_BY_LANGUAGE, key = "#object.getProgrammingLanguageUid().toString()")
            }
    )
    public ProglangVersionSecure insert(ProglangVersionUpsert object) {
        ProglangVersion proglangVersion = this.mapper.proglangVersionUpsertToProglangVersion(object);
        this.proglangVersionDao.insert(proglangVersion);
        return this.mapper.proglangVersionToProglangVersionSecure(proglangVersion);
    }

    @Override
    @Caching(
            evict = @CacheEvict(value = Constants.CACHE_KEY_COLLECTION, allEntries = true),
            put = @CachePut(value = Constants.CACHE_KEY, key = "#uuid.toString()")
    )
    public ProglangVersionSecure update(ProglangVersionUpsert object, UUID uuid) {
        ProglangVersion programmingLanguage;
        try {
            programmingLanguage = this.findByUUIDRaw(uuid);
            this.mapper.updateProglangVersion(object, programmingLanguage);
            this.proglangVersionDao.update(programmingLanguage);
        } catch (EntityDoesntExistException e) {
            programmingLanguage = this.mapper.proglangVersionUpsertToProglangVersion(object);
            programmingLanguage.setProgrammingLanguageUid(e.getUuid());
            this.proglangVersionDao.insert(programmingLanguage);
        }
        return this.mapper.proglangVersionToProglangVersionSecure(programmingLanguage);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = Constants.CACHE_KEY, key = "#uuid.toString()"),
                    @CacheEvict(value = Constants.CACHE_KEY_COLLECTION, allEntries = true),
            }
    )
    public void delete(UUID uuid) {
        UUID proglangVersionUid = this.findByUUIDRaw(uuid).getProglangVersionUid();
        this.proglangVersionDao.deleteById(proglangVersionUid);
    }

    private ProglangVersion findByUUIDRaw(UUID uuid) {
        this.verifyIfExistEntity(uuid);
        return this.proglangVersionDao.findById(uuid);
    }

    private void verifyIfExistEntity(UUID uuid) throws EntityDoesntExistException {
        DSLContext ctx = this.proglangVersionDao.ctx();
        SelectConditionStep<Record1<Integer>> countQuery = ctx.selectCount()
                .from(PROGLANG_VERSION)
                .where(PROGLANG_VERSION.IS_ENABLE.eq(true)
                        .and(PROGLANG_VERSION.PROGRAMMING_LANGUAGE_UID.eq(uuid))
                );
        @Nullable Integer doesntExists = countQuery.fetchOne(0, Integer.class);
        if (doesntExists != null && doesntExists == 0) {
            throw new EntityDoesntExistException(uuid, "ProgrammingLanguage");
        }
    }


}
