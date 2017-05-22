package Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Altair
 */
public class Emovie {
    private int movieId;
    private String title;
    private int releaseDate;
    
 public Emovie(int movieId, String title, int releaseDate) {
        this.movieId = movieId;
        this.title = title;
        this.releaseDate = releaseDate;
    }
 
    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }
}