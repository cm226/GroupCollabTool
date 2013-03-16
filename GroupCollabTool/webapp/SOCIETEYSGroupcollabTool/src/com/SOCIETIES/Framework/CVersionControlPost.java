package com.SOCIETIES.Framework;

import java.util.Date;
import java.util.ArrayList;

public class CVersionControlPost implements IFeedPost
{
	String m_UserID;
    Date m_Date;
    String m_Description;
    ArrayList<String> m_Links;
    
    CVersionControlPost(String UserID, Date Date, String Description, ArrayList<String> Links)
    {
    	m_UserID = UserID;
    	m_Date = Date;
    	m_Description = Description;
    	m_Links = Links;
    }
    
	@Override
	public String GetUserID()
	{
		return m_UserID;
	}
	
	@Override
	public Date GetPostedDate()
	{
		return m_Date;
	}
	
	@Override
	public String GetDescription()
	{
		return m_Description;
	}
	
	public ArrayList<String> GetLinks()
	{
		return m_Links;
	}
}