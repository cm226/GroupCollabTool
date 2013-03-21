package org.societies.GroupCollabTool.Comms;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.societies.GroupCollabtoolClient.GroupCollabToolClient;
import org.societies.api.activity.IActivity;
import org.societies.api.activity.IActivityFeed;
import org.societies.api.cis.management.ICis;
import org.societies.api.cis.management.ICisManager;
import org.societies.api.identity.IIdentity;
import org.societies.api.personalisation.model.IAction;
import org.societies.api.personalisation.model.Action;
import org.societies.api.useragent.monitoring.IUserActionMonitor;

import com.SOCIETIES.GroupCollabTool.Comms.Shared.ActivityDescription;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.IServer;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.IMember;

import org.societies.api.schema.servicelifecycle.model.ServiceResourceIdentifier;

public class RMIServer  implements IServer 
{
	private IUserActionMonitor uam;
	private IIdentity userID;
	private String defaultType;
	private ServiceResourceIdentifier myServiceID;
	private List<String> myServiceTypes;
	private  ICisManager manager;
	private static Logger LOG = LoggerFactory.getLogger(GroupCollabToolClient.class);
	
	
public RMIServer(IUserActionMonitor uam,
				IIdentity userID,
				ServiceResourceIdentifier serviceID,
				List<String> myServiceTypes,
				ICisManager manager)
{
	this.uam = uam;
	this.userID = userID;
	this.myServiceID = serviceID;
	this.myServiceTypes = myServiceTypes;
	this.manager = manager;
}

public void updateDefaultType(String newType)
{
	this.defaultType = newType;
}

public ActivityDescription[] getActivitys() throws RemoteException
{
	List<ICis> cisList =  manager.getCisList();
	
	ActivityGetter activityGetter = new ActivityGetter();
	
	Double milliSecondsSinceepoch = new Double((new Date()).getTime());
	cisList.get(0).getActivityFeed().getActivities("0 "+milliSecondsSinceepoch.toString(),activityGetter);
	
	return activityGetter.getActiviteys(); // blocking call
}

public ActivityDescription[] getActivitys(String arg0) throws RemoteException
{
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

public void postToFeed(IMember User, String Message) throws RemoteException
{
	List<ICis> cisList =  manager.getCisList();
	if(cisList.size() > 0)
	{
		IActivityFeed activityFeed = cisList.get(0).getActivityFeed();
		
		IActivity newActivity1 = activityFeed.getEmptyIActivity(); // get an empty activity interface so you can use to fill up with the new activity data
		newActivity1.setActor(User.GetID());
		newActivity1.setObject(Message);
		newActivity1.setVerb(new Date().toString());
		activityFeed.addActivity(newActivity1, new UpdaterCallback());
	}
	
}


}
