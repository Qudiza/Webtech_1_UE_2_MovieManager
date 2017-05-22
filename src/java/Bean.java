/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Control.Item;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Altair
 */
@Named(value = "Bean")
@ViewScoped
public class Bean {

    private List<Item> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<Item>();
    }

    public void add() {
        items.add(new Item());
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public void save() {
        System.out.println("items: " + items);
    }

    public List<Item> getItems() {
        return items;
    }

}
