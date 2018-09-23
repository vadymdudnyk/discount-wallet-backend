package com.vdudnyk.discountwallet.application.code;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeFacade {
    private final CodeService codeService;

    public List<Code> getAllBusinessCodes(Long businessId) {
        return codeService.getAllBusinessCodes(businessId);
    }

    public void validateCode(Long businessId, String code) {
        codeService.validateCode(businessId, code);
    }
}
