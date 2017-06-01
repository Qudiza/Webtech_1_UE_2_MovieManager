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
     
        //mssql
        if(DBC.getDb_used().equals("ms")){
            DBC.executeQuery("INSERT INTO movieRegisseur VALUES('" + movieRegisseur.getRegisseurId() + "', " + movieRegisseur.getMovieId() + ")");
        }
        //mysql
        else{
            DBC.executeQuery("INSERT INTO movieregisseur VALUES('" + movieRegisseur.getRegisseurId() + "', " + movieRegisseur.getMovieId() + ")");
        }
     
      return true;
  }
    public ArrayList<Integer> getMovieIdsWithRegisseur() throws SQLException{
        
        ResultSet rs;
        //mssql
        if(DBC.getDb_used().equals("ms")){
            rs = DBC.getRS("SELECT movieId FROM movieRegisseur WHERE regisseurId = " + movieRegisseur.getRegisseurId());
        }
        //mysql
        else{
            rs = DBC.getRS("SELECT movieId FROM movieregisseur WHERE regisseurId = " + movieRegisseur.getRegisseurId());
        }
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (rs.next()) {
          result.add(rs.getInt(1));
         }
        return result;
    }
}