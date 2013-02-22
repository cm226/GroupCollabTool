package com.SOCIETIES.GroupCollabTool.Comms;

import java.util.Date;

import com.SOCIETIES.Framework.IActivity;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.ActivityDescription;

public abstract class ActivityBase implements IActivity
{	
	private ActivityDescription activity;
	
	ActivityBase(ActivityDescription act)
	{
		this.activity = act;
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return activity.getType();
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return activity.getDate();
	}

	@Override
	public String getdescription() {
		// TODO Auto-generated method stub
		return activity.getDesc();
	}

	@Override
	public String getLinks() {
		// TODO Auto-generated method stub
		return activity.getLink();
	}

	@Override
	public abstract String getImageURL();
	
}
