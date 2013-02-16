package com.SOCIETEYS.GroupCollabTool.MainPage;

import java.io.PrintWriter;

import com.SOCIETEYS.Framework.IPageComponent;

public class PageHeader implements IPageComponent {

	@Override
	public void writePage(PrintWriter out)
	{
		out.println("\t\t<div id=\"header\">");
		out.println("\t\tGroup Collaboration Tool");
		out.println("\t\t</div>  <!-- header -->");
		out.println("");
	}

}
