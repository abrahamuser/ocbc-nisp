package com.silverlake.mleb.mcb.module.vc.holiday;

import java.io.Serializable;

public class ListHolidayCalendar implements Serializable {

    private String calendar_date;
    private String holiday_type;
    private String desc_id;
    private String desc_en;

    public String getCalendar_date() {
        return calendar_date;
    }

    public void setCalendar_date(String calendar_date) {
        this.calendar_date = calendar_date;
    }

    public String getHoliday_type() {
        return holiday_type;
    }

    public void setHoliday_type(String holiday_type) {
        this.holiday_type = holiday_type;
    }

    public String getDesc_id() {
        return desc_id;
    }

    public void setDesc_id(String desc_id) {
        this.desc_id = desc_id;
    }

    public String getDesc_en() {
        return desc_en;
    }

    public void setDesc_en(String desc_en) {
        this.desc_en = desc_en;
    }

}

