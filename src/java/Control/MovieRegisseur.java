/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entity.EmovieRegisseur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Altair
 */
public class MovieRegisseur {
    private EmovieRegisseur movieRegisseur;
    private DBConnection DBC = new DBConnection();

    public MovieRegisseur(int regisseurId, int movieId) {
        this.movieRegisseur = new EmovieRegisseur(regisseurId, movieId);
    }
  
  //gibt true zurück, wenn Eintrag in movieRegisseur erstellt wurde
    public boolean insertMovieRegisseur() throws SQLException {
     //Film eintragen
     DBC.executeQuery("INSERT INTO movieRegisseur VALUES('" + movieRegisseur.getRegisseurId() + "', " + movieRegisseur.getMovieId() + ")");
      return true;
  }
    public ArrayList<Integer> getMovieIdsWithRegisseur() throws SQLException{
        ResultSet rs = DBC.getRS("SELECT movieId FROM movieRegisseur WHERE regisseurId = " + movieRegisseur.getRegisseurId());
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (rs.next()) {
          result.add(rs.getInt(1));
         }
        return result;
    }
}