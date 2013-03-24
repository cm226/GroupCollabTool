package com.SOCIETIES.GroupCollabTool.MainPage;

import java.io.PrintWriter;

import com.SOCIETIES.Framework.IPageComponent;

public class Head implements IPageComponent 

{

	
	@Override
	public void writePage(PrintWriter out)
	{
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Group Collaboration Tool</title>");
		out.println("");
		out.println("<script type='text/javascript'>");
		out.println("");
		out.println("function contentTypeChange()");
		out.println("{");
		out.println("\tvar Context= document.getElementById('selectVal');");
		out.println("\twindow.location = 'http://localhost:8090/SOCIETEYSGroupcollabTool/CollabToolServlet?stype='+Context.value;");
		out.println("}");
		out.println("");
		out.println("function feedPost()");
		out.println("{");
		out.println("\tvar feedPost = document.getElementById('feedpost');");
		out.println("\tvar Context= document.getElementById('selectVal');");
		out.println("	");
		out.println("\twindow.location = 'http://localhost:8090/SOCIETEYSGroupcollabTool/CollabToolServlet?stype='+Context.value+\"&feedpost=\"+feedPost.value;");
		out.println("}");
		out.println("");
		out.println("</script> ");
		out.println("");
		out.println("</head>");
		
	}

}
