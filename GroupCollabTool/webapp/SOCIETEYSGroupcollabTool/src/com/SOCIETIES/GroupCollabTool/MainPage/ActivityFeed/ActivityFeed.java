package com.SOCIETIES.GroupCollabTool.MainPage.ActivityFeed;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.SOCIETIES.Framework.IActivity;
import com.SOCIETIES.Framework.IPageComponent;
import com.SOCIETIES.GroupCollabTool.Comms.SOCIETIESInterfaceLayer;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.ActivityDescription;

public class ActivityFeed implements IPageComponent
{
	ArrayList<IActivity> m_activities = new ArrayList<IActivity>();
	
	public ActivityFeed(String contentType)
	{
		SOCIETIESInterfaceLayer intrfacelyr = new SOCIETIESInterfaceLayer();
		ActivityDescription[] activitys =  intrfacelyr.getActiviteys(contentType);
		
		if(activitys != null)
		{
			for(int i = 0 ; i < activitys.length; i++)
				this.m_activities.add(new Activity(activitys[i]));
		}
		
		/*for(int i = 0 ; i < 10; i++)
		{
			m_activities.add(new Activity());
		}*/
	}
	
	@Override
	public void writePage(PrintWriter out)
	{		
		out.println("\t\t<div id=\"activityfeed\">");
		out.println("");
		
		out.println("\t\t\t<form method=\"get\">");
		out.println("\t\t\t\t<textarea rows=\"3\" cols=\"30\" class=\"box\" name=\"feedpost\" id=\"feedpost\"></textarea>");
		out.println("\t\t\t\t<!--<input type=\"text\" class=\"box\" name=\"message\"><br>-->");
		out.println("\t\t\t\t<input type=\"button\" value=\"Post\" onclick='javascript:feedPost();'>");
		out.println("\t\t\t</form>");
		out.println("");
		
	    
		Iterator<IActivity> activityIt = this.m_activities.iterator();		
		
		while(activityIt.hasNext())
		{
			IActivity activity = activityIt.next();
			
			out.println("\t\t\t<div id=\"post\">");
			out.println("\t\t\t\t<img src=\"" + activity.getImageURL() + "\" class=\"profile\">");
			out.println("\t\t\t\t<b>" + activity.getType() + ":</b> " + activity.getdescription() + "<br>");
			out.println("\t\t\t\tRelated Links: ");
			
			if(activity.getLinks().size() != 0)
				out.println("<a href=\"" + activity.getLinks().get(0) + "\">" + 1 + "</a>");
			
			for(int i = 1; i < activity.getLinks().size(); i++)
			{
				out.println(", <a href=\"" + activity.getLinks().get(i) + "\">" + (i+1) + "</a>");
			}
			
			out.println("\t\t\t\t<h6>  - Posted " + activity.getDate() +  "  </h6>");
			out.println("\t\t\t</div> <!-- post -->");
			out.println("");
		}
 
		out.println("\t\t</div>  <!-- activityfeed -->");
		out.println("");
	}

}
