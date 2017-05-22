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
public class Eregisseur {
private int regisseurId;
private String regisseurName;

    public Eregisseur(int regisseurId, String regisseurName) {
        this.regisseurId = regisseurId;
        this.regisseurName = regisseurName;
    }

    public int getRegisseurId() {
        return regisseurId;
    }

    public String getRegisseurName() {
        return regisseurName;
    }

    public void setRegisseurId(int regisseurId) {
        this.regisseurId = regisseurId;
    }

    public void setRegisseurName(String regisseurName) {
        this.regisseurName = regisseurName;
    }
}