package com.vdudnyk.discountwallet.application.code;

import com.vdudnyk.discountwallet.application.business.BusinessFacade;
import com.vdudnyk.discountwallet.application.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class CodeService {
    private final CodeRepository codeRepository;
    private final CodeGenerator codeGenerator;
    private final BusinessFacade businessFacade;

    Code generateCodeForUser(Long businessId, User user) {
        codeGenerator.generateSimpleCode();
        Code code = new Code();
        code.setBusiness(businessFacade.getBusiness(businessId));
        code.setUser(user);
        code.setActive(true);
        codeRepository.save(code);
        return code;

    }

    List<Code> getAllBusinessCodes(Long businessId) {
        businessFacade.getUserBusiness(businessId);
        return null;
    }

    void validateCode(Long businessId, String code) {
        log.info("businessId: {}, code: {}", businessId, code);
    }
}
