package com.SOCIETIES.GroupCollabTool.MainPage.ActivityFeed;

import java.util.ArrayList;
import java.util.Date;

import com.SOCIETIES.Framework.IActivity;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.ActivityDescription;

public class Activity implements IActivity {

	ActivityDescription description;
	
	public Activity()
	{
		String Type = "Git";
		String Description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur pretium euismod iaculis. Quisque volutpat ullamcorper metus hendrerit auctor. Quisque eget enim leo. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean quis augue lorem. Donec vehicula lectus id sapien blandit eu volutpat arcu consectetur. Sed ultricies orci nec nunc sollicitudin sit amet pretium risus interdum.";
		Date DatePosted = new Date();
		
		ArrayList<String> Links = new ArrayList<String>();
		Links.add("www.google.com");
		Links.add("www.github.com");
		
		this.description = new ActivityDescription(Type, Description, Links, DatePosted);
	}
	
	public Activity(ActivityDescription activity)
	{
		this.description = activity;
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.description.getType();
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return this.description.getDate();
	}

	@Override
	public String getdescription() {
		// TODO Auto-generated method stub
		return this.description.getDesc();
	}

	@Override
	public ArrayList<String> getLinks() {
		// TODO Auto-generated method stub
		return this.description.getLinks();
	}

	@Override
	public String getImageURL() {
		// TODO Auto-generated method stub
		return "";
	}

}
