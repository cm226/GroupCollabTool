package org.societies.GroupCollabTool.Connectors;

import java.util.Date;

public interface IFeedPost
{
	String GetUserID();
    Date GetPostedDate();
    String GetDescription();
}
