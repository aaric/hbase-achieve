package com.sample.hbase.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * HBase配置
 *
 * @author Aaric, created on 2020-04-26T14:40.
 * @version 1.1.0-SNAPSHOT
 */
@Configuration
public class HBaseConfig {

    /**
     * HBase连接
     *
     * @return
     * @throws IOException
     */
    @Bean
    public Connection hbaseConnection() throws IOException {
        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "10.0.11.34,10.0.11.35,10.0.11.39");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.master", "10.0.11.35:60000");
        return ConnectionFactory.createConnection(configuration);
    }
}
