package org.societies.Comms;

import java.rmi.RemoteException;
import java.util.Date;

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
}
