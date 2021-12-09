package org.catty.domains.account.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.catty.domains.account.model.AccountRole;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id", updatable = false)
    private Integer id;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "phone_number", nullable = false, length = 13)
    private String phoneNumber;

    @Column(name = "authority", nullable = false)
    private AccountRole role;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Account(String email, String password, String username, String phoneNumber, LocalDateTime createdAt) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.role = AccountRole.USER;
        this.createdAt = createdAt;
    }
}