package com.SOCIETIES.Framework;

import java.util.ArrayList;

public interface IUpdater
{
	public ArrayList<CVersionControlPost> GetPosts();
	
	public ArrayList<CVersionControlPost> GetPosts(String PreviousCall);
}
