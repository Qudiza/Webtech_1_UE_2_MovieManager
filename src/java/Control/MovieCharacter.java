/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entity.EmovieCharacter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Altair
 */
public class MovieCharacter {
    private EmovieCharacter movieCharacter;
    private DBConnection DBC = new DBConnection();

    public MovieCharacter(String characterName, int actorId, int movieId) {
        this.movieCharacter = new EmovieCharacter(characterName, actorId, movieId);
    }
  
  //gibt true zurück, wenn MovieCharacter erstellt wurde
  //gibt false zurück, wenn MovieCharacter bereits vorhanden ist
    public boolean insertMovieCharacter() throws SQLException {
        
        ResultSet rs;
        
        //mssql
        if(DBC.getDb_used().equals("ms")){
            rs = DBC.getRS("SELECT characterName FROM movieCharacter");
        }
        //mysql
        else{
            rs = DBC.getRS("SELECT characterName FROM moviecharacter");
        }
        while (rs.next()) {
            if(rs.getString(1).equals(movieCharacter.getCharacterName())) {
                //Film bereits vorhanden
                return false;
            }
        }
        
        //mssql
        if(DBC.getDb_used().equals("ms")){
            DBC.executeQuery("INSERT INTO movieCharacter VALUES('" + movieCharacter.getCharacterName() + "', " + movieCharacter.getActorId() + ", " + movieCharacter.getMovieId() + ")");
        }
        //mysql
        else{
            DBC.executeQuery("INSERT INTO moviecharacter(characterName, actorId, movieId) VALUES('" + movieCharacter.getCharacterName() + "', " + movieCharacter.getActorId() + ", " + movieCharacter.getMovieId() + ")");
        }
        //Film eintragen
        
        return true;
  }
    
    public ArrayList<Integer> getMovieIdsByCharacter() throws SQLException{
        
        ResultSet rs;
        
        //mssql
        if(DBC.getDb_used().equals("ms")){
            rs = DBC.getRS("SELECT movieId FROM movieCharacter WHERE actorId = " + movieCharacter.getActorId());
        }
        //mysql
        else{
            rs = DBC.getRS("SELECT movieId FROM moviecharacter WHERE actorId = " + movieCharacter.getActorId());
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        while (rs.next()) {
          result.add(rs.getInt(1));
         }
        return result;
    }
}