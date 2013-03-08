package com.SOCIETIES.GroupCollabTool.MainPage;

import java.io.PrintWriter;

import com.SOCIETIES.Framework.IPageComponent;

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
			.append("<script type='text/javascript'>")
			.append("function contentTypeChange()")
			.append("{")
				.append("var myTextField = document.getElementById('selectVal');")
				.append("window.location = 'http://localhost:8090/SOCIETEYSGroupcollabTool/CollabToolServlet?stype='+myTextField.value;")
			.append("}")
			.append("</script>") 
			.append("</head>").toString();
		
		out.write(s);
	}

}
