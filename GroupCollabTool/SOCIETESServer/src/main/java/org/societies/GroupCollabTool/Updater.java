package org.societies.GroupCollabTool;



import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.societies.Comms.RMIServer;
import org.societies.api.activity.IActivity;
import org.societies.api.activity.IActivityFeed;
import org.societies.api.cis.management.ICis;
import org.societies.api.cis.management.ICisManager;

import org.societies.Comms.RMIServer.*;

import com.SOCIETIES.GroupCollabTool.Comms.Shared.IServer;

public class Updater implements Runnable
{
	private ICisManager manager;
	private boolean running = true;
	private Logger log;
	
	Updater( ICisManager cisManager, Logger log)
	{
		this.manager = cisManager;
		this.log  = log;
		this.initRMI();
	}

	@Override
	public void run() 
	{
		while(this.running)
		{
			List<ICis> cisList =  manager.getCisList();
			if(cisList.size() > 0)
			{
				System.out.println("have cis: "+cisList.get(0).getName());
				ArrayList<String> links = new ArrayList<String>();
				links.add("www.google.com");
				links.add("www.google.com");
				
				CVersionControlPost change = new CVersionControlPost("Git", new Date(), "commit desc",links);
				addToActivityFeed(cisList.get(0).getActivityFeed(),change);
			
				log.info("added to activity feed");
			}
			else
			{
				log.warn("No CIS's to moniter");
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // increase this later, prob better to use asinc callback in future 
		}

	}
	
	public void stopUpdateing()
	{
		this.running = false;
	}
	
	private void initRMI()
	{
		try {
	            RMIServer obj = new RMIServer(manager);
	            IServer stub = (IServer) UnicastRemoteObject.exportObject((Remote) obj, 0);

	            // Bind the remote object's stub in the registry
	            Registry registry = LocateRegistry.getRegistry();
	            registry.bind("IServer", stub);

	        } catch (Exception e) {
	        	
	            log.error(" Group CollabTool RMI Server exception: " + e.toString());
	            e.printStackTrace();
	        }
	}
	

	
	private void addToActivityFeed(IActivityFeed activityFeed, CVersionControlPost change)
	{

		IActivity newActivity1 = activityFeed.getEmptyIActivity(); // get an empty activity interface so you can use to fill up with the new activity data
		newActivity1.setActor(change.GetUserID());
		newActivity1.setObject(change.GetDescription());
		
		StringBuilder sb = new StringBuilder();
		ArrayList<String> links = change.GetFilesChanged();
		String csvLinks;
		if(links.size() > 0)
		{
			for(int i = links.size()-1; i >0; i--) sb.append(links.get(i)).append(",");
			csvLinks = sb.toString();
			csvLinks = csvLinks.substring(0, csvLinks.length()-1); // remove last comma
		}
		else csvLinks = "";
		
		
		newActivity1.setTarget(csvLinks);
		newActivity1.setVerb(change.GetPostedDate().toString());
 
		activityFeed.addActivity(newActivity1, new UpdaterCallback());
	}
	
	
	

}
