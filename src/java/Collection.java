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

    public boolean isCollectionHasMovies() {
        return collectionHasMovies;
    }
    public void setCollectionHasMovies(boolean collectionHasMovies) {
        this.collectionHasMovies = collectionHasMovies;
    }
    public ArrayList<ArrayList<String>> getCollectionList() {
        return collectionList;
    }
    public void setCollectionList(ArrayList<ArrayList<String>> collectionList) {
        this.collectionList = collectionList;
    }
    public void setCollectionListSize(int collectionListSize) {
        this.collectionListSize = collectionListSize;
    }

    public int getCollectionListSize() {
        return collectionListSize;
    }
    /**
     * Creates a new instance of Collection
     */
    public Collection() throws SQLException {
        generateCollection();
    }
    
    public void generateCollection() throws SQLException{
        
        //Anhand der SessionId die userId herausfinden um Collection-Objekt fuer Benutzer anzulegen
        ManageSessionId MSId = new ManageSessionId(Controller.getActualSessionId());
            MovieCollection moviecollection = new MovieCollection(MSId.getUserIdBySessionId(), 0);

            if(moviecollection.getMovieCollectionByUserId(MSId.getUserIdBySessionId()) != null) {
                collectionList = moviecollection.getMovieCollectionByUserId(MSId.getUserIdBySessionId());
                collectionHasMovies = true;
            } else {
                collectionHasMovies = false;
            }
            collectionListSize = collectionList.size();
    }
    
    public String showCollectionItem(int outer, int inner) throws SQLException {
        return collectionList.get(outer).get(inner);
    }
    
    public String Logout() {
        Controller.destroyActualSessionId();
        return "index";
    }
    public String navigateToCollection(){
        return"collection";
    }
}