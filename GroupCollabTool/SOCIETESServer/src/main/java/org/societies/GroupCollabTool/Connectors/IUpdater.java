package org.societies.GroupCollabTool.Connectors;

import java.util.ArrayList;

import org.societies.GroupCollabTool.CVersionControlPost;

public interface IUpdater
{
	public ArrayList<CVersionControlPost> GetPosts();
	
	public ArrayList<CVersionControlPost> GetPosts(String PreviousCall);
}
