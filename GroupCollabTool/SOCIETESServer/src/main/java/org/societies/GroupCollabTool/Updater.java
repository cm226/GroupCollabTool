package org.societies.GroupCollabTool;

import org.societies.api.activity.IActivity;
import org.societies.api.activity.IActivityFeed;

public class Updater implements Runnable
{
	private IActivityFeed activityFeed;
	
	Updater(IActivityFeed activityFeed)
	{
		this.activityFeed = activityFeed;
	}

	@Override
	public void run() {
		addToActivityFeed();
		
	}
	
	public void stopUpdateing()
	{
		
		
	}
	
	private void addToActivityFeed()
	{

		IActivity newActivity1 = activityFeed.getEmptyIActivity(); // get an empty activity interface so you can use to fill up with the new activity data
		newActivity1.setActor("John");
		newActivity1.setObject("volleyball");
		newActivity1.setTarget("at the national tournament");
		newActivity1.setVerb("played");
 
		activityFeed.addActivity(newActivity1, new UpdaterCallback());
		
		
	}

}
