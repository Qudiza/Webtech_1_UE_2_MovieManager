package Bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Control.Controller;
import Control.DBConnection;
import Control.Moge;
import Control.Movie;
import Control.MovieCharacter;
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
import javax.validation.constraints.Min;

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
    private String actor;
    private String genre;
    private String movieCharacter;
    
    
    
    private ArrayList<String> actorList;
    private ArrayList<String> genreList;
    
    //Actor
    private ArrayList<String> actorsToSave;
    private ArrayList<String> movieCharacterToSave;
    private ArrayList<String> outputString;
    
    //Genre
    private ArrayList<String> genresToSave;
    private ArrayList<String> outputStringGenre;
    
    private String message="";
    
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
        
        //Movie
        Movie movie = new Movie(title, releaseDate);
        movie.insertMovie();
        int movieId = movie.getMovieIdbyTitle();
        
        //Regisseur
        Regisseur reg = new Regisseur(regisseur);
        reg.insertRegisseur();
        int regId = reg.getRegisseurId();
        
        //MovieRegisseur
        MovieRegisseur more = new MovieRegisseur(regId, movieId);
        more.insertMovieRegisseur();
        
        //MovieCharacter
        for(int i=0;i<actorsToSave.size();i++){
            Actor a = new Actor(actorsToSave.get(i), 'A');
            int actorId = a.getActorIdByName();
            MovieCharacter moviechar = new MovieCharacter(movieCharacterToSave.get(i), actorId, movieId);
            moviechar.insertMovieCharacter();
        }
        
        for(String g: genresToSave){
            Genre genre = new Genre(g);
            int genreId = genre.getGenreIdByGenre();
            Moge moge = new Moge(movieId, genreId);
            moge.insertMoge();
        }
        resetInsertMovie();
        setMessage("Film gespeichert");
        
    }

   
    public ArrayList<String> getGenreList() {
        return genreList;
    }

    

    public void setGenreList(ArrayList<String> genreList) {
        this.genreList = genreList;
    }
    
   
    public ArrayList<String> getActorList() {
        return actorList;
    }

    
    public void setActorList(ArrayList<String> actorList) {
        this.actorList = actorList;
    }
    
    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        
            this.actor = actor;    
        
    }
    
    
    
    public void addGenreToList(String genreName){
        //genreList.add(new Genre(genreName));
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
        
        if(genreList == null){
            genreList = new ArrayList<>();    
        }
        try {
            
                rs = DBC.getRS("SELECT genre FROM genre");
                if(rs.isBeforeFirst()){
                }
                while(rs.next()){
        
                    String genreName = rs.getString("genre");
                    genreList.add(genreName);
                }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public boolean checkLoginValidation() throws SQLException {
        return CheckLogin.checkLoginValidation();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public ArrayList<String> getActorsToSave() {
        return actorsToSave;
    }

    public void setActorsToSave(ArrayList<String> actorsToSave) {
        this.actorsToSave = actorsToSave;
    }
    
    public void saveActor(){
        if(actorsToSave == null){
            actorsToSave = new ArrayList<String>();
            movieCharacterToSave = new ArrayList<String>();
            outputString = new ArrayList<String>();
        }
        if(actorsToSave.contains(actor)){
            
        }
        else{
            actorsToSave.add(actor);
            movieCharacterToSave.add(movieCharacter);
            if(movieCharacter.equals("")){
                outputString.add(actor);
            }
            else{
                outputString.add(actor+" als "+movieCharacter);    
            }
            movieCharacter="";
        }
    }
    
    public void saveGenre(){
        if(genresToSave == null){
            genresToSave = new ArrayList<String>();
            outputStringGenre = new ArrayList<String>();
        }
        if(genresToSave.contains(genre)){
            
        }
        else{
            genresToSave.add(genre);
            outputStringGenre.add(genre);    
            
            
        }
    }
    
    public void resetActor(){
        if(actorsToSave != null){
            actorsToSave = new ArrayList<String>();
            movieCharacterToSave = new ArrayList<String>();
            outputString = new ArrayList<String>();
        }
    }

    public void resetGenre(){
        if(genresToSave != null){
            genresToSave = new ArrayList<String>();
            outputStringGenre = new ArrayList<String>();
        }
    }
    
    public void resetInsertMovie(){
        resetActor();
        resetGenre();
        resetMovie();
    }
    
    public void resetMovie(){
        title="";
        regisseur="";
        releaseDate=0;
    }
    
    public String getMovieCharacter() {
        return movieCharacter;
    }

    public void setMovieCharacter(String movieCharacter) {
        this.movieCharacter = movieCharacter;
    }

    public ArrayList<String> getMovieCharacterToSave() {
        return movieCharacterToSave;
    }

    public void setMovieCharacterToSave(ArrayList<String> movieCharacterToSave) {
        this.movieCharacterToSave = movieCharacterToSave;
    }

    public ArrayList<String> getOutputString() {
        return outputString;
    }

    public void setOutputString(ArrayList<String> outputString) {
        this.outputString = outputString;
    }

    public String getGenre() {
        return genre;
    }

    public ArrayList<String> getGenresToSave() {
        return genresToSave;
    }

    public ArrayList<String> getOutputStringGenre() {
        return outputStringGenre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setGenresToSave(ArrayList<String> genresToSave) {
        this.genresToSave = genresToSave;
    }

    public void setOutputStringGenre(ArrayList<String> outputStringGenre) {
        this.outputStringGenre = outputStringGenre;
    }
    
    
}
