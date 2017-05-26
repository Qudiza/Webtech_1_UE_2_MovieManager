/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Control.Controller;
import Control.Movie;
import Control.MovieRegisseur;
import Control.Regisseur;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Altair
 */
@Named(value = "insertMovie")
@Dependent

public class InsertMovie {
public String title;
public int releaseDate;
public String regisseur;
public ArrayList<String> actorList;
public ArrayList<String> genreList;

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
    public String searchMovie() {
        return "SearchMovie";
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
        
        for(int i = 0; i < actorList.size();i++) {
            
        }
        for(int i = 0; i < genreList.size();i++) {
            
        }
        
    }
    
    public void newActor(){
        actorList = new ArrayList<>();
    }
    public void newGenre(){
        genreList = new ArrayList<>();
    }
}
