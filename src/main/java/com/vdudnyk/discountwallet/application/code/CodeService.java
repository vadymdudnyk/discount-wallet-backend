package com.vdudnyk.discountwallet.application.code;

import com.vdudnyk.discountwallet.application.business.BusinessFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class CodeService {
    private final CodeRepository codeRepository;
    private final CodeGenerator codeGenerator;
    private final BusinessFacade businessFacade;

    Code generateCode(Long businessId) {
        return null;
    }

    List<Code> getAllBusinessCodes(Long businessId) {
        businessFacade.getUserBusiness(businessId);
        return null;
    }
}
