package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordToken {

    private static final int EXPIRATION = ChronoUnit.MINUTES.ordinal() * 2;

    @Id
    private long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, referencedColumnName = "userId")
    private User user;

    private Date expiryDate;
}
