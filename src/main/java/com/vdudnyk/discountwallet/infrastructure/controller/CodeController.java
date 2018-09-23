package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.code.Code;
import com.vdudnyk.discountwallet.application.code.CodeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CodeController {
    private final CodeFacade codeFacade;

    @GetMapping("/businesses/{businessId}/codes")
    List<Code> getAllBusinessCodes(@PathVariable Long businessId) {
        return codeFacade.getAllBusinessCodes(businessId);
    }

    @GetMapping("/business/{businessId}/codes/{code}/validate")
    ResponseEntity<StatusResponse> validateCode(@PathVariable Long businessId, @PathVariable String code) {
        codeFacade.validateCode(businessId, code);
        return ResponseEntity.ok(StatusResponse.success());
    }
}


