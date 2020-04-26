package com.sample.hbase;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.Instant;

/**
 * 启动类
 *
 * @author Aaric, created on 2020-04-26T14:16.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
@SpringBootApplication
public class App implements CommandLineRunner {

    /**
     * Main
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "127.0.0.1");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.master", "127.0.0.1:60000");

        String rowKey = "key_" + Instant.now().toEpochMilli();
        try {
            Connection connection = ConnectionFactory.createConnection(configuration);
            Table table = connection.getTable(TableName.valueOf("test"));
            Put put = new Put(rowKey.getBytes());
            put.addColumn(Bytes.toBytes("base"), Bytes.toBytes("data"), Bytes.toBytes("hello world"));

            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("save rowKey: {}", rowKey);
    }
}
