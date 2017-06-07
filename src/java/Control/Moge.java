/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entity.Emoge;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Altair
 */
public class Moge {
  private Emoge moge;
  private DBConnection DBC = new DBConnection();

    public Moge(int movieId, int genreId) {
        this.moge = new Emoge(movieId, genreId);
    }
  
  //gibt true zurück, wenn moge-Eintrag erstellt wurde
  public boolean insertMoge() throws SQLException {
      //moge eintragen
      DBC.executeQuery("INSERT INTO moge VALUES(" + moge.getMovieId() + ", " + moge.getGenreId() + ")");
      return true;
  }
  
  public ArrayList<Integer> getMovieIdsWithGenreId() throws SQLException{
        ResultSet rs = DBC.getRS("SELECT movieId FROM moge WHERE genreId = " + moge.getGenreId());
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        if(!rs.isBeforeFirst())
        {
            return null;
        } else {
          while (rs.next()) {
          result.add(rs.getInt(1));
         }
        return result;
        }
    }
}