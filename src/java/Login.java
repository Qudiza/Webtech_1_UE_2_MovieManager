/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Control.Controller;
import Control.ManageSessionId;
import Control.MovieUser;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Altair
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    /**
     * Creates a new instance of Login
     */
    
    //@ManagedProperty(value="Username") private String username;
    //@ManagedProperty(value="Password") private String password;
    
    private String username;
    private String password;
    private String errorMessage="";

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public Login() {   
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    
    public String DoLogin() throws SQLException {
        if(LoginValidation())
        {
            errorMessage = "";
            
            ManageSessionId MSId = new ManageSessionId(Controller.getActualSessionId());
            MSId.saveSessionId(username);
            
            return "collection";
        }
        errorMessage = "Fehler beim Login";
        return "login";
    }
    public boolean LoginValidation() throws SQLException
    {
        //ToDo ableich mit DB
        MovieUser loginUser = new MovieUser(username, password);
        
        return loginUser.userValidierung();
     /*   
        if(loginUser.userValidierung())
        {
            return true;
        }
         return false;
*/
    }
}