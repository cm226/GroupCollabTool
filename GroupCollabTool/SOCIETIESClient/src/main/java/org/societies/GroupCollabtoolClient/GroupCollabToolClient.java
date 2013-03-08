package org.societies.GroupCollabtoolClient;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.societies.GroupCollabTool.Comms.*;
import org.societies.api.css.devicemgmt.display.IDisplayableService;

import com.SOCIETIES.GroupCollabTool.Comms.Shared.IServer;

public class GroupCollabToolClient implements IClient
{

	private static Logger LOG = LoggerFactory.getLogger(GroupCollabToolClient.class);
	
	public void initService()
	{
		LOG.info("Client started");
		initRMI();
	}
	
	private void initRMI()
	{
		try {
	            RMIServer obj = new RMIServer();
	            IServer stub = (IServer) UnicastRemoteObject.exportObject((Remote) obj, 0);

	            // Bind the remote object's stub in the registry
	            Registry registry = LocateRegistry.getRegistry();
	            registry.bind("IServer", stub);

	        } catch (Exception e) {
	        	
	            LOG.error(" Group CollabTool RMI Server exception: " + e.toString());
	            e.printStackTrace();
	        }
	}
	
	

}
