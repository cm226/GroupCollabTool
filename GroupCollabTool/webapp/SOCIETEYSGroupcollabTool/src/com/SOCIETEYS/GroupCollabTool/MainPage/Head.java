package com.SOCIETEYS.GroupCollabTool.MainPage;

import java.io.PrintWriter;

import com.SOCIETEYS.Framework.IPageComponent;

public class Head implements IPageComponent 

{

	
	@Override
	public void writePage(PrintWriter out)
	{
		String s = new StringBuilder()
			.append("<head>")
			.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">")
			.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />")
			.append("<title>Group Collaboration Tool</title>")
			.append("</head>").toString();
		
		out.write(s);
	}

}
