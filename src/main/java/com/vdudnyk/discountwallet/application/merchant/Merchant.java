package com.vdudnyk.discountwallet.application.merchant;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Merchant {
    @Id
    @GeneratedValue
    private Long id;

    private String businessName;
    private String city;
    private String address;
}
