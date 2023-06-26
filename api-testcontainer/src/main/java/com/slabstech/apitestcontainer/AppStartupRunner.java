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
                        .name("1000")
                        .email("test@test.com")
                        .build(),
                User.builder()
                        .address(Address.builder()
                                .consignee("GLS")
                                .street("Kemperplatz")
                                .number("1")
                                .zip("10785")
                                .build())
                        .name("1001")
                        .email("test2@test.com")
                        .build(),
                User.builder()
                        .address(Address.builder()
                                .consignee("Max Mustermann")
                                .street("Weinbergsweg")
                                .number("2")
                                .zip("10119")
                                .build())
                        .name("1002")
                        .email("test3@test.com")
                        .build(),
                User.builder()
                        .address(Address.builder()
                                .consignee("Hans Meier")
                                .street("Weinbergsweg")
                                .number("3")
                                .zip("10119")
                                .build())
                        .name("1003")
                        .email("test4@test.com")
                        .build(),
                User.builder()
                        .address(Address.builder()
                                .consignee("GLS")
                                .street("Kemperplatz")
                                .number("1")
                                .zip("10785")
                                .build())
                        .name("1000")
                        .email("test5@test.com")
                        .build()));
    }
}

