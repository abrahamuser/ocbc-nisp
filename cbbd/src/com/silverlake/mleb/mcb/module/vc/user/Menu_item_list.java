
package com.silverlake.mleb.mcb.module.vc.user;

import java.io.Serializable;

public class Menu_item_list implements Serializable {

    private String menu_item_id;
    private String has_child;
    private String menu_item_title;
    private String menu_parent_id;

    public String getMenu_item_id() {
        return menu_item_id;
    }

    public void setMenu_item_id(String menu_item_id) {
        this.menu_item_id = menu_item_id;
    }

    public String getHas_child() {
        return has_child;
    }

    public void setHas_child(String has_child) {
        this.has_child = has_child;
    }

    public String getMenu_item_title() {
        return menu_item_title;
    }

    public void setMenu_item_title(String menu_item_title) {
        this.menu_item_title = menu_item_title;
    }

    public String getMenu_parent_id() {
        return menu_parent_id;
    }

    public void setMenu_parent_id(String menu_parent_id) {
        this.menu_parent_id = menu_parent_id;
    }

}
