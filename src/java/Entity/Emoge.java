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
public class Emoge {
    private int movieId;
    private int genreId;

    public Emoge(int movieId, int genreId) {
        this.movieId = movieId;
        this.genreId = genreId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}