/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Altair
 */
public class Controller {
    
    public static String getActualSessionId(){
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        return session.getId();
    }
    
    public static void destroyActualSessionId() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        fCtx.getExternalContext().invalidateSession();
    }
}