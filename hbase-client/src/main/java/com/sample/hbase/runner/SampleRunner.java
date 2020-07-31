package com.sample.hbase.runner;

import lombok.extern.log4j.Log4j2;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

/**
 * 测试
 *
 * @author Aaric, created on 2020-07-31T13:52.
 * @version 1.2.0-SNAPSHOT
 */
@Log4j2
@Order(1)
@Component
public class SampleRunner implements CommandLineRunner {

    @Autowired
    private Connection bigTableConnection;

    @Value("${hbase.table.test}")
    private String testTableName;

    @Override
    public void run(String... args) throws Exception {
        String rowKey = "key_" + Instant.now().toEpochMilli();
        try {
            Table table = bigTableConnection.getTable(TableName.valueOf(testTableName));
            Put put = new Put(rowKey.getBytes());
            put.addColumn(Bytes.toBytes("base"), Bytes.toBytes("data"), Bytes.toBytes("hello world"));

            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("save rowKey: {}", rowKey);
    }
}
