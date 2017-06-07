/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Control.Controller;
import Control.ManageSessionId;
import Control.Movie;
import Control.MovieCollection;
import Control.Search;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.validation.constraints.Min;

/**
 *
 * @author Altair
 */
@Named(value = "searchMovie")
@SessionScoped
//@RequestScoped            //achtung, wenn RequestScoped aktiviert ist, können keine Filme mehr der Sammlung hinzugefügt werden

public class SearchMovie implements Serializable {
    
    private ArrayList<ArrayList<String>> movieList = new ArrayList<ArrayList<String>>();
    private String regisseur, actor, genre, operator;
    private boolean hasMovies = false, initialise = true;
    private int movieListSize = 0;
    
    //@Min(1888)            // kann nicht verwendet werden, da releaseDate auch null sein darf (bei und-Verknüpfung ohne releaseDate zu kennen unmöglich)
    //@Max(getYear())       //geht nicht, weil @Max(value)  value eine Konstante sein muss.
    private int releaseDate;
    
    /**
     * Creates a new instance of SearchMovie
     */
    public SearchMovie() throws SQLException {
        
        if(regisseur == null) {
                regisseur = "";
            }
        if(actor == null) {
            actor = "";
        }
        if(genre == null) {
            genre="";
        }
       if(operator == null) {
            operator="OR";
       }
        
        if(initialise == false) {
         generateSearchMovie();
        }
        else {
        }
    }

    public void generateSearchMovie() throws SQLException {
        Search s = new Search(regisseur, releaseDate, actor, genre, operator, initialise);

        if(s.getMovieListBySearchQuery(s.generateQuery()) != null) {
            movieList = s.getMovieListBySearchQuery(s.generateQuery());
            hasMovies = true;
        } else {
            hasMovies = false;
        }
        movieListSize = movieList.size();
    }
    
    public int getMovieListSize() {
        return movieListSize;
    }
    
    public String showMovieListItem(int outer, int inner) throws SQLException {
        return movieList.get(outer).get(inner);
    }
    
    public void addToCollection(int i) throws SQLException {
        ManageSessionId MSId = new ManageSessionId(Controller.getActualSessionId());
        Movie mov = new Movie((movieList.get(i).get(0)), 1900);
        MovieCollection movcol = new MovieCollection(MSId.getUserIdBySessionId(), mov.getMovieIdbyTitle());
        movcol.insertMovieCollection();
    }
    
    public String fillFormularData() throws SQLException{
        initialise = false;
        generateSearchMovie();
        
        return "searchMovie";
    }

        public String searchMovie() {
        return "searchMovie";
    }
    
    public String getRegisseur() {
        return regisseur;
    }
    public String getActor() {
        return actor;
    }
    public String getGenre() {
        return genre;
    }
    public String getOperator() {
        return operator;
    }
    public boolean isHasMovies() {
        return hasMovies;
    }
    public void setRegisseur(String regisseur) {
        this.regisseur = regisseur;
    }
    public void setActor(String actor) {
        this.actor = actor;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public void setHasMovies(boolean hasMovies) {
        this.hasMovies = hasMovies;
    }
    public void setMovieListSize(int movieListSize) {
        this.movieListSize = movieListSize;
    }
    public int getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String goToCollection() {
        return "collection";
    }
//    @deprecated    
//    public int getYear(){
//        Date date = new Date();
//        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
//        
//        return year;
//    }
    public boolean checkLoginValidation() throws SQLException {
        return CheckLogin.checkLoginValidation();
    }
}