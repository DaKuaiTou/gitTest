package com.dkt.demo;

import java.sql.*;

/**
 * @Author: dkt
 * @Date: 2021/11/26 15:30
 */
public class JDBCDemo {
    /*
    *   原生的JDBC数据连接技术的步骤
    *
    *   1.注册驱动
    *   2.获得连接
    *   3.获取预编译sql语句对象
    *   4.设置参数，执行
    *   5.随便写
    * */
    public static void main (String[] args) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8",
                    "root", "rootroot");
            preparedStatement = connection.prepareStatement("select * from user where username = ?");
            preparedStatement.setString(1,"zhangsan");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("id")+","+resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
