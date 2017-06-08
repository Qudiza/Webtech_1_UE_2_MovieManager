/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entity.Eregisseur;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Altair
 */
public class Regisseur {
    private Eregisseur regisseur;
    private DBConnection DBC = new DBConnection();

    public Regisseur(String regisseurName) {
        this.regisseur = new Eregisseur(0, regisseurName);
    }
  
  //gibt true zurück, wenn Regisseur erstellt wurde
  //gibt false zurück, wenn Regisseur bereits vorhanden ist
    public boolean insertRegisseur() throws SQLException {
      ResultSet rs = DBC.getRS("SELECT regisseurName FROM regisseur");
      while (rs.next()) {
          if(rs.getString(1).equals(regisseur.getRegisseurName())) {
              //Regisseur bereits vorhanden
              return false;
          }
         }
      //Regisseur eintragen
      DBC.executeQuery("INSERT INTO regisseur(regisseurName) VALUES('" + regisseur.getRegisseurName() + "')");
      return true;
  }
    public int getRegisseurId() throws SQLException{
        ResultSet rs = DBC.getRS("SELECT regisseurId FROM regisseur WHERE regisseurName = '" + regisseur.getRegisseurName() + "'");
        
        if(!rs.isBeforeFirst())
        {
            return 0;
        } else {
            rs.next();
            return rs.getInt(1);
        }
    }
}