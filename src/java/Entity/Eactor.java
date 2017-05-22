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
public class Eactor {
    private int actorId;
    private String actorName;
    private char gender;
    
    public Eactor(int actorId, String actorName, char gender) {
        this.actorId = actorId;
        this.actorName = actorName;
        this.gender = gender;
    }

    public int getActorId() {
        return actorId;
    }

    public String getActorName() {
        return actorName;
    }

    public char getGender() {
        return gender;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}