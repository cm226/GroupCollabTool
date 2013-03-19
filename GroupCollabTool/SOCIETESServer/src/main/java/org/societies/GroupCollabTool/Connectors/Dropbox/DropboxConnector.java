package org.societies.GroupCollabTool.Connectors.Dropbox;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.societies.GroupCollabTool.CVersionControlPost;
import org.societies.GroupCollabTool.Connectors.IUpdater;

/*
import com.dropbox.client2.jsonextract.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DeltaEntry;
import com.dropbox.client2.DropboxAPI.DeltaPage;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.RESTUtility;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.jsonextract.JsonThing;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.WebAuthSession;
*/




public class DropboxConnector //implements IUpdater
{

	/*
	final static private String APP_KEY = "5txslhtrgt5z6ax";
	final static private String APP_SECRET = "5q8ojaml7fymqrn";
	
	final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
	
	public static final String STATE_FILE = "D:\\programming\\SOCIETIES\\GroupCollabTool\\GroupCollabTool\\SOCIETESServer\\src\\main\\java\\org\\societies\\GroupCollabTool\\Connectors\\Dropbox\\GroupCollabTool.json";
	
	
	// In the class declaration section:
	private DropboxAPI<WebAuthSession> mDBApi;
	
	public DropboxConnector()
	{
		// first time authentication
			File stateFile = new File(STATE_FILE);
			 if(!stateFile.exists())
				try {
					makeLink();
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public ArrayList<CVersionControlPost> getChanges()
	{
		try
		{
			State state = State.load(STATE_FILE);
	
			WebAuthSession session = new WebAuthSession(state.appKey, WebAuthSession.AccessType.APP_FOLDER);
			session.setAccessTokenPair(state.links.get(state.links.keySet().iterator().next())); // bad bad code (but ill fix later..)
			mDBApi = new DropboxAPI<WebAuthSession>(session);
			
			DeltaPage<Entry> deltaEntrys;
			
			ArrayList<CVersionControlPost> posts = new ArrayList<CVersionControlPost>();
			
			do
			{
				//deltaEntrys = mDBApi.delta(null);
				deltaEntrys = mDBApi.delta(state.cursor);
				List<DeltaEntry<Entry>> list = deltaEntrys.entries;
				Iterator<DeltaEntry<Entry>> entryIt = list.iterator();
				while(entryIt.hasNext())
				{
					CVersionControlPost vcp = buildVersionControlPost(entryIt.next());
					if(vcp != null) posts.add(vcp);
				}
				state.cursor = deltaEntrys.cursor;
			}
			while (deltaEntrys.hasMore);
			
			
			state.save(STATE_FILE);
			return posts;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void printPosts(Iterator<CVersionControlPost> postsIt)
	{
		while(postsIt.hasNext())
		{
			CVersionControlPost n = postsIt.next();
			System.out.println("made by: "+n.GetUserID());
			System.out.println("on: "+n.GetPostedDate());
			System.out.println("for: "+n.GetDescription());
			System.out.println("involving: "+n.GetFilesChanged().get(0));
			
		}
		
	}
	
	private CVersionControlPost buildVersionControlPost(DeltaEntry<Entry> entry)
	{
		Entry i = entry.metadata;
		if(i == null) return null; // if i = null the path doesn't exist on dropbox's copy of the file system i.e we don't care bout it
		
		String filename = i.fileName();
		
		Date lastModDate = RESTUtility.parseDate(i.modified);
		String linkToFile = i.path;
		
		String message = "";
		if(!i.isDir) message =  "File "+filename+" was modified";
		else message =  "Directory "+filename+" was created";
			
		
		ArrayList<String> links = new ArrayList<String>();
		links.add("https://www.dropbox.com/home/Apps/SocietiesGroupCollabTool");
		CVersionControlPost vcp = new CVersionControlPost("dropbox", lastModDate, message, links);
		return vcp;
		
	}
	
	/*
	 * Call this function the first time to get dropbox to authenticate the 
	 * GroupCollabTool folder in dropbox
	 * 
	 * It will create a file at the location specified in STATE_FILE above 
	 * you only need to (and should) call it only once
	 * 
	 */
/*
	public void makeLink() throws Exception
	{
		AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		State state = new State(appKeys,null);
		
		 
		 WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
		 
		 mDBApi = new DropboxAPI<WebAuthSession>(session);
		 
		 
	        // Make the user log in and authorize us.
	        WebAuthSession.WebAuthInfo info = null;
			try {
				info = session.getAuthInfo();
			} catch (DropboxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("1. Go to: " + info.url);
	        System.out.println("2. Allow access to this app.");
	        System.out.println("3. Press ENTER.");
	        
	        try {
	            while (System.in.read() != '\n') {}
	        }
	        catch (IOException ex) {
	        	ex.printStackTrace();
	        }

	        // This will fail if the user didn't visit the above URL and hit 'Allow'.
	        String uid = session.retrieveWebAccessToken(info.requestTokenPair);
	        AccessTokenPair accessToken = session.getAccessTokenPair();
	        System.out.println("Link successful.");

	        state.links.put(uid, accessToken);
	        state.save(STATE_FILE);
		
	}
	

	@Override
	public ArrayList<CVersionControlPost> GetPosts()
	{
		return null;//getChanges();
	}

	@Override
	public ArrayList<CVersionControlPost> GetPosts(String PreviousCall)
	{
		return null; //getChanges();
	}
	*/

}
