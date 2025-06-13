
package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObMenuListBean implements Serializable  {

    private String menu_id;
    private String menu_title;
    private String menu_title_id;
    private String menu_title_cn;
    private String menu_desc;
    private String menu_desc_id;
    private String menu_desc_cn;
    private List<ObMenuItemListBean> menu_item_list;

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

    public List<ObMenuItemListBean> getMenu_item_list() {
        return menu_item_list;
    }

    public void setMenu_item_list(List<ObMenuItemListBean> menu_item_list) {
        this.menu_item_list = menu_item_list;
    }

	public String getMenu_desc() {
		return menu_desc;
	}

	public void setMenu_desc(String menu_desc) {
		this.menu_desc = menu_desc;
	}

	public String getMenu_desc_id() {
		return menu_desc_id;
	}

	public void setMenu_desc_id(String menu_desc_id) {
		this.menu_desc_id = menu_desc_id;
	}

	public String getMenu_title_id() {
		return menu_title_id;
	}

	public void setMenu_title_id(String menu_title_id) {
		this.menu_title_id = menu_title_id;
	}

    public String getMenu_title_cn() {
		return menu_title_cn;
	}

	public void setMenu_title_cn(String menu_title_cn) {
		this.menu_title_cn = menu_title_cn;
	}

    public String getMenu_desc_cn() {
		return menu_desc_cn;
	}

	public void setMenu_desc_cn(String menu_desc_cn) {
		this.menu_desc_cn = menu_desc_cn;
	}
}
