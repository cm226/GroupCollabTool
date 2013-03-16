package com.SOCIETIES.GroupCollabTool.MainPage;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.SOCIETIES.Framework.IPageComponent;
import com.SOCIETIES.GroupCollabTool.Comms.Member;

public class Members implements IPageComponent
{
	ArrayList<Member> m_members = new ArrayList<Member>();
	
	public Members()
	{
		this.m_members.add(new Member("userID", "Bryce", "Dickson", "bfd1@hw.ac.uk", "test.jpg"));
		this.m_members.add(new Member("userID", "Craig", "Matear", "cm226@hw.ac.uk", "test.jpg"));
		this.m_members.add(new Member("userID", "Maciej", "Dura", "msd2@hw.ac.uk", "test.jpg"));
	}

	@Override
	public void writePage(PrintWriter out)
	{
		out.println("\t\t<div id=\"groupmembers\">");
		out.println("");
		
		Iterator<Member> memberIt = this.m_members.iterator();		
		
		while(memberIt.hasNext())
		{
			Member member = memberIt.next();
			
			out.println("\t\t\t<div id=\"person\">");
			out.println("\t\t\t\t<img src=\"" + member.GetImage() + "\" class=\"profile\">");
			out.println("\t\t\t\t" + member.GetFirstName() + " " + member.GetLastName() + " <br />");
			out.println("\t\t\t\t<a href=\"mailto:" + member.GetEMail() + "\">" + member.GetEMail() + "</a>");
			out.println("\t\t\t</div> <!-- person -->");
			out.println("");
		}
		
		out.println("\t\t</div>  <!-- groupmembers -->");
		out.println("");
	}
}
