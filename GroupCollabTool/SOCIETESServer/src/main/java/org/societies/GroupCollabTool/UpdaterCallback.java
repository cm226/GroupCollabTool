package org.societies.GroupCollabTool;

import org.societies.api.activity.IActivityFeedCallback;
//import org.societies.api.schema.activity.MarshaledActivityFeed;
//import org.societies.api.schema.activityfeed.MarshaledActivityFeed;
import org.societies.api.schema.activityfeed.MarshaledActivityFeed;

public class UpdaterCallback implements IActivityFeedCallback {


	@Override
	public void receiveResult(MarshaledActivityFeed arg0) {
		 if(arg0.getAddActivityResponse().isResult())
		 { // retrieve the AddResponse Marshalled object and the value of the result of the operation
			System.out.println("it seemed to work");
		 }
		
	}


}
