/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Control.DBConnection;
import Entity.Eactor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Altair
 */

@ManagedBean
@ViewScoped
public class Actor {
    private ArrayList<Eactor> actors;
    private DBConnection DBC = new DBConnection();
    private Eactor actor;
    
    private String message;
    
    public Actor(String actorName, char gender) {
          this.actor = new Eactor(0, actorName, gender);
      }

    public Actor(){

    }

    //gibt true zurück, wenn Schauspieler erstellt wurde
    //gibt false zurück, wenn Schauspieler bereits vorhanden ist
    public boolean insertActor() {

        for (Eactor a : actors) {
            try {
                ResultSet rs = DBC.getRS("SELECT * FROM actor WHERE actorName = '"+a.getActorName()+"'");
                if(rs.isBeforeFirst()){
                    //Schauspieler bereits vorhanden
                }
                else{
                    //Schauspieler eintragen
                    DBC.executeQuery("INSERT INTO actor(actorName, gender) VALUES( '"+a.getActorName()+"', '"+a.getGender()+"' )");
                }
                /*
                if(rs.getString(1).equals(a.getActorName())) {
                //Schauspieler bereits vorhanden
                }
                
                //Schauspieler eintragen
                DBC.executeQuery("INSERT INTO actor VALUES('" + actor.getActorName() + "', '" + actor.getGender() + "')");
                */
            } catch (SQLException ex) {
                Logger.getLogger(Actor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          return true;
    }

    public int getActorIdByName() throws SQLException{
        
        ResultSet rs = DBC.getRS("SELECT actorId FROM actor WHERE actorName = '" + actor.getActorName() + "'");
        
        if(!rs.isBeforeFirst())
        {
            return 0;
        } else {
            rs.next();
        return rs.getInt(1);
        }
     }
  
    @PostConstruct
    public void init() {
        actors = new ArrayList<Eactor>();
    }
  
    public void add() {
        actors.add(new Eactor());
        setMessage("");
    }

    public void remove(Eactor actorItem) {
        actors.remove(actorItem);
    }

    //TODO: ändern auf speichern der Werte
    public void save() {
        insertActor();
        setMessage("Die Schauspieler wurden eingetragen");
        //reset the list of Eactors
        init();
    }
    
    public ArrayList<Eactor> getItems() {
        return actors;
    }
    
    public ArrayList<Eactor> getActors(){
        return actors;
    }
  
    public void setMessage(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return message;
    }
}