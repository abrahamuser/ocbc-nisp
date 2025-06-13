
package com.silverlake.mleb.mcb.module.vc.user;

import java.io.Serializable;
import java.util.List;

public class Menu_access_list implements Serializable  {

    private String menu_id;
    private String menu_title;
    private List<Menu_item_list> menu_item_list;

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_title() {
        return menu_title;
    }

    public void setMenu_title(String menu_title) {
        this.menu_title = menu_title;
    }

    public List<Menu_item_list> getMenu_item_list() {
        return menu_item_list;
    }

    public void setMenu_item_list(List<Menu_item_list> menu_item_list) {
        this.menu_item_list = menu_item_list;
    }

}
