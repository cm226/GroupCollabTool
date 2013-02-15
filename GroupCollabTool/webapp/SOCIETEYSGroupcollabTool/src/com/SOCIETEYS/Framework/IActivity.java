package com.SOCIETEYS.Framework;

import java.util.Date;

public interface IActivity 
{
	String getType(); // returns where the activity is from 
	Date getDate(); // return date posted
	String getdescription(); // returns a description of the event
	String getLinks(); // returns any associated urls with the activity (git commit links and so on)
}
