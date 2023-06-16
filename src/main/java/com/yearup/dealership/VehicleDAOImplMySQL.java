package com.yearup.dealership;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Properties;

public class VehicleDAOImplMySQL {

    private static final String PROPERTIES_FILE = "database.properties";

    private static BasicDataSource dataSource = new BasicDataSource();

    static {

        Properties properties = loadProperties();
        String driverClass = properties.getProperty("driverClass");

        String mysqlUrl = properties.getProperty("databaseUrl");
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(mysqlUrl);
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
    }
}
