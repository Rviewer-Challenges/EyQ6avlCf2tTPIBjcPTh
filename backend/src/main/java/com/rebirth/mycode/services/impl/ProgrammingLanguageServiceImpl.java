package com.rebirth.mycode.services.impl;

import com.rebirth.mycode.domain.tables.daos.ProgrammingLanguageDao;
import com.rebirth.mycode.domain.tables.pojos.ProgrammingLanguage;
import com.rebirth.mycode.domain.udt.pojos.ProgrammingLanguageSecure;
import com.rebirth.mycode.domain.udt.pojos.ProgrammingLanguageUpsert;
import com.rebirth.mycode.exceptions.EntityDoesntExistException;
import com.rebirth.mycode.services.ProgrammingLanguageService;
import com.rebirth.mycode.services.mapper.ProgrammingLanguageMapper;
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

import static com.rebirth.mycode.domain.tables.ProgrammingLanguage.PROGRAMMING_LANGUAGE;

@Service
public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {

    private final ProgrammingLanguageDao programmingLanguageDao;
    private final ProgrammingLanguageMapper mapper;

    @Autowired
    public ProgrammingLanguageServiceImpl(ProgrammingLanguageDao programmingLanguageDao, ProgrammingLanguageMapper mapper) {
        this.programmingLanguageDao = programmingLanguageDao;
        this.mapper = mapper;
    }

    @Override
    @Cacheable(value = Constants.CACHE_KEY, key = "#uuid.toString()")
    public ProgrammingLanguageSecure findById(UUID uuid) {
        ProgrammingLanguage programmingLanguageInPersistence = this.findByUUIDRaw(uuid);
        return this.mapper.programmingLanguage2ProgrammingLanguageSecure(programmingLanguageInPersistence);
    }

    @Override
    @Cacheable(value = Constants.CACHE_KEY_COLLECTION)
    public List<ProgrammingLanguageSecure> findAll() {
        List<ProgrammingLanguage> programmingLanguageList = this.programmingLanguageDao.fetchByIsEnable(Boolean.TRUE);
        return programmingLanguageList
                .stream()
                .map(this.mapper::programmingLanguage2ProgrammingLanguageSecure)
                .toList();
    }

    @Override
    @CacheEvict(value = Constants.CACHE_KEY_COLLECTION, allEntries = true)
    public ProgrammingLanguageSecure insert(ProgrammingLanguageUpsert object) {
        ProgrammingLanguage programmingLanguage = this.mapper.programmingLanguageDtoToProgrammingLanguage(object);
        this.programmingLanguageDao.insert(programmingLanguage);
        return this.mapper.programmingLanguage2ProgrammingLanguageSecure(programmingLanguage);
    }

    @Override
    @Caching(
            evict = @CacheEvict(value = Constants.CACHE_KEY_COLLECTION, allEntries = true),
            put = @CachePut(value = Constants.CACHE_KEY, key = "#uuid.toString()")
    )
    public ProgrammingLanguageSecure update(ProgrammingLanguageUpsert object, UUID uuid) {
        try {
            ProgrammingLanguage programmingLanguageInPersistence = this.findByUUIDRaw(uuid);

            this.mapper.updateProgrammingLanguage(object, programmingLanguageInPersistence);
            this.programmingLanguageDao.update(programmingLanguageInPersistence);
            return this.mapper.programmingLanguage2ProgrammingLanguageSecure(programmingLanguageInPersistence);

        } catch (EntityDoesntExistException e) {
            ProgrammingLanguage programmingLanguage = this.mapper.programmingLanguageDtoToProgrammingLanguage(object);
            programmingLanguage.setProgrammingLanguageUid(e.getUuid());
            this.programmingLanguageDao.insert(programmingLanguage);
            return this.mapper.programmingLanguage2ProgrammingLanguageSecure(programmingLanguage);
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = Constants.CACHE_KEY, key = "#uuid.toString()"),
                    @CacheEvict(value = Constants.CACHE_KEY_COLLECTION, allEntries = true),
            }
    )
    public void delete(UUID uuid) {
        this.verifyIfExistEntity(uuid);
        this.programmingLanguageDao.deleteById(uuid);
    }

    private ProgrammingLanguage findByUUIDRaw(UUID uuid) {
        this.verifyIfExistEntity(uuid);
        return this.programmingLanguageDao.findById(uuid);
    }

    private void verifyIfExistEntity(UUID uuid) throws EntityDoesntExistException {
        DSLContext ctx = this.programmingLanguageDao.ctx();
        SelectConditionStep<Record1<Integer>> countQuery = ctx.selectCount()
                .from(PROGRAMMING_LANGUAGE)
                .where(PROGRAMMING_LANGUAGE.IS_ENABLE.eq(true)
                        .and(PROGRAMMING_LANGUAGE.PROGRAMMING_LANGUAGE_UID.eq(uuid))
                );
        @Nullable Integer doesntExists = countQuery.fetchOne(0, Integer.class);
        if (doesntExists != null && doesntExists == 0) {
            throw new EntityDoesntExistException(uuid, "ProgrammingLanguage");
        }
    }

    @Override
    public ProgrammingLanguageSecure fetchByName(String name) {
        ProgrammingLanguage programmingLanguage = this.programmingLanguageDao.fetchOneByProgrammingLanguageName(name);
        return this.mapper.programmingLanguage2ProgrammingLanguageSecure(programmingLanguage);
    }
}
