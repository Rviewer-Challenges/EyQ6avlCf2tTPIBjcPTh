package com.rebirth.mycode.configuration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class MyAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("System");
    }

}
