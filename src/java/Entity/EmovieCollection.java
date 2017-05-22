/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Altair
 */
public class EmovieCollection {
private int collectionId;
private int userId;
private int movieId;

    public EmovieCollection(int collectionId, int userId, int movieId) {
        this.collectionId = collectionId;
        this.userId = userId;
        this.movieId = movieId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public int getUserId() {
        return userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}