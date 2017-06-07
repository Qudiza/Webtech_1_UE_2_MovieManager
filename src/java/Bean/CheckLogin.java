package Bean;


import Control.Controller;
import Control.ManageSessionId;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WEBTECH
 */
@SessionScoped
public class CheckLogin implements Serializable{

    //returns false if Login is not valid
    //returns true  if Login is valid
    public static boolean checkLoginValidation() throws SQLException {
        ManageSessionId testMSId = new ManageSessionId(Controller.getActualSessionId());
        int id = testMSId.getUserIdBySessionId();
        
        if(id == 0) {
            return false;
        } else {
            return true;
        }
    }
}