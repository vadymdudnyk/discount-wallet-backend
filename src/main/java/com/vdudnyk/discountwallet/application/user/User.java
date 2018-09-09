package com.vdudnyk.discountwallet.application.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String phoneNumber;

    private String oneTimePassword;
    private String firstName;
    private String lastName;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    private Boolean isVerified;
}
