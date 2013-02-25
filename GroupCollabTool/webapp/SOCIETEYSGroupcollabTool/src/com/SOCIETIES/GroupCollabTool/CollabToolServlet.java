package com.SOCIETIES.GroupCollabTool;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SOCIETIES.GroupCollabTool.Comms.SOCIETIESInterfaceLayer;
import com.SOCIETIES.GroupCollabTool.MainPage.CollabToolDesktop;


/**
 * Servlet implementation class CollabToolServlet
 */
public class CollabToolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollabToolServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//SOCIETIESInterfaceLayer intrfacelyr = new SOCIETIESInterfaceLayer();
		//intrfacelyr.getActiviteys();
		
		
		PrintWriter out = response.getWriter();
		CollabToolDesktop mainPage = new CollabToolDesktop();
		mainPage.writePage(out);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}

}
