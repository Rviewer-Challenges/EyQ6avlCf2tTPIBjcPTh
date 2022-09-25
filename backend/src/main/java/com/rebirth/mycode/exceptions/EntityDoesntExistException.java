package com.rebirth.mycode.exceptions;

import java.util.UUID;

public class EntityDoesntExistException extends RuntimeException {
    private final UUID uuid;
    private final String entityName;

    public EntityDoesntExistException(UUID uuid, String entityName) {
        super("No existe la entidad %s con el UUID %s".formatted(entityName, uuid.toString()));
        this.uuid = uuid;
        this.entityName = entityName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getEntityName() {
        return entityName;
    }
}
