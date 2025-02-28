package com.pelegrina.learning_chat_app_backend.configuration;

import com.pelegrina.learning_chat_app_backend.domain.shared.metaData.AuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The {@code AuditingConfiguration} class is responsible for configuring the auditing of the application. It enables
 * JPA (Java Persistence API) auditing and specifies the {@link AuditorAware} implementation to be used.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditingConfiguration {
    @Bean
    public AuditorAware auditorAware() {
        return new AuditorAware();
    }
}
