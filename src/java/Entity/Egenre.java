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
public class Egenre {
    private int genreId;
    private String genre;

    public Egenre(int genreId, String genre) {
        this.genreId = genreId;
        this.genre = genre;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}