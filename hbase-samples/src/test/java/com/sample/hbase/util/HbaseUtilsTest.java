package com.sample.hbase.util;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * HbaseUtils测试类
 *
 * @author Aaric, created on 2017-05-24T18:04.
 * @since 1.0-SNAPSHOT
 */
public class HbaseUtilsTest {

    @Test
    @Disabled
    public void testGeneratePrimaryKey() throws Exception {
        System.out.println(HbaseUtils.generatePrimaryKey(12345));
    }
}
