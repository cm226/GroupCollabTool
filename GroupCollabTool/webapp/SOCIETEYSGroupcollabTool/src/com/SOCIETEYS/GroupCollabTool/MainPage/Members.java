package com.SOCIETEYS.GroupCollabTool.MainPage;

import java.io.PrintWriter;

import com.SOCIETEYS.Framework.IPageComponent;

public class Members implements IPageComponent {

	@Override
	public void writePage(PrintWriter out) {		
		
		 out.write("<div id=\"groupmembers\">");   
		 out.write("<div id=\"person\">");
	     out.write("<img src=\"avatar1.jpg\" class=\"profile\">");
	     out.write("    Bryce Dickson <br />");
	     out.write("<a href=\"mailto:brad1600@gmail.com\">brad1600@gmail.com</a>");
	     out.write("</div> <!-- person -->");
         
	     out.write("<div id=\"person\">");
	     out.write("<img src=\"avatar2.jpg\" class=\"profile\">");
	     out.write("Craig Matear <br />");
	     out.write("<a href=\"mailto:brad1600@gmail.com\">brad1600@gmail.com</a>");
	     out.write("</div> <!-- person -->");
     	 out.write("</div>  <!-- groupmembers -->");

	}

}
