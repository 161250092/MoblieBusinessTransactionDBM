package DataServiceImpl;

import java.sql.*;

public class DBUtil {
    private static Connection conn = null;
    private final static String URL = "jdbc:sqlserver://localhost:3306;DatabaseName=moliebussinessDB";
    private static final String USER="root";
    private static final String PASSWORD="123456";

    //静态代码块（将加载驱动、连接数据库放入静态块中）
    static{
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得数据库的连接
            conn=(Connection)DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //对外提供一个方法来获取数据库连接
    public static Connection getConnection(){
        return conn;
    }



}
