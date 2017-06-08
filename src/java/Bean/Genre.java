/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Control.DBConnection;
import Entity.Egenre;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Altair
 */
@ManagedBean
@ViewScoped
public class Genre {
    private Egenre genre;
    private DBConnection DBC = new DBConnection();

    private String message;
    private String suggestion;
    String name="";
    
    public Genre(String genre) {
        this.genre = new Egenre(0, genre);
    }
    
    public Genre(){
        
    }
  
  //gibt true zurück, wenn Genre erstellt wurde
  //gibt false zurück, wenn Genre bereits vorhanden ist
    public void insertGenre() throws SQLException {
      String genreName = genre.getGenre().substring(0,1).toUpperCase() + genre.getGenre().substring(1).toLowerCase();
      ResultSet rs = DBC.getRS("SELECT * FROM genre WHERE genre = '"+genreName+"' ");
      if(rs.isBeforeFirst()){
          //Genre bereits vorhanden
      }
      else{
        //Genre eintragen
        DBC.executeQuery("INSERT INTO genre(genre) VALUES('"+genreName+"')");  
      }
      genre.setGenre("");
      setMessage("Genre eingefügt");
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
    
    public Egenre getGenre(){
        return genre;
    }
    
    public void setGenre(Egenre genre){
        this.genre = genre;
    }
    
    @PostConstruct
    public void init() {
        genre = new Egenre();
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setMessage(String message){
        this.message = message;
    }

    public String getSuggestion() {
        if(name.equals("")){
            setSuggestion("");
        }
        else{
            setSuggestion(getName()+", ich empfehle das Genre: Splatter");
        }
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}