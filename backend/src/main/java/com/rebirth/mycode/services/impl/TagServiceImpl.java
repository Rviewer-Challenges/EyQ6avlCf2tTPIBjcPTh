package com.rebirth.mycode.services.impl;

import com.rebirth.mycode.domain.tables.daos.TagDao;
import com.rebirth.mycode.domain.tables.pojos.Tag;
import com.rebirth.mycode.domain.tables.records.TagRecord;
import com.rebirth.mycode.domain.udt.pojos.TagSecure;
import com.rebirth.mycode.domain.udt.pojos.TagUpsert;
import com.rebirth.mycode.exceptions.EntityDoesntExistException;
import com.rebirth.mycode.services.TagService;
import com.rebirth.mycode.services.mapper.TagMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.rebirth.mycode.domain.tables.Tag.TAG;

@Service
public class TagServiceImpl implements TagService {

    private final TagMapper mapper;
    private final TagDao tagDao;

    public TagServiceImpl(TagMapper mapper, TagDao tagDao) {
        this.mapper = mapper;
        this.tagDao = tagDao;
    }

    @Override
    @Cacheable(value = Constants.CACHE_KEY, key = "#uuid.toString()")
    public TagSecure findById(UUID uuid) {
        Tag tag = this.findByUUIDRaw(uuid);
        return this.mapper.tagToTagSecure(tag);
    }

    @Override
    @Cacheable(value = Constants.CACHE_KEY_COLLECTION)
    public List<TagSecure> findAll() {
        return this.tagDao.fetchByIsEnable(Boolean.TRUE)
                .stream()
                .map(this.mapper::tagToTagSecure)
                .toList();
    }

    @Override
    @CacheEvict(value = Constants.CACHE_KEY_COLLECTION, allEntries = true)
    public TagSecure insert(TagUpsert object) {
        Tag tag = this.mapper.tagUpsertToTag(object);
        this.tagDao.insert(tag);
        return this.mapper.tagToTagSecure(tag);
    }

    @Override
    @Caching(
            evict = @CacheEvict(value = Constants.CACHE_KEY_COLLECTION, allEntries = true),
            put = @CachePut(value = Constants.CACHE_KEY, key = "#uuid.toString()")
    )
    public TagSecure update(TagUpsert object, UUID uuid) {
        Tag tagInPersistence;
        try {
            tagInPersistence = this.findByUUIDRaw(uuid);
            this.mapper.updateTag(object, tagInPersistence);
            this.tagDao.update(tagInPersistence);
        } catch (EntityDoesntExistException entityDoesntExistException) {
            tagInPersistence = this.mapper.tagUpsertToTag(object);
            tagInPersistence.setTagUid(entityDoesntExistException.getUuid());
            this.tagDao.insert(tagInPersistence);
        }
        return this.mapper.tagToTagSecure(tagInPersistence);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = Constants.CACHE_KEY, key = "#uuid.toString()"),
                    @CacheEvict(value = Constants.CACHE_KEY_COLLECTION, allEntries = true),
            }
    )
    public void delete(UUID uuid) {
        Tag tagToDelete = this.findByUUIDRaw(uuid);
        this.tagDao.delete(tagToDelete);
    }

    private Tag findByUUIDRaw(UUID uuid) {
        this.verifyIfExistEntity(uuid);
        return this.tagDao.findById(uuid);
    }

    private void verifyIfExistEntity(UUID uuid) throws EntityDoesntExistException {
        DSLContext ctx = this.tagDao.ctx();
        SelectConditionStep<Record1<Integer>> countQuery = ctx
                .selectCount()
                .from(TAG)
                .where(TAG.IS_ENABLE.eq(true).and(TAG.TAG_UID.eq(uuid)));
        @Nullable Integer doesntExists = countQuery.fetchOne(0, Integer.class);
        if (doesntExists != null && doesntExists == 0) {
            throw new EntityDoesntExistException(uuid, "Tag");
        }
    }
}
