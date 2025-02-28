package com.pelegrina.learning_chat_app_backend.domain.user;

import com.pelegrina.learning_chat_app_backend.domain.shared.metaData.IntValues;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ToDo
 * - Indexes with @Table
 */
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@Schema(description = "User")
@Setter
@ToString
public class UserEntity implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "User ID", example = "1")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "{validation.user.email.invalid}")
    @NotNull(message = "{validation.user.email.notnull}")
    @Schema(description = "User email", example = "user@email.com")
    private String email;

    @Column(name = "password", nullable = false)
    @NotNull(message = "{validation.user.password.notnull}")
    @Schema(description = "User password", example = "n0ts3cur3")
    @Size(min = IntValues.PASSWORD_MIN_LENGTH, message = "{validation.user.password.tooshort}")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private UserRoleEntity role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Prefixes role names with "ROLE_" as per Spring Security conventions.
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
