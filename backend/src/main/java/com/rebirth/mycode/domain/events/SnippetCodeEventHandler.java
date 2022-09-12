package com.rebirth.mycode.domain.events;

import com.rebirth.mycode.domain.models.SnippetCode;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import java.util.UUID;

@RepositoryEventHandler
public class SnippetCodeEventHandler {

    @HandleBeforeCreate
    public void handleSnippetCodeSave(SnippetCode snippetCode) {
        snippetCode.setUuid(UUID.randomUUID());
    }
}
