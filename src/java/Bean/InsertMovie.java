package Bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Control.Controller;
import Control.DBConnection;
import Control.Movie;
import Control.MovieRegisseur;
import Control.Regisseur;
import Entity.Eactor;
import Entity.Egenre;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Altair
 */
@Named(value = "insertMovie")

@SessionScoped

public class InsertMovie implements Serializable {
    private DBConnection DBC = new DBConnection();
    private String title;
    private int releaseDate;
    private String regisseur;
    private ArrayList<String> actor;

    private ArrayList<ArrayList<String>> actorList;
    private ArrayList<Genre> genreList;
    
    
    private String debugMessage="";
    
    /**
     * Creates a new instance of InsertMovie
     */
    public InsertMovie() {
        
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRegisseur() {
        return regisseur;
    }

    public void setRegisseur(String regisseur) {
        this.regisseur = regisseur;
    }
    
    public String createMovie() {
        return "insertMovie";    
    }
    
    public String Logout() {
        Controller.destroyActualSessionId();
        return "index";
    }
   
    public void insertMovie() throws SQLException{
        //Hier werden die Daten ausgelesen und in die DB eingetragen
        Movie movie = new Movie(title, releaseDate);
        int movieId = movie.getMovieIdbyTitle();
        
        Regisseur reg = new Regisseur(regisseur);
        reg.insertRegisseur();
        int regId = reg.getRegisseurId();
        
        MovieRegisseur more = new MovieRegisseur(regId, movieId);
        more.insertMovieRegisseur();
        
        
        
    }

   
    public ArrayList<Genre> getGenreList() {
        return genreList;
    }

    

    public void setGenreList(ArrayList<Genre> genreList) {
        this.genreList = genreList;
    }
    
   
    public ArrayList<String> getActorList() {
        return actorList;
    }

    
    public void setActorList(ArrayList<String> actorList) {
        this.actorList = actorList;
    }
    
    public ArrayList<String> getActor() {
        return actor;
    }

    public void setActor(ArrayList<String> actor) {
        
            this.actor = actor;    
        
    }
    
    
    
    public void addGenreToList(String genreName){
        genreList.add(new Genre(genreName));
    }
    
    @PostConstruct
    public void init(){
        if(actorList == null){
            actorList = new ArrayList<>();    
        }
        ResultSet rs;
        try {
            
                rs = DBC.getRS("SELECT actorName FROM actor");
                if(rs.isBeforeFirst()){
                }
                while(rs.next()){
        
                    String actorName = rs.getString("actorName");
                    actorList.add(actorName);
                }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean checkLoginValidation() throws SQLException {
        return CheckLogin.checkLoginValidation();
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
    
    
}
