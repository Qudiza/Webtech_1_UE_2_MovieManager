/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entity.Emovie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Altair
 */
public class Movie {
    private Emovie movie;
    private DBConnection DBC = new DBConnection();

    public Movie(String title, int releaseDate) {
        this.movie = new Emovie(0, title, releaseDate);
    }
  
  //gibt true zurück, wenn Film erstellt wurde
  //gibt false zurück, wenn Film bereits vorhanden ist
    public boolean insertMovie() throws SQLException {
      ResultSet rs = DBC.getRS("SELECT title FROM movie");
      while (rs.next()) {
          if(rs.getString(1).equals(movie.getTitle())) {
              //Film bereits vorhanden
              return false;
          }
         }
      //Film eintragen
      DBC.executeQuery("INSERT INTO movie(title, releaseDate) VALUES('" + movie.getTitle() + "', " + movie.getReleaseDate() + ")");
      return true;
  }
    public ArrayList<Integer> getMovieIdsWithReleaseDate() throws SQLException{
        ResultSet rs = DBC.getRS("SELECT movieId FROM movie WHERE releaseDate = " + movie.getReleaseDate());
        ArrayList<Integer> result = new ArrayList<>();
        if(!rs.isBeforeFirst()) {
            return null;
        } else {
            while (rs.next()) {
          result.add(rs.getInt(1));
         }
        return result;
        }
    }
    
    public int getMovieIdbyTitle() throws SQLException{
        ResultSet rs = DBC.getRS("SELECT movieId FROM movie WHERE title = '" + movie.getTitle() + "'");
        if(!rs.isBeforeFirst()) {
            return 0;
        } else {
            rs.next();
            return rs.getInt(1);
        }
    }
    
}