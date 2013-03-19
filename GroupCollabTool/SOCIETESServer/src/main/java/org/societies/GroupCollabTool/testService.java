package org.societies.GroupCollabTool;

import java.util.List;

import org.societies.api.cis.management.ICis;
import org.societies.api.cis.management.ICisManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class testService implements ITestService
{
	private ICisManager myCisManager;
	private static Logger LOG = LoggerFactory.getLogger(testService.class);
	
	private Updater updaterThread;
	private Thread t1 ;
	
	public ICisManager getmyCisManager(){return this.myCisManager;}
	public void setmyCisManager(ICisManager manager){this.myCisManager = manager;}
	
	
	public void initService()
	{
			t1 = new Thread(updaterThread = new Updater(myCisManager, LOG), "updaterThread");
			t1.start();
	}
	
	
	public void onShutdown()
	{
		if(t1 != null && updaterThread != null)
		{
			this.updaterThread.stopUpdateing();
			
			try
			{
				t1.join(1000);
			}
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}