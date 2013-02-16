package com.SOCIETIES.GroupCollabTool.MainPage;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.SOCIETIES.Framework.IPageComponent;
import com.SOCIETIES.GroupCollabTool.MainPage.StubMember;

public class Members implements IPageComponent
{
	ArrayList<StubMember> m_members = new ArrayList<StubMember>();
	
	public Members()
	{
		this.m_members.add(new StubMember());
		this.m_members.add(new StubMember());
		this.m_members.add(new StubMember());
		this.m_members.add(new StubMember());
	}

	@Override
	public void writePage(PrintWriter out)
	{
		out.println("\t\t<div id=\"groupmembers\">");
		out.println("");
		
		Iterator<StubMember> memberIt = this.m_members.iterator();		
		
		while(memberIt.hasNext())
		{
			StubMember member = memberIt.next();
			
			out.println("\t\t\t<div id=\"person\">");
			out.println("\t\t\t\t<img src=\"" + member.getAvatar() + "\" class=\"profile\">");
			out.println("\t\t\t\t" + member.getName() + " <br />");
			out.println("\t\t\t\t<a href=\"mailto:" + member.getEMail() + "\">" + member.getEMail() + "</a>");
			out.println("\t\t\t</div> <!-- person -->");
			out.println("");
		}
		
		out.println("\t\t</div>  <!-- groupmembers -->");
		out.println("");
	}
}
