package com.SOCIETEYS.GroupCollabTool.MainPage.ActivityFeed;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.SOCIETEYS.Framework.IActivity;
import com.SOCIETEYS.Framework.IPageComponent;

public class ActivityFeed implements IPageComponent
{
	ArrayList<IActivity> activitys = new ArrayList<IActivity>();
	
	public ActivityFeed()
	{
		this.activitys.add(new StubActivity());
		this.activitys.add(new StubActivity());
		this.activitys.add(new StubActivity());
		this.activitys.add(new StubActivity());
	}
	
	@Override
	public void writePage(PrintWriter out) {
		out.write("<div id=\"activityfeed\">");
	    
		Iterator<IActivity> activityIt = this.activitys.iterator();		
		
		while(activityIt.hasNext())
		{
			IActivity activity = activityIt.next();
			
			out.write("<div id=\"post\">");
			out.write("<img src=\""+activity.getImageURL()+"\" class=\"profile\">");
			out.write("<b>"+activity.getType()+":</b>");
			out.write(activity.getdescription());
			out.write("<h6>  - Posted 14/02/2013  </h6>");
			out.write("</div> <!-- post -->");
		
		}
 
		out.write("</div>  <!-- activityfeed -->");
	}

}
