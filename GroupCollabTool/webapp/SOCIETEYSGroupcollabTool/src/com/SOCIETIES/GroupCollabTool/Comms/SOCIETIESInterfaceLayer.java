package com.SOCIETIES.GroupCollabTool.Comms;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.SOCIETIES.GroupCollabTool.Comms.Shared.ActivityDescription;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.IMember;
import com.SOCIETIES.GroupCollabTool.Comms.Shared.IServer;

public class SOCIETIESInterfaceLayer {
	
	 public SOCIETIESInterfaceLayer() {}

	 public ActivityDescription[] getActiviteys(String type)
	 {
	        String host = "localhost";
	        try {
	            Registry registry = LocateRegistry.getRegistry(host);
	            IServer stub = (IServer) registry.lookup("IServer");
	            ActivityDescription[] response;
	            if(type==null) response = stub.getActivitys();
	            else response = stub.getActivitys(type);
	            
	            return response;
	        } catch (Exception e) {
	            System.err.println("Client exception: " + e.toString());
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	 public void makePost(IMember user, String userpost)
	 {
	        String host = "localhost";
	        try {
	            Registry registry = LocateRegistry.getRegistry(host);
	            IServer stub = (IServer) registry.lookup("IServer");
	            stub.postToFeed(user, userpost);

	        } catch (Exception e) {
	            System.err.println("Client exception: " + e.toString());
	            e.printStackTrace();

	        }
	    }

}
