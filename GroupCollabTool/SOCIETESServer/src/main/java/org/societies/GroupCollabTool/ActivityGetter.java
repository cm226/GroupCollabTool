package org.societies.GroupCollabTool;

import java.util.Date;
import java.util.List;
import java.util.Iterator;
import org.societies.api.activity.IActivityFeedCallback;
import org.societies.api.schema.activityfeed.MarshaledActivityFeed;
import java.util.concurrent.Semaphore;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.ActivityDescription;
import org.societies.api.schema.activityfeed.AddActivityResponse;
import org.societies.api.schema.activityfeed.GetActivitiesResponse;
import org.societies.api.schema.activity.MarshaledActivity;

public class ActivityGetter implements IActivityFeedCallback
{
	private boolean isDirty = false;
	private final Semaphore available = new Semaphore(1);
	
	private ActivityDescription[] posts;
	
	public ActivityGetter()
	{
		try{this.available.acquire();}
		catch (InterruptedException e)
		{
			isDirty = true;
			// log errors
		}
	}
	
	@Override
	public void receiveResult(MarshaledActivityFeed arg0) 
	{
		if(this.isDirty)
			return;
		
		 AddActivityResponse addResponce = arg0.getAddActivityResponse();
		 if(addResponce.isResult())
		 { 
			List<MarshaledActivity> acts =  arg0.getGetActivitiesResponse().getMarshaledActivity();
			posts = new ActivityDescription[acts.size()];
			
			Iterator<MarshaledActivity> actsIt = acts.iterator();
			int i = 0;
			while(actsIt.hasNext())
			{
				MarshaledActivity act = actsIt.next();
				posts[i] = new ActivityDescription(act.getActor(), act.getObject(), act.getTarget(), new Date());
				i++;
			}
			
			this.available.release();
		 }
	}
	
	
	public ActivityDescription[] getActiviteys()
	{
		try{this.available.acquire();}
		catch (InterruptedException e)
		{
			return new ActivityDescription[]{};
			// log error
		}
		
		return posts;
	}
}
