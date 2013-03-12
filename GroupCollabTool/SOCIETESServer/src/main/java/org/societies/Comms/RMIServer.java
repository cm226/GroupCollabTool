package org.societies.Comms;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import org.societies.GroupCollabTool.ActivityGetter;
import org.societies.GroupCollabTool.CVersionControlPost;
import org.societies.api.cis.management.ICis;
import org.societies.api.cis.management.ICisManager;

import com.SOCIETIES.GroupCollabTool.Comms.Shared.ActivityDescription;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.IServer;

public class RMIServer implements IServer {
    
private  ICisManager manager;
	
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
	
	ActivityGetter activityGetter = new ActivityGetter();
	
	Double milliSecondsSinceepoch = new Double((new Date()).getTime());
	cisList.get(0).getActivityFeed().getActivities("0 "+milliSecondsSinceepoch.toString(),activityGetter);
	
	return activityGetter.getActiviteys(); // blocking call
	
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
