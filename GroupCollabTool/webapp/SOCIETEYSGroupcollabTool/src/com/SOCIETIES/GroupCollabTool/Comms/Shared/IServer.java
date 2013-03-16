package com.SOCIETIES.GroupCollabTool.Comms.Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote
{
	ActivityDescription[] getActivitys() throws RemoteException;
	ActivityDescription[] getActivitys(String type) throws RemoteException;
	
	public void postToFeed(IMember User, String Message);
}
