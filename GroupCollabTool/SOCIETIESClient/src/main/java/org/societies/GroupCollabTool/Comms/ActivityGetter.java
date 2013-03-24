package org.societies.GroupCollabTool.Comms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.societies.GroupCollabtoolClient.GroupCollabToolClient;
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
	private static Logger LOG = LoggerFactory.getLogger(GroupCollabToolClient.class);
	private String type;
	
	public ActivityGetter(String type)
	{
		try{this.available.acquire();}
		catch (InterruptedException e)
		{
			isDirty = true;
			e.printStackTrace();
			// log errors
		}
		this.type = type;
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
			ArrayList<ActivityDescription> subActList = new ArrayList<ActivityDescription>();
			
			
			LOG.info("Acts Size"+acts.size());
			
			Iterator<MarshaledActivity> actsIt = acts.iterator();
			int i = 0;
			while(actsIt.hasNext())
			{
				MarshaledActivity act = actsIt.next();
				String actor = act.getActor();
				
				LOG.info("groupCollabTool type is: "+this.type);
				LOG.info("actor is: "+actor);
				// yes yes i know, but im very short on time 
				if(this.type == null || this.type.compareTo("All") ==0)
				{
					if(actor.compareTo("User") != 0 &&
						actor.compareTo("Git") != 0 &&
						actor.compareTo("dropbox") != 0)
						continue;
					
					LOG.info("actor ok with: "+actor);
				}
				else if(this.type.compareTo("Code") == 0 && actor.compareTo("Git") != 0)
					continue;
				else if(this.type.compareTo("Documents") == 0 && actor.compareTo("dropbox") != 0)
					continue;
				
				LOG.info("actor"+actor);
				LOG.info("obj"+act.getObject());
				LOG.info("Targ"+act.getTarget());
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date posted;
				try{posted = sdf.parse(act.getVerb());}
				catch(java.text.ParseException e)
				{
					posted = new Date();
					LOG.info("Date parser Error:"+act.getTarget());
				}
				
				
				ArrayList<String> targets = new ArrayList<String>();
				if(act.getTarget() != null)
				{
					String[] links = act.getTarget().split(",");
					int linkCnt = 0;
					for(linkCnt=0; linkCnt< links.length; linkCnt++)
						targets.add(links[linkCnt]);
				}
					subActList.add(new ActivityDescription(actor,act.getObject(),targets,posted));
					LOG.info("postedDate: "+posted);
			}
			
			int accualCont = subActList.size();
			LOG.info("gct sptttingout : "+accualCont);
			posts = new ActivityDescription[accualCont];
				for (int i2 = 0; i2 < accualCont; i2++)
					posts[i2] = subActList.get(i2);
				
			
			
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