package com.SOCIETIES.GroupCollabTool.MainPage;

import java.io.PrintWriter;

import com.SOCIETIES.Framework.IPageComponent;

public class ContentPicker implements IPageComponent 
{
	private String initVal;
	
	public ContentPicker(String initVal)
	{
		if(initVal != null)
			this.initVal = initVal;
		else this.initVal = "";
	}
	
	
	@Override
	public void writePage(PrintWriter out) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("<div id=\"ContentPicker\">")
		.append("<select onchange='javascript:contentTypeChange();' id=\"selectVal\">");
		
		sb.append("<option");
		if(this.initVal.compareTo("All")==0)
			sb.append(" selected=\"selected\"");
		sb.append(">All</option>");
		
		
		sb.append("<option");
			if(this.initVal.compareTo("Documents")==0)
				sb.append(" selected=\"selected\"");
		sb.append(">Documents</option>");
		
		sb.append("<option");
		if(this.initVal.compareTo("Code")==0)
			sb.append(" selected=\"selected\"");
		sb.append(">Code</option>");
	

		
		sb.append("</select>")
	.append("</div> <!-- ContentPicker-->");
		
	out.write(sb.toString());
	}

}
