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
		out.println("var feedPost = document.getElementById('feedpost');");
		out.println("var Context= document.getElementById('selectVal');");
		out.println("");
		out.println("var NextPage = 'http://localhost:8090/SOCIETEYSGroupcollabTool/CollabToolServlet';");
		out.println("");
		out.println("if((feedPost.value.length > 0) && (Context.value.length > 0))");
		out.println("{");
		out.println("NextPage += \"?stype=\"+Context.value+\"&feedpost=\"+feedPost.value;");
		out.println("}");
		out.println("else if(feedPost.value.length > 0)");
		out.println("{");
		out.println("NextPage += \"?feedpost=\"+feedPost.value;");
		out.println("}");
		out.println("else if(Context.value.length > 0)");
		out.println("{");
		out.println("NextPage += \"?stype=\"+Context.value;");
		out.println("}");
		out.println("");
		out.println("window.location = NextPage;");
		out.println("}");
		out.println("");
		out.println("</script> ");
		out.println("");
		out.println("</head>");
		
	}

}
