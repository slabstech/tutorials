package com.slabstech.apitestcontainer.util;

import org.testcontainers.containers.PostgreSQLContainer;

public class SLabsPostgresqlContainer extends PostgreSQLContainer<SLabsPostgresqlContainer> {

    private static final String IMAGE_VERSION = "postgres:alpine3.18";

    private static SLabsPostgresqlContainer container;


    private SLabsPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static SLabsPostgresqlContainer getInstance() {
        if (container == null) {
            container = new SLabsPostgresqlContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}

