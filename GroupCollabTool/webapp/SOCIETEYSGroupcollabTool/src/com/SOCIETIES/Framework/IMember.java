/**
 * Class used for the storage of member data, stores only a SOCIETIES ID such that more 
 * complicated structures can easily be derived from it by simply communicating with the 
 * SOCIETIES server and also such that any interfaces can be rendered both down and up 
 * using it
 */

package com.SOCIETIES.Framework;

public interface IMember 
{
	String m_sUserID = new String();
	
	public String GetID();
}
