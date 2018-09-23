package com.vdudnyk.discountwallet.infrastructure.controller;

import lombok.Value;

@Value
class StatusResponse {
    private final String status;

    static StatusResponse success() {
        return new StatusResponse("SUCCESS");
    }
}
