package org.societies.GroupCollabTool;



import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;

import org.societies.api.activity.IActivity;
import org.societies.api.activity.IActivityFeed;
import org.societies.api.cis.management.ICis;
import org.societies.api.cis.management.ICisManager;

import org.societies.GroupCollabTool.Connectors.IUpdater;
//import org.societies.GroupCollabTool.Connectors.Dropbox.DropboxConnector;
import org.societies.GroupCollabTool.Connectors.Git.CGitComponent;

import com.SOCIETIES.GroupCollabTool.Comms.Shared.IServer;

public class Updater implements Runnable
{
	private ICisManager manager;
	private boolean running = true;
	private Logger log;
	private List<IUpdater> connectors;
	
	Updater( ICisManager cisManager, Logger log)
	{
		this.manager = cisManager;
		this.log  = log;
		this.connectors = new ArrayList<IUpdater>();
		
		this.connectors.add(new CGitComponent("cm226","GroupCollabTool"));
		//this.connectors.add(new DropboxConnector());
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
				
				Iterator<IUpdater> connectorIt = this.connectors.iterator();
				while(connectorIt.hasNext())
				{
					IUpdater updater = connectorIt.next();
					ArrayList<CVersionControlPost> posts =  updater.GetPosts();
					Iterator<CVersionControlPost> postIt = posts.iterator();
					while(postIt.hasNext())
						addToActivityFeed(cisList.get(0).getActivityFeed(),postIt.next());
					
					
				}
				/*ArrayList<String> changes = new ArrayList<String>();
				changes.add("change");
				CVersionControlPost vcp = new CVersionControlPost("User", new Date(), "desc", changes);
				addToActivityFeed(cisList.get(0).getActivityFeed(),vcp);*/
				
			
				log.info("added to activity feed");
			}
			else
			{
				log.warn("No CIS's to moniter");
			}
			
			try 
			{
				Thread.sleep(10000);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // increase this later, prob better to use asinc callback in future 
		}

	}
	
	public void stopUpdateing()
	{
		this.running = false;
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
			int j = links.size();
			for(int i = 0; i < j; i++)
			{
				log.info("i:"+i);
				sb.append(links.get(i)).append(",");
			}
			log.info("size:"+j);
			csvLinks = sb.toString();
			
			log.info("csvLinkLen: "+csvLinks.length());
			csvLinks = csvLinks.substring(0, csvLinks.length()-1); // remove last comma
		}
		else csvLinks = "";
		
		
		newActivity1.setTarget(csvLinks);
		newActivity1.setVerb(change.GetPostedDate().toString());
 
		activityFeed.addActivity(newActivity1, new UpdaterCallback());
	}
	
	
	

}
