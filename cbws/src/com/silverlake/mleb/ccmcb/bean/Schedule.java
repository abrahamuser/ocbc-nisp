package com.silverlake.mleb.ccmcb.bean;

import java.io.Serializable;

/**
 * Dukcapil data is also known as KTP data.
 */
public class Schedule implements Serializable{

	private String repeatType;

    private String occurrence;

    private String startDate;

    public String getRepeatType ()
    {
        return repeatType;
    }

    public void setRepeatType (String repeatType)
    {
        this.repeatType = repeatType;
    }

    public String getOccurrence ()
    {
        return occurrence;
    }

    public void setOccurrence (String occurrence)
    {
        this.occurrence = occurrence;
    }

    public String getStartDate ()
    {
        return startDate;
    }

    public void setStartDate (String startDate)
    {
        this.startDate = startDate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [repeatType = "+repeatType+", occurrence = "+occurrence+", startDate = "+startDate+"]";
    }
	
	
}
