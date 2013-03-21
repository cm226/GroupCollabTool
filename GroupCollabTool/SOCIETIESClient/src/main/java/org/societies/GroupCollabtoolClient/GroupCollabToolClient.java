package org.societies.GroupCollabtoolClient;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.societies.GroupCollabTool.Comms.*;
import org.societies.api.cis.management.ICisManager;
import org.societies.api.comm.xmpp.interfaces.ICommManager;
import org.societies.api.css.devicemgmt.display.IDisplayableService;
import org.societies.api.identity.IIdentity;
import org.societies.api.personalisation.model.IAction;
import org.societies.api.personalisation.model.IActionConsumer;
import org.societies.api.useragent.monitoring.IUserActionMonitor;
import org.societies.api.schema.servicelifecycle.model.ServiceResourceIdentifier;
import org.societies.api.services.IServices ;

import com.SOCIETIES.GroupCollabTool.Comms.Shared.IServer;

public class GroupCollabToolClient implements IClient, IActionConsumer
{

	private IUserActionMonitor uam;
	private IIdentity userID;
	private ICommManager commsMgr;
	private ServiceResourceIdentifier myServiceID;
	private IServices servics;
	private String myServiceType;
	private List<String> myServiceTypes;
	private RMIServer rmiServer;
	private ICisManager myCisManager;
	
	private static Logger LOG = LoggerFactory.getLogger(GroupCollabToolClient.class);
	
	
	public void initService()
	{
		LOG.info("Client started");
		userID = commsMgr.getIdManager().getThisNetworkNode();
		//myServiceID = this.servics.getMyServiceId(this);
		
		myServiceType = "Collaberation";
		
		myServiceTypes = new ArrayList<String>();
		myServiceTypes.add(myServiceType);
		
		initRMI();
	}
	
	private void initRMI()
	{
		try {
				rmiServer = new RMIServer(uam,userID, myServiceID, myServiceTypes,myCisManager);
	            IServer stub = (IServer) UnicastRemoteObject.exportObject((Remote) rmiServer, 0);

	            // Bind the remote object's stub in the registry
	            Registry registry = LocateRegistry.getRegistry();
	            registry.bind("IServer", stub);

	        } catch (Exception e) {
	        	
	            LOG.error(" Group CollabTool RMI Server exception: " + e.toString());
	            e.printStackTrace();
	        }
	}
	
	
	/*
	 * These methods are called by PersonalisationManager and User Agent
	 * (non-Javadoc)
	 * @see org.societies.api.personalisation.model.IActionConsumer#getServiceIdentifier()
	 */
	
	public ServiceResourceIdentifier getServiceIdentifier()
	{
		return this.myServiceID;
	}
	
	public String getServiceType()
	{
		return this.myServiceType;
	}

	public List<String> getServiceTypes() 
	{
		return myServiceTypes;
	}

	public boolean setIAction(IIdentity arg0, IAction arg1)
	{
		String param = arg1.getparameterName();
		if(param.compareToIgnoreCase("contentType") == 0)
		{
			String value = arg1.getvalue();
			this.rmiServer.updateDefaultType(value);
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	// Societies getters/ setters
	
	public void setuam(IUserActionMonitor uam)
	{
		this.uam = uam;
	}
	
	public void setcommsMgr(ICommManager commMngr)
	{
		this.commsMgr = commMngr;
	}
	
	public void setservics(IServices serv)
	{
		this.servics = serv;
	}

	
	public ICisManager getmyCisManager(){return this.myCisManager;}
	public void setmyCisManager(ICisManager manager){this.myCisManager = manager;}


	
	
	
	
}
