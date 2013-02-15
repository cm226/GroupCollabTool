package com.SOCIETEYS.GroupCollabTool.MainPage;

import java.io.PrintWriter;

import com.SOCIETEYS.Framework.IPageComponent;

public class PageHeader implements IPageComponent {

	@Override
	public void writePage(PrintWriter out) {
		out.write("<div id=\"header\">");
		out.write("Group Collaboration Tool");
		out.write("</div>  <!-- header -->");

	}

}
