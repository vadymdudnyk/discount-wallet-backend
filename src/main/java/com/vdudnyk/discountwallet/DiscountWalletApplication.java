package com.vdudnyk.discountwallet;

import com.vdudnyk.discountwallet.application.event.EventProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(value = {EventProcessor.class})
public class DiscountWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscountWalletApplication.class, args);
    }
}
