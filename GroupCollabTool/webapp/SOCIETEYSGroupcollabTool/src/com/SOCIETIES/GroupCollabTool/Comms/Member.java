package com.SOCIETIES.GroupCollabTool.Comms;

import com.SOCIETIES.Framework.IMember;

public class Member implements IMember
{
	public Member()
	{
		m_sFirstName = "Bryce";
		m_sLastName = "Dickson";
		m_sEMail = "bfd1@hw.ac.uk";
	}
	
	String m_sFirstName = new String();
	String m_sLastName = new String();
	String m_sEMail = new String();
	String m_sImage = new String();
	
	public String GetID()
	{
		return m_sUserID;
	}
	
	public String GetFirstName()
	{
		return m_sFirstName;
	}
	
	public String GetLastName()
	{
		return m_sLastName;
	}
	
	public String GetEMail()
	{
		return m_sEMail;
	}
	
	public String GetImage()
	{
		return m_sImage;
	}
}
