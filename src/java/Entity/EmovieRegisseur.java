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
public class EmovieRegisseur {
    private int regisseurId;
    private int movieId;

    public EmovieRegisseur(int regisseurId, int movieId) {
        this.regisseurId = regisseurId;
        this.movieId = movieId;
    }

    public int getRegisseurId() {
        return regisseurId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setRegisseurId(int regisseurId) {
        this.regisseurId = regisseurId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}