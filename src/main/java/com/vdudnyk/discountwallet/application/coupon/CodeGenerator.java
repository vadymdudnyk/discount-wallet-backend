package com.vdudnyk.discountwallet.application.coupon;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class CodeGenerator {
    String generateSimpleCode() {
        return RandomStringUtils.randomAlphabetic(6).toUpperCase();
    }
}
