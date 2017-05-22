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
public class EmovieCharacter {
    private String characterName;
    private int actorId;
    private int movieId;

    public EmovieCharacter(String characterName, int actorId, int movieId) {
        this.characterName = characterName;
        this.actorId = actorId;
        this.movieId = movieId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public int getActorId() {
        return actorId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }  
}