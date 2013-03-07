package com.SOCIETIES.GroupCollabTool.MainPage.ActivityFeed;

import java.util.Date;

import com.SOCIETIES.Framework.IActivity;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.ActivityDescription;

public class Activity implements IActivity {

	ActivityDescription decription;
	
	public Activity(ActivityDescription activity)
	{
		this.decription = activity;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.decription.getType();
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return this.decription.getDate();
	}

	@Override
	public String getdescription() {
		// TODO Auto-generated method stub
		return this.decription.getDesc();
	}

	@Override
	public String getLinks() {
		// TODO Auto-generated method stub
		return this.decription.getLink();
	}

	@Override
	public String getImageURL() {
		// TODO Auto-generated method stub
		return "";
	}

}
