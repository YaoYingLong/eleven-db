package com.eleven.icode.es_sql;

import org.elasticsearch.xpack.sql.jdbc.EsDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 使用JDBC来操作ES
 */
public class JdbcExample {
    public static void main(String[] args) throws Exception {
        // 1. 加载ES驱动
        Class.forName(EsDriver.class.getName());
        // 2. 建立连接
        Connection connection = DriverManager.getConnection("jdbc:es://http://localhost:9200");
        // 3. 准备SQL语句
        String sql = "select job_type, count(*) cnt from job_index group by job_type";
        // 4. 使用PreparedStatement执行SQL
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        // 5. 遍历结果
        while (resultSet.next()) {
            byte jobType = resultSet.getByte("job_type");
            long cnt = resultSet.getLong("cnt");
            System.out.println("方式：" + jobType + " 数量：" + cnt);
        }
        // 6. 关闭连接
        resultSet.close();
        connection.close();
    }
}
