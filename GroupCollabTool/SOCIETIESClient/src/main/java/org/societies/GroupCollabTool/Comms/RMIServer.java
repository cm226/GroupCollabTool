package org.societies.GroupCollabTool.Comms;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
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
import org.societies.api.services.IServices;

public class RMIServer  implements IServer 
{
	private IUserActionMonitor uam;
	private IIdentity userID;
	private String defaultType;
	private IServices servics;
	private List<String> myServiceTypes;
	private  ICisManager manager;
	private static Logger LOG = LoggerFactory.getLogger(GroupCollabToolClient.class);
	
	
public RMIServer(IUserActionMonitor uam,
				IIdentity userID,
				IServices serviceID,
				List<String> myServiceTypes,
				ICisManager manager)
{
	this.uam = uam;
	this.userID = userID;
	this.servics = serviceID;
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
	
	ActivityGetter activityGetter = new ActivityGetter(this.defaultType);
	
	Long milliSecondsSinceepoch = System.currentTimeMillis();
	cisList.get(0).getActivityFeed().getActivities("0 "+milliSecondsSinceepoch.toString(),activityGetter);
	
	return activityGetter.getActiviteys(); // blocking call
}

public ActivityDescription[] getActivitys(String type) throws RemoteException
{
	ServiceResourceIdentifier myServiceID = this.servics.getMyServiceId(GroupCollabToolClient.class);
	LOG.info("serviceID: "+myServiceID);
	LOG.info("type: "+type);
	LOG.info("serviceTypes: "+myServiceTypes.get(0));
	LOG.info("moniter: "+this.uam);
	
	IAction action = new Action(myServiceID, myServiceTypes.get(0), "contentType",type);
	this.uam.monitor(this.userID, action);
	
	List<ICis> cisList =  manager.getCisList();
	LOG.info("getting Activities: "+type);
	ActivityGetter activityGetter = new ActivityGetter(type);
	
	Long milliSecondsSinceepoch = System.currentTimeMillis();
	
	if(cisList.size() == 0)
		return new ActivityDescription[]{};
		
	LOG.info("getting from: "+cisList.get(0).getName());
	IActivityFeed actFeed = cisList.get(0).getActivityFeed();
	
	actFeed.getActivities("0 "+milliSecondsSinceepoch.toString(),activityGetter);
	
	LOG.info("Entering BlockingCall");
	ActivityDescription[] act = activityGetter.getActiviteys(); // blocking call
	LOG.info("Exiting blocking call with: "+act.length);
	
	return act;
}

public void postToFeed(IMember User, String Message) throws RemoteException
{
	LOG.info("Positing to feed");
	List<ICis> cisList =  manager.getCisList();
	if(cisList.size() > 0)
	{
		IActivityFeed activityFeed = cisList.get(0).getActivityFeed();
		
		IActivity newActivity1 = activityFeed.getEmptyIActivity(); // get an empty activity interface so you can use to fill up with the new activity data
		newActivity1.setActor("User");
		newActivity1.setObject(Message);
		
		Date now= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateStr = sdf.format(now);
		
		newActivity1.setVerb(dateStr);
		newActivity1.setTarget("");
		
		activityFeed.addActivity(newActivity1, new UpdaterCallback());
	}
	
}


}
