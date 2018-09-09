package com.vdudnyk.discountwallet.application.merchant;

import com.vdudnyk.discountwallet.application.code.Code;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class Merchant {
    @Id
    @GeneratedValue
    private Long id;

    private String businessName;

    @OneToMany
    private Set<Code> codes;
}
