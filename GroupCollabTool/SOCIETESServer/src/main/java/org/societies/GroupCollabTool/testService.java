package org.societies.GroupCollabTool;

import java.util.List;

import org.societies.api.cis.management.ICis;
import org.societies.api.cis.management.ICisManager;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;



public class testService implements ITestService
{
	private ICisManager myCisManager;
	//private Logger logging = LoggerFactory.getLogger(this.getClass());
	
	private Updater updaterThread;
	private Thread t1 ;
	public ICisManager getmyCisManager(){return this.myCisManager;}
	public void setmyCisManager(ICisManager manager){this.myCisManager = manager;}
	
	
	public void initService()
	{
		List<ICis> cisList =  myCisManager.getCisList();
		if(cisList.size() > 0)
		{
			t1 = new Thread(updaterThread = new Updater(cisList.get(0).getActivityFeed()), "updaterThread");
			t1.start();
		}
		else
		{
			//log no CIS's
		}
	}
	
	
	public void onShutdown()
	{
		if(t1 != null && updaterThread != null)
		{
			this.updaterThread.stopUpdateing();
			
			try {
				t1.join(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}