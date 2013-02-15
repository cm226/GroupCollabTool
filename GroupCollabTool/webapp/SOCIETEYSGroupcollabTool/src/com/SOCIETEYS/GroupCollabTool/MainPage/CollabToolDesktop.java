package com.SOCIETEYS.GroupCollabTool.MainPage;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;


import com.SOCIETEYS.Framework.*;
import com.SOCIETEYS.GroupCollabTool.MainPage.ActivityFeed.ActivityFeed;

public class CollabToolDesktop implements IPage
{

	private ArrayList<IPageComponent> components = new ArrayList<IPageComponent>();
	
	public CollabToolDesktop()
	{
		// possibly make this handled in a static function to avoid overhead of creating it every time?
		components.add(new PageHeader());
		components.add(new Members());
		components.add(new ActivityFeed());
	}
	
	@Override
	public void writePage(PrintWriter out)
	{
		Head h = new Head();
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		h.writePage(out);
		
		out.write("<body>");
		out.write("<div id=\"wrap\">");
		Iterator<IPageComponent> componentIT = this.components.iterator();
		while(componentIT.hasNext())
			componentIT.next().writePage(out);
		
		out.write("</div>");
		out.write("</body>");
		out.write("</html>");
		
	}


}
