/**
 * Interface class used for the storage of activities to be fed to the activity feed.
 * Created as an interface as there may be different variables of activity we may wish to
 * parse differently such as distinguishing user and subversion posts. 
 */

package com.SOCIETIES.Framework;

import java.util.ArrayList;
import java.util.Date;

public interface IActivity 
{
	String getType(); // returns where the activity is from 
	Date getDate(); // return date posted
	String getdescription(); // returns a description of the event
	ArrayList<String> getLinks(); // returns any associated urls with the activity (git commit links and so on)
	String getImageURL(); // returns the image url for the image type 
}
