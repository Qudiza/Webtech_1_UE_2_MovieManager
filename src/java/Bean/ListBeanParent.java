/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Arnold
 */

@Named
@ViewScoped
public class ListBeanParent {
    private ListBean actorList;
    private ListBean genreList;

    public void setActorList(ListBean actorList) {
        this.actorList = actorList;
    }
    public ListBean getActorList() {
        return actorList;
    }
    
    public void setGenreList(ListBean genreList) {
        this.genreList = genreList;
    }
    public ListBean getGenreList() {
        return genreList;
    }
    
    
}
