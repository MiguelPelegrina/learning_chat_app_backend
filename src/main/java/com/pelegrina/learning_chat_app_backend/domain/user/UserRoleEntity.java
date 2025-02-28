package com.pelegrina.learning_chat_app_backend.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Schema(description = "Role that is assigned to a user")
@Setter
@ToString
public class UserRoleEntity implements Serializable {
    @Id
    @Column(name = "name", length = 45)
    private String name;
}
