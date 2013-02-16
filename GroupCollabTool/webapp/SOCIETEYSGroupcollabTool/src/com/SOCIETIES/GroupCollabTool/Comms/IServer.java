package com.SOCIETIES.GroupCollabTool.Comms;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote
{
	ActivityDescription[] getActivitys() throws RemoteException;	
}
