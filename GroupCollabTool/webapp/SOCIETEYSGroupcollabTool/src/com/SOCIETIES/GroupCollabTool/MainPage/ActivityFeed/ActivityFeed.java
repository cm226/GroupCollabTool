package com.SOCIETIES.GroupCollabTool.MainPage.ActivityFeed;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import sun.security.x509.AVA;

import com.SOCIETIES.Framework.IActivity;
import com.SOCIETIES.Framework.IPageComponent;
import com.SOCIETIES.GroupCollabTool.Comms.SOCIETIESInterfaceLayer;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.ActivityDescription;

public class ActivityFeed implements IPageComponent
{
	ArrayList<IActivity> m_activities = new ArrayList<IActivity>();
	
	public ActivityFeed()
	{
		SOCIETIESInterfaceLayer intrfacelyr = new SOCIETIESInterfaceLayer();
		ActivityDescription[] activitys =  intrfacelyr.getActiviteys();
		if(activitys != null)
		{
			for(int i = 0 ; i < activitys.length; i++)
				this.m_activities.add(new Activity(activitys[i]));
		}
	}
	
	@Override
	public void writePage(PrintWriter out)
	{		
		out.println("\t\t<div id=\"activityfeed\">");
		out.println("");
	    
		Iterator<IActivity> activityIt = this.m_activities.iterator();		
		
		while(activityIt.hasNext())
		{
			IActivity activity = activityIt.next();
			
			out.println("\t\t\t<div id=\"post\">");
			out.println("\t\t\t\t<img src=\"" + activity.getImageURL() + "\" class=\"profile\">");
			out.println("\t\t\t\t<b>" + activity.getType() + ":</b> " + activity.getdescription());
			out.println("\t\t\t\t<h6>  - Posted 14/02/2013  </h6>");
			out.println("\t\t\t</div> <!-- post -->");
			out.println("");
		}
 
		out.println("\t\t</div>  <!-- activityfeed -->");
		out.println("");
	}

}
