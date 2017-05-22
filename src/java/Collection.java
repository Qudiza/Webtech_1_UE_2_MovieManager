/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Control.Controller;
import Control.ManageSessionId;
import Control.MovieCollection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


/**
 *
 * @author Altair
 */
@Named(value = "collection")

@Dependent
public class Collection {
public ArrayList<ArrayList<String>> collectionList = new ArrayList<ArrayList<String>>();
public boolean collectionHasMovies = false;
public int collectionListSize = 0;

    public void setCollectionHasMovies(boolean collectionHasMovies) {
        this.collectionHasMovies = collectionHasMovies;
    }
    public boolean isCollectionHasMovies() {
        return collectionHasMovies;
    }
    public ArrayList<ArrayList<String>> getCollectionList() {
        return collectionList;
    }
    public void setCollectionList(ArrayList<ArrayList<String>> collectionList) {
        this.collectionList = collectionList;
    }
    /**
     * Creates a new instance of Collection
     */
    public Collection() {
        
    }
    
    public void generateCollection(){
        
        //Anhand der SessionId die userId herausfinden um Collection-Objekt fuer Benutzer anzulegen
        ManageSessionId MSId = new ManageSessionId(Controller.getActualSessionId());

        try {
            MovieCollection moviecollection;
            moviecollection = new MovieCollection(MSId.getUserIdBySessionId(), 0);
            collectionList = moviecollection.getMovieCollectionByUserId();
            collectionHasMovies = collectionHasMovies();
            collectionListSize = collectionList.size();
        } catch (SQLException ex) {
            Logger.getLogger(Collection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public String navigateToCollection(){
        return"collection";
    }
    //Liefert true zurück, wenn die Filmesammlung Filme enthält.
    public boolean collectionHasMovies(){
        System.out.println(!collectionList.isEmpty());
        return !collectionList.isEmpty();
    }
}