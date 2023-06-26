package com.slabstech.apitestcontainer;


import java.util.Arrays;

import com.slabstech.apitestcontainer.model.Address;
import com.slabstech.apitestcontainer.model.User;
import com.slabstech.apitestcontainer.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Your application started with option names : {}", args.getOptionNames());

        userRepository.saveAll(Arrays.asList(User.builder()
                        .address(Address.builder()
                                .consignee("GLS")
                                .street("Kemperplatz")
                                .number("1")
                                .zip("10785")
                                .build())
                        .userName("1000")
                        .build(),
                User.builder()
                        .address(Address.builder()
                                .consignee("GLS")
                                .street("Kemperplatz")
                                .number("1")
                                .zip("10785")
                                .build())
                        .userName("1001")
                        .build(),
                User.builder()
                        .address(Address.builder()
                                .consignee("Max Mustermann")
                                .street("Weinbergsweg")
                                .number("2")
                                .zip("10119")
                                .build())
                        .userName("1002")

                        .build(),
                User.builder()
                        .address(Address.builder()
                                .consignee("Hans Meier")
                                .street("Weinbergsweg")
                                .number("3")
                                .zip("10119")
                                .build())
                        .userName("1003")
                        .build(),
                User.builder()
                        .address(Address.builder()
                                .consignee("GLS")
                                .street("Kemperplatz")
                                .number("1")
                                .zip("10785")
                                .build())
                        .userName("1000")
                        .build()));
    }
}

