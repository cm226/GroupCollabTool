package com.SOCIETIES.GroupCollabTool.Comms.Shared;

import java.io.Serializable;
import java.util.Date;

public class ActivityDescription implements Serializable
{
	private static final long serialVersionUID = 20120731125400L;
	
	private String type, desc, link;
	private Date posted;
	
	public ActivityDescription(String type, String desc, String link, Date posted)
	{
		this.type = type;
		this.desc = desc;
		this.link = link;
		this.posted = posted;
	}
	
	public String getType(){return this.type;}
	public String getDesc(){return this.desc;}
	public String getLink(){return this.link;}
	
	public Date getDate(){return this.posted;}
	
}
