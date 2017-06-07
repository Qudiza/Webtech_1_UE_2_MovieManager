/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Control.Controller;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Arnold
 */
@ManagedBean
public class TopNavigationBean {
    
    public String logout() {
        Controller.destroyActualSessionId();
        return "index";
    }
    
}
