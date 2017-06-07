/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entity.Egenre;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Altair
 */
public class Genre {
    private Egenre genre;
    private DBConnection DBC = new DBConnection();

    public Genre(String genre) {
        this.genre = new Egenre(0, genre);
    }
  
  //gibt true zurück, wenn Genre erstellt wurde
  //gibt false zurück, wenn Genre bereits vorhanden ist
    public boolean insertGenre() throws SQLException {
      ResultSet rs = DBC.getRS("SELECT genre FROM genre");
      while (rs.next()) {
          if(rs.getString(1).equals(genre.getGenre())) {
              //Genre bereits vorhanden
              return false;
          }
         }
      //Genre eintragen
      DBC.executeQuery("INSERT INTO genre VALUES('" + genre.getGenre() + "')");
      return true;
  }
    
    public int getGenreIdByGenre() throws SQLException{
      ResultSet rs = DBC.getRS("SELECT genreId FROM genre WHERE genre = '" + genre.getGenre() + "'");
      
      if(!rs.isBeforeFirst())
        {
            return 0;
        } else {
          rs.next();
      return rs.getInt(1);
      }
    }
}