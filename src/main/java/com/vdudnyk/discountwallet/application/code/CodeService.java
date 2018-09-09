package com.vdudnyk.discountwallet.application.code;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CodeService {
    private final CodeRepository codeRepository;

    public Code createCode(String codeValue) {
        Code code = new Code();
        code.setValue(codeValue);
        return codeRepository.save(code);
    }
}
