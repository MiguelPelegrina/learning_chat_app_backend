package com.pelegrina.learning_chat_app_backend.domain.shared.metaData;

import com.pelegrina.learning_chat_app_backend.domain.user.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAware implements org.springframework.data.domain.AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        var principal = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal);

        if (principal.isPresent()) {
            if (principal.get() instanceof UserEntity) {
                return principal.map(UserEntity.class::cast).map(UserEntity::getUsername);
            }

            if (principal.get() instanceof String) {
                return principal.map(String.class::cast);
            }
        }

        return Optional.empty();
    }
}