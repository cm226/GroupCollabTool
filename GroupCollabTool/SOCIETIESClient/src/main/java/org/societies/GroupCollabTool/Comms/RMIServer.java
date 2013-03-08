package org.societies.GroupCollabTool.Comms;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import com.SOCIETIES.GroupCollabTool.Comms.Shared.ActivityDescription;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.IServer;

public class RMIServer implements IServer {
    
public RMIServer() {}

@Override
public ActivityDescription[] getActivitys() throws RemoteException
{
	ActivityDescription[] activitys = new ActivityDescription[5];
	activitys[0] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	activitys[1] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	activitys[2] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	activitys[3] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	activitys[4] = new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date());
	
	return activitys;
	
} 


@Override
public ActivityDescription[] getActivitys(String type) throws RemoteException
{
	ArrayList<ActivityDescription> activitys = new ArrayList<ActivityDescription>();
	System.out.println("requesting type: "+type);
	
	if(type.compareTo("Documents")==0)
	{
		activitys.add(new ActivityDescription("DropBox", "project plan.xls edited", "www.google.com", new Date()));
		activitys.add(new ActivityDescription("DropBox", "User Guide.doc was edited", "www.google.com", new Date()));
	}
	else if(type.compareTo("Code")==0)
	{
		activitys.add(new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date()));
		activitys.add(new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date()));
	}
	else
	{
		activitys.add(new ActivityDescription("DropBox", "project plan.xls edited", "www.google.com", new Date()));
		activitys.add(new ActivityDescription("DropBox", "User Guide.doc was edited", "www.google.com", new Date()));
		activitys.add(new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date()));
		activitys.add(new ActivityDescription("Git","blargh git thing commit blargh","www.google.com",new Date()));
		
	}
	//ActivityDescription[] actArr = (ActivityDescription[]) activitys.toArray(); // for some reason this threw an invalid cast exception on the webapp side 
	
	ActivityDescription[] actArr = new ActivityDescription[activitys.size()];
	Iterator<ActivityDescription> activityIt = activitys.iterator();
	int count = 0;
	while(activityIt.hasNext())
	{
		actArr[count] = activityIt.next();
		count++;
	}
	
	return actArr;
	
} 

}
