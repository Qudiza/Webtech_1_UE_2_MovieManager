/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entity.Eactor;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Altair
 */
public class Actor {
  private Eactor actor;
  private DBConnection DBC = new DBConnection();

    public Actor(String actorName, char gender) {
        this.actor = new Eactor(0, actorName, gender);
    }
  
  //gibt true zurück, wenn Schauspieler erstellt wurde
  //gibt false zurück, wenn Schauspieler bereits vorhanden ist
  public boolean insertActor() throws SQLException {
      
      
      ResultSet rs = DBC.getRS("SELECT actorName FROM actor");
      while (rs.next()) {
          if(rs.getString(1).equals(actor.getActorName())) {
              //Schauspieler bereits vorhanden
              return false;
          }
         }
      //Schauspieler eintragen
      DBC.executeQuery("INSERT INTO actor VALUES('" + actor.getActorName() + "', '" + actor.getGender() + "')");
      return true;
  }
  
  public int getActorIdByName() throws SQLException{
      ResultSet rs = DBC.getRS("SELECT actorId FROM actor WHERE actorName = '" + actor.getActorName() + "'");
      rs.next();
      return rs.getInt(1);
  }
}