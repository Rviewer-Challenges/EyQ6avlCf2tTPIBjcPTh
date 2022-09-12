package com.rebirth.mycode.configuration;

import com.rebirth.mycode.domain.events.SnippetCodeEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public SnippetCodeEventHandler snippetCodeEventHandler() {
        return new SnippetCodeEventHandler();
    }
}
