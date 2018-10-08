package com.vdudnyk.discountwallet.application.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeGenerator {
    public static String generateSimpleCode() {
        return RandomStringUtils.randomAlphabetic(8).toUpperCase();
    }
}
