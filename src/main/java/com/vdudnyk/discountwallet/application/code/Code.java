package com.vdudnyk.discountwallet.application.code;

import com.vdudnyk.discountwallet.application.business.Business;
import com.vdudnyk.discountwallet.application.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Code {

    @Id
    @GeneratedValue
    private Long id;

    private String value;

    @ManyToOne
    private Business business;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private CodeType codeType;

    private String description;

    private Long usages;
    private Long maxUsages;

    private Boolean active;

    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
}
