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
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Altair
 */
@Named(value = "searchMovie")
@Dependent
public class SearchMovie implements Serializable {
private ArrayList<ArrayList<String>> movieList = new ArrayList<ArrayList<String>>();

@ManagedProperty(value = "") private String regisseur;
@ManagedProperty(value = "") private String actor;
@ManagedProperty(value = "") private String genre;
@ManagedProperty(value = "OR") private String operator;
@ManagedProperty(value = "") private int releaseDate;

public boolean hasMovies = false;
public int movieListSize = 0;
    
    /**
     * Creates a new instance of SearchMovie
     */
    public SearchMovie() throws SQLException {
        Search s = new Search(regisseur, releaseDate, actor, genre, operator);
        System.out.println("************************ IN SearchMovie ************************");
        if(s.getMovieListBySearchQuery(s.generateQuery()) != null) {
            System.out.println("************************ IN IF ************************");
            movieList = s.getMovieListBySearchQuery(s.generateQuery());
            hasMovies = true;
        } else {
            hasMovies = false;
        }
        movieListSize = movieList.size();
    }

    public int getMovieListSize() {
        System.out.println("************************ IN getMovieListSize ************************");
        return movieListSize;
    }
    
    public String showMovieListItem(int outer, int inner) throws SQLException {
        System.out.println("************************ IN showMovieListItem ************************");
        return movieList.get(outer).get(inner);
    }
    
    public void addToCollection(int i) throws SQLException {
        System.out.println("************************ IN addToCollection ************************");
        ManageSessionId MSId = new ManageSessionId(Controller.getActualSessionId());
        Movie mov = new Movie((movieList.get(i).get(0)), 1900);
        MovieCollection movcol = new MovieCollection(MSId.getUserIdBySessionId(), mov.getMovieIdbyTitle());
        
        movcol.insertMovieCollection();
    }
    
    public void fillFormularData(){
        System.out.println("************************ IN fillFormularData ************************");
        
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

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setHasMovies(boolean hasMovies) {
        this.hasMovies = hasMovies;
    }

    public void setMovieListSize(int movieListSize) {
        this.movieListSize = movieListSize;
    }
    
    
    
    
    
}