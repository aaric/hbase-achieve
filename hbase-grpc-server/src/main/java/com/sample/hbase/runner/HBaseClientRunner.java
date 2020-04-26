package com.sample.hbase.runner;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * HBase客户端启动类
 *
 * @author Aaric, created on 2020-04-26T14:48.
 * @version 1.1.0-SNAPSHOT
 */
@Slf4j
@Order(1)
@Component
public class HBaseClientRunner implements CommandLineRunner {

    @Autowired
    private Connection hbaseConnection;

    @Override
    public void run(String... args) throws Exception {
        // 操作表格对象
        Table table = hbaseConnection.getTable(TableName.valueOf("zsgroup:telemetry"));

        // 插入一条数据
        String rowKey = "key_" + Instant.now().toEpochMilli();
        Put put = new Put(rowKey.getBytes());
        put.addColumn(Bytes.toBytes("base"), Bytes.toBytes("data"), Bytes.toBytes("hello world"));
        table.put(put);

        // 打印日志
        log.info("save rowKey: {}", rowKey);
    }
}
