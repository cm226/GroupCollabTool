package org.societies.Comms;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.societies.GroupCollabTool.ActivityGetter;
import org.societies.GroupCollabTool.CVersionControlPost;
import org.societies.GroupCollabTool.testService;
import org.societies.api.activity.IActivityFeed;
import org.societies.api.cis.management.ICis;
import org.societies.api.cis.management.ICisManager;

import com.SOCIETIES.GroupCollabTool.Comms.Shared.ActivityDescription;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.IServer;

public class RMIServer implements IServer {
    
private  ICisManager manager;
private static Logger LOG = LoggerFactory.getLogger(testService.class);
	
public RMIServer(ICisManager manager)
{
	this.manager = manager;
}

@Override
public ActivityDescription[] getActivitys() throws RemoteException
{
	/*
	ActivityDescription[] activitys = new ActivityDescription[5];
	activitys[0] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	activitys[1] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	activitys[2] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	activitys[3] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	activitys[4] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());*/
	

	
	List<ICis> cisList =  manager.getCisList();
	LOG.info("getting Activities");
	ActivityGetter activityGetter = new ActivityGetter();
	
	Long milliSecondsSinceepoch = System.currentTimeMillis();//new Date().getTime();
	
	if(cisList.size() == 0)
		return new ActivityDescription[]{};
		
	IActivityFeed actFeed = cisList.get(0).getActivityFeed();
	actFeed.getActivities("0 "+milliSecondsSinceepoch.toString(),activityGetter);
	
	LOG.info("Entering BlockingCall");
	ActivityDescription[] act = activityGetter.getActiviteys(); // blocking call
	LOG.info("Exiting blocking call with: "+act.length);
	
	return act;
	
} 


@Override
public ActivityDescription[] getActivitys(String type) throws RemoteException
{
	/*ActivityDescription[] activitys = new ActivityDescription[5];
	activitys[0] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	activitys[1] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	activitys[2] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	activitys[3] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	activitys[4] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	
	return activitys;*/
	
	
	List<ICis> cisList =  manager.getCisList();
	
	ActivityGetter activityGetter = new ActivityGetter();
	
	Double milliSecondsSinceepoch = new Double((new Date()).getTime());
	cisList.get(0).getActivityFeed().getActivities("0 "+milliSecondsSinceepoch.toString(),activityGetter);
	
	return activityGetter.getActiviteys(); // blocking call
	
}


}
