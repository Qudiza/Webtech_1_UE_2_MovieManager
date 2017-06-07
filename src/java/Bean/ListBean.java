/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Arnold
 */
@ManagedBean
@ViewScoped
public class ListBean {
    private List<ListItem> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<ListItem>();
    }

    public void add() {
        items.add(new ListItem());
    }

    public void remove(ListItem item) {
        items.remove(item);
    }

    //TODO: ändern auf speichern der Werte
    public void save() {
        System.out.println("items: " + items);
    }

    public List<ListItem> getItems() {
        return items;
    }

}
