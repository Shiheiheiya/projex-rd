package com.dbly.ssm;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

public class unitTest {
    private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test1() throws SQLException {
        // 获取DataSource对象
        BasicDataSource bds = ac.getBean("dataSource", BasicDataSource.class);
        Connection conn = bds.getConnection();
        System.out.println(conn);

    }
}
