package com.SOCIETIES.Framework;

import java.util.Date;
import java.util.ArrayList;

public class CVersionControlPost implements IFeedPost
{
	String m_UserID;
    Date m_Date;
    String m_Description;
    ArrayList<String> m_FilesChanged;
    
	@Override
	public String GetUserID()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Date GetPostedDate()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String GetDescription()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<String> GetFilesChanged()
	{
		return null;
	}
}