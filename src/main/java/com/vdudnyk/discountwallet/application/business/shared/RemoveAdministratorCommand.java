package com.vdudnyk.discountwallet.application.business.shared;

import lombok.Data;

@Data
public class RemoveAdministratorCommand {
    private Long businessId;
    private String usernameOfAdministrator;
}
