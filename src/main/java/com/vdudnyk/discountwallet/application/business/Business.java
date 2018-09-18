package com.vdudnyk.discountwallet.application.business;

import com.vdudnyk.discountwallet.application.user.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Data
public class Business {
    @Id
    @GeneratedValue
    private Long id;

    private String businessName;
    private String city;
    private String address;
    private String zipCode;
    @ManyToMany
    private Set<User> administrator;
}
