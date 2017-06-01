/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entity.EmovieCollection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Altair
 */
public class MovieCollection {
    private EmovieCollection movieCollection;
    private DBConnection DBC = new DBConnection();

    public MovieCollection(int userId, int movieId) {
        this.movieCollection = new EmovieCollection(0, userId, movieId);
    }
  
  //gibt true zurück, wenn Eintrag in MovieCollection erstellt wurde
  
    public boolean insertMovieCollection() throws SQLException {
        //Eintrag in MovieCollection eintragen
        //mssql
        if(DBC.getDb_used().equals("ms")){
            DBC.executeQuery("INSERT INTO movieCollection VALUES('" + movieCollection.getUserId() + "', " + movieCollection.getMovieId() + ")");
        }
        //mysql
        else{
            DBC.executeQuery("INSERT INTO moviecollection VALUES('" + movieCollection.getUserId() + "', " + movieCollection.getMovieId() + ")");
        }
        return true;
  }
    public ArrayList<ArrayList<String>> getMovieCollectionByUserId(int userId) throws SQLException{
        
        ArrayList<ArrayList<String>> collection = new ArrayList<ArrayList<String>>(2);
        
        String query = "SELECT title, releaseDate FROM movie where movieId in ( ";
        ResultSet rs;
         //mssql
        if(DBC.getDb_used().equals("ms")){
            rs= DBC.getRS("SELECT movieId FROM movieCollection WHERE userId = " + movieCollection.getUserId());
        }
        //mysql
        else{
            rs= DBC.getRS("SELECT movieId FROM moviecollection WHERE userId = " + movieCollection.getUserId());
        }

        boolean hasMovies = false;
        while(rs.next()){
            query += rs.getString(1) + ",";
            hasMovies = true;
        }
        query = query.substring(0,query.length()-1);
        query += ")";
        
        if(hasMovies) {
            //Liste mit Filmen der Collection des Benutzers
        rs = DBC.getRS(query);
        int i = 0;
        
        while(rs.next()){
            collection.add(new ArrayList<>());

            collection.get(i).add(rs.getString(1));
            collection.get(i).add(Integer.toString(rs.getInt(2)));

            i++;
        }
        return collection;
        }
        return null;
    }  
}