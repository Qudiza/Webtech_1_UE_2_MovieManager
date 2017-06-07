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
    
    //look at constructor
    private String db_connect_string;
    
    //same values for dbsystems
    private final String db_userid = "moviesa";
    private final String db_password = "1234";
    
    //change this var to your dbsystem used : my or ms
    private final String db_used = "ms";
    //change this var to your dbsystem used : moviedb or MovieDB
    private final String db_name = "MovieDB";
    
    private Connection getDBConnection()
    {
        //mssql
        if(this.getDb_used().equals("ms")){
            
            db_connect_string = "jdbc:sqlserver://localhost";
            try {
          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
          Connection conn = DriverManager.getConnection(db_connect_string,
                   db_userid, db_password);
          return conn;
       } catch (Exception e) {
          e.printStackTrace();
       }
      }
        //mysql
        if(this.getDb_used().equals("my")){
            
            db_connect_string = "jdbc:mysql://localhost:3306/moviedb";
            
            try {
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn = DriverManager.getConnection(db_connect_string,
                      db_userid, db_password);
             return conn;
          } catch (Exception e) {
                System.out.println("exception bei DBConnection try");
             e.printStackTrace();
          }
      }
        return null;
    }

    public ResultSet getRS(String query)
    {
        Connection conn = getDBConnection();
        try{
            Statement statement = conn.createStatement();
            query = "USE "+this.getDb_name() + " " + query;
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
            query = "USE "+this.getDb_name() + " " + query;
            statement.executeUpdate(query);
        
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        return false;
    }
    
    public String getDb_used() {
        return db_used;
    }
    
    public String getDb_name(){
        return db_name;
    }
}