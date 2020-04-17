package com.yhy.gmall.pms.config;


import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@Configuration
public class PmsDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        File yamlFile = null;
        DataSource dataSource = null;
        try {
            yamlFile = ResourceUtils.getFile("classpath:sharding-jdbc.yml");
            dataSource = MasterSlaveDataSourceFactory.createDataSource(yamlFile);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
