package com.github.aaric.achieve.hbase;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * HbaseChapter04Test
 *
 * @author Aaric, created on 2017-10-26T09:09.
 * @since 1.0-SNAPSHOT
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HbaseChapter04Test {

    public static final String TABLE_NAME = "phone";

    @Autowired
    protected HbaseTemplate hbaseTemplate;

    protected Admin admin;
    protected Table table;

    @Before
    public void begin() throws IOException {
        Connection connection = ConnectionFactory.createConnection(hbaseTemplate.getConfiguration());
        admin = connection.getAdmin();
        table = connection.getTable(TableName.valueOf(TABLE_NAME));
    }

    @Test
    public void testCreateTable() throws IOException {
        if (!admin.tableExists(TableName.valueOf(TABLE_NAME))) {
            HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
            HColumnDescriptor family = new HColumnDescriptor("cf1");
            family.setBlockCacheEnabled(true);
            family.setInMemory(true);
            family.setMaxVersions(1);
            family.setTimeToLive(3 * 365 * (1000 * 60 * 60 * 24));  // 3 years
            desc.addFamily(family);

            admin.createTable(desc);
        }
    }

    @Test
    public void testInsert() {
        System.err.println(table);
    }
}
