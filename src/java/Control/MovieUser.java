/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entity.EmovieUser;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Altair
 */
public class MovieUser {
    private EmovieUser movieUser;
    private DBConnection DBC = new DBConnection();

    public MovieUser(String userName, String userPassword) {
        this.movieUser = new EmovieUser(0, userName, userPassword);
    }
  
  //gibt true zurück, wenn Benutzer erstellt wurde
  //gibt false zurück, wenn Benutzer bereits vorhanden ist
    public boolean insertMovieUser() throws SQLException {
        
        ResultSet rs;
        
        //mssql
        if(DBC.getDb_used().equals("ms")){
            rs = DBC.getRS("SELECT userName FROM movieUser");
        }
        //mysql
        else{
            rs = DBC.getRS("SELECT userName FROM movieuser");
        }
        
        while (rs.next()) {
            if(rs.getString(1).equals(movieUser.getUserName())) {
                //Username bereits vorhanden
                return false;
            }
        }
        //Benutzer eintragen
        //mssql
        if(DBC.getDb_used().equals("ms")){
            DBC.executeQuery("INSERT INTO movieUser VALUES('" + movieUser.getUserName() + "', " + movieUser.getUserPassword() + ")");   
        }
        //mysql
        else{
            DBC.executeQuery("INSERT INTO movieuser VALUES('" + movieUser.getUserName() + "', " + movieUser.getUserPassword() + ")");
        }
      return true;
  }
    public boolean userValidierung() throws SQLException{
       
        ResultSet rs;
         //mssql
        if(DBC.getDb_used().equals("ms")){
            rs = DBC.getRS("SELECT userPassword FROM movieUser WHERE userName = '" + movieUser.getUserName() + "'");
        }
        //mysql
        else{
            rs = DBC.getRS("SELECT userPassword FROM movieuser WHERE userName = '" + movieUser.getUserName() + "'");
        }
       
        //to avoid nullptr exception while trying to login with wrong username
        if(rs.next()){
            if(rs.getString(1).equals(movieUser.getUserPassword())) {
                return true;
            }
        }
          
        return false;
    }
    public int getUserIdByUserName() throws SQLException{
        ResultSet rs;
        //mssql
        if(DBC.getDb_used().equals("ms")){
            rs = DBC.getRS("SELECT userId FROM movieUser WHERE userName = '" + movieUser.getUserName() + "'");
        }
        //mysql
        else{
            rs = DBC.getRS("SELECT userId FROM movieuser WHERE userName = '" + movieUser.getUserName() + "'");
        }
        
        rs.next();
        
        return rs.getInt(1);
    }
    public String getUserNameByUserId(int userId) throws SQLException{
       
        ResultSet rs;
         //mssql
        if(DBC.getDb_used().equals("ms")){
            rs = DBC.getRS("SELECT userName FROM movieUser WHERE userId = " + userId + "");
        }
        //mysql
        else{
            rs = DBC.getRS("SELECT userName FROM movieuser WHERE userId = " + userId + "");
        }
        
        rs.next();
        
        return rs.getString(1);
    }
}