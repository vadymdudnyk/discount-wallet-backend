package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.code.Code;
import com.vdudnyk.discountwallet.application.code.CodeFacade;
import lombok.RequiredArgsConstructor;
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
}


