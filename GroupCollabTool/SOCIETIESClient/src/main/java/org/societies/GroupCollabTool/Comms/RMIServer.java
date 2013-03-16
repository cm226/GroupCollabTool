package org.societies.GroupCollabTool.Comms;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.societies.api.identity.IIdentity;
import org.societies.api.personalisation.model.IAction;
import org.societies.api.personalisation.model.Action;
import org.societies.api.useragent.monitoring.IUserActionMonitor;

import com.SOCIETIES.GroupCollabTool.Comms.Shared.ActivityDescription;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.IServer;

import org.societies.api.schema.servicelifecycle.model.ServiceResourceIdentifier;

public class RMIServer implements IServer
{
	private IUserActionMonitor uam;
	private IIdentity userID;
	private String defaultType;
	private ServiceResourceIdentifier myServiceID;
	private List<String> myServiceTypes;
	
	
public RMIServer(IUserActionMonitor uam, IIdentity userID, ServiceResourceIdentifier serviceID, List<String> myServiceTypes)
{
	this.uam = uam;
	this.userID = userID;
	this.myServiceID = serviceID;
	this.myServiceTypes = myServiceTypes;
}

@Override
public ActivityDescription[] getActivitys() throws RemoteException
{
	return this.getActivitysBase(this.defaultType);
} 


@Override
public ActivityDescription[] getActivitys(String type) throws RemoteException
{
	
	IAction action = new Action(myServiceID, myServiceTypes.get(0), "contentType",type);
	this.uam.monitor(this.userID, action);
	
	return this.getActivitysBase(type);
	
}

private ActivityDescription[] getActivitysBase(String type)
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

public void updateDefaultType(String newType)
{
	this.defaultType = newType;
}


}
