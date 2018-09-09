package com.vdudnyk.discountwallet.application.code;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Code {

    @Id
    @GeneratedValue
    private Long id;

    private String value;
}
