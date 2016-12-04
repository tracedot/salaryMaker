package SalarySheet;

import jdk.nashorn.internal.runtime.Version;

import java.io.File;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

/**
 * Created by famed on 5/6/16.
 */
public class SqlConnect {

    public static Connection con() throws ClassNotFoundException, SQLException {
/*

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Class.forName("com.mysql.jdbc.Driver");


        String url = "jdbc:mysql://localhost:3306/SalarySheet?useUnicode=yes&characterEncoding=UTF-8";
        String user = "root";
        String password = "admin";
        */
        String dbfile = (new File("").getAbsolutePath()+"/salarymakerfile/db/salarysheet");
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Class.forName("org.h2.Driver");

        System.out.println(dbfile);
        String url = "jdbc:h2:"+dbfile; //For unicode ?useUnicode=yes&characterEncoding=UTF-8
        String user = "root";
        String password = "admin";


        try {
            con = DriverManager.getConnection(url, user, password);


        } catch (SQLException ex) {

            ConnectionError connectionError = new ConnectionError();
            connectionError.alertError();
            System.out.println(ex);




        }




        return con;
    }


}
