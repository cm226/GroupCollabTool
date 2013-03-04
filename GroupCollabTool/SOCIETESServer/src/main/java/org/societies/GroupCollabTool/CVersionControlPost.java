package org.societies.GroupCollabTool;

import java.util.Date;
import java.util.ArrayList;

public class CVersionControlPost implements IFeedPost
{
	String m_UserID;
    Date m_Date;
    String m_Description;
    ArrayList<String> m_FilesChanged;
    
    CVersionControlPost(String user, Date posted, String description, ArrayList<String> changes )
    {
    	this.m_UserID = user;
    	this.m_Date = posted;
    	this.m_Description = description;
    	this.m_FilesChanged = changes;
    	
    }
    
	@Override
	public String GetUserID()
	{
		// TODO Auto-generated method stub
		return m_UserID;
	}
	
	@Override
	public Date GetPostedDate()
	{
		// TODO Auto-generated method stub
		return this.m_Date;
	}
	
	@Override
	public String GetDescription()
	{
		// TODO Auto-generated method stub
		return this.m_Description;
	}
	
	public ArrayList<String> GetFilesChanged()
	{
		return this.m_FilesChanged;
	}
}