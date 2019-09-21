package com.fit.h2;

import org.h2.tools.Server;

import java.sql.*;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-08-29
 */
public class H2Server {

    private Server server;

    public static void main(String[] args) {
        H2Server h2Server = new H2Server();
        h2Server.start();
        h2Server.crudTest();
        h2Server.stop();
    }


    public void start() {
        try {
            System.out.println("正在启动h2...");
            server = Server.createTcpServer(new String[]{"-tcp", "-tcpAllowOthers", "-tcpPort", "8043"}).start();
            System.out.println("启动成功：" + server.getStatus());
        } catch (SQLException e) {
            System.out.println("启动h2出错：" + e.toString());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        if (server != null) {
            System.out.println("正在关闭h2...");
            server.stop();
            System.out.println("关闭成功.");
        }
    }

    public void crudTest() {
        try {
            Class.forName("org.h2.Driver");
            // connect to h2
            Connection conn = DriverManager.getConnection("jdbc:h2:./h2db/resource", "sa", "sa");
            Statement stat = conn.createStatement();
            // create table
            stat.execute("CREATE TABLE TEST(NAME VARCHAR)");
            // insert table
            stat.execute("INSERT INTO TEST VALUES('Stone')");
            stat.execute("INSERT INTO TEST VALUES('Brave')");
            // retrive data
            ResultSet result = stat.executeQuery("select name from test ");
            int i = 1;
            while (result.next()) {
                System.out.println(i++ + "-->" + result.getString("name"));
            }
            // drop table
            stat.execute("DROP TABLE TEST");
            result.close();
            stat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
