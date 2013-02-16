/*
 * Main/home page for the SOCIETIES Group Collaboration Tool
 */

package com.SOCIETIES.GroupCollabTool.MainPage;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.SOCIETIES.Framework.*;
import com.SOCIETIES.GroupCollabTool.MainPage.ActivityFeed.ActivityFeed;

public class CollabToolDesktop implements IPage
{

	private ArrayList<IPageComponent> components = new ArrayList<IPageComponent>();
	
	public CollabToolDesktop()
	{
		// possibly make this handled in a static function to avoid overhead of creating it every time?
		components.add(new PageHeader());
		components.add(new ActivityFeed());
		components.add(new Members());
	}
	
	@Override
	public void writePage(PrintWriter out)
	{
		//First print all of the header data for this page
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		out.println("");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Group Collaboration Tool</title>");
		out.println("</head>");
		out.println("");
		
		//Start work on the body of the page
		out.println("<body>");
		out.println("");
		out.println("\t<div id=\"wrap\">");
		out.println("");
		
		//Print page header, member list and activity feed
		Iterator<IPageComponent> componentIT = this.components.iterator();
		while(componentIT.hasNext())
			componentIT.next().writePage(out);
		
		//Close all still open tags
		out.println("\t</div> <!-- Wrap -->");
		out.println("");
		out.println("</body>");
		out.println("</html>");
		
	}


}
