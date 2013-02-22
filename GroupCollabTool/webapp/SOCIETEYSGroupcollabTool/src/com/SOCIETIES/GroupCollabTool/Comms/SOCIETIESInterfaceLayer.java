package com.SOCIETIES.GroupCollabTool.Comms;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.SOCIETIES.GroupCollabTool.Comms.Shared.ActivityDescription;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.IServer;

public class SOCIETIESInterfaceLayer {
	
	 public SOCIETIESInterfaceLayer() {}

	 public void getActiviteys()
	 {
	        String host = "localhost";
	        try {
	            Registry registry = LocateRegistry.getRegistry(host);
	            IServer stub = (IServer) registry.lookup("IServer");
	            ActivityDescription[] response = stub.getActivitys();
	            System.out.println("response: " + response[0].getDesc());
	        } catch (Exception e) {
	            System.err.println("Client exception: " + e.toString());
	            e.printStackTrace();
	        }
	    }

}
