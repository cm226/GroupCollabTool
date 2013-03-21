package org.societies.GroupCollabTool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static Logger LOG = LoggerFactory.getLogger(testService.class);
	
	public ActivityGetter()
	{
		try{this.available.acquire();}
		catch (InterruptedException e)
		{
			isDirty = true;
			e.printStackTrace();
			// log errors
		}
	}
	
	@Override
	public void receiveResult(MarshaledActivityFeed arg0) 
	{
		if(this.isDirty)
		{
			LOG.info("Activity Result Is dirty");
			return;
		}
		
		 GetActivitiesResponse addResponce = arg0.getGetActivitiesResponse();
		 
		 LOG.info("addResponce : "+addResponce);

			List<MarshaledActivity> acts =  addResponce.getMarshaledActivity();
			posts = new ActivityDescription[acts.size()];
			
			LOG.info("Acts Size"+acts.size());
			
			Iterator<MarshaledActivity> actsIt = acts.iterator();
			int i = 0;
			while(actsIt.hasNext())
			{
				MarshaledActivity act = actsIt.next();
				LOG.info("actor"+act.getActor());
				LOG.info("obj"+act.getObject());
				LOG.info("Targ"+act.getTarget());
				ArrayList<String> targets = new ArrayList<String>();
				String[] links = act.getTarget().split(",");
				int linkCnt = 0;
				for(linkCnt=0; linkCnt< links.length; linkCnt++)
					targets.add(links[linkCnt]);
				
				posts[i] = new ActivityDescription(act.getActor(), act.getObject(),targets , new Date());
				i++;
			}
			
			this.available.release();
	}
	
	
	public ActivityDescription[] getActiviteys()
	{
		try
		{
			this.available.acquire();
		}
		catch (InterruptedException e)
		{
			System.out.println("unable to aquire lock ");
			e.printStackTrace();
			return new ActivityDescription[]{};
			// log error
		}
		
		return posts;
	}
}
