package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Altair
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    
    private final String db_connect_string = "jdbc:sqlserver://localhost";
    private final String db_userid = "moviesa";
    private final String db_password = "1234";
    
    private Connection getDBConnection()
    {
        try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         Connection conn = DriverManager.getConnection(db_connect_string,
                  db_userid, db_password);
         return conn;
      } catch (Exception e) {
         e.printStackTrace();
      }
        return null;
    }

    public ResultSet getRS(String query)
    {
        Connection conn = getDBConnection();
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
        
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean executeQuery(String query) {
        Connection conn = getDBConnection();
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        return false;
    }
}