package com.SOCIETIES.GroupCollabTool.Comms;

import java.io.Serializable;
import java.util.Date;

public class ActivityDescription implements Serializable
{
	private static final long serialVersionUID = 20120731125400L;
	
	private String type, desc, link;
	private Date posted;
	
	public String getType(){return this.type;}
	public String getDesc(){return this.desc;}
	public String getLink(){return this.link;}
	
	public Date getDate(){return this.posted;}
	
	
}
