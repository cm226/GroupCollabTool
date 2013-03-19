package com.SOCIETIES.GroupCollabTool.Comms.Shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ActivityDescription implements Serializable
{
	private static final long serialVersionUID = 20120731125400L;
	
	private String type, desc;
	ArrayList<String> links;
	private Date posted;
	
	public ActivityDescription(String type, String desc, ArrayList<String> links, Date posted)
	{
		this.type = type;
		this.desc = desc;
		this.links = links;
		this.posted = posted;
	}
	
	public String getType(){return this.type;}
	public String getDesc(){return this.desc;}
	public ArrayList<String> getLinks(){return this.links;}
	
	public Date getDate(){return this.posted;}
	
}
