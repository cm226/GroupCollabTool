package com.SOCIETIES.Framework;

import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CGitComponent implements IUpdater {
	
	private String m_sGitURL;
	private String m_sLastSHA;
	
	public CGitComponent(String s_GitURL) {
		m_sGitURL = s_GitURL;
	}
	
	public String GetLastCall()
	{
		return m_sLastSHA;
	}
	
	@Override
	public ArrayList<CVersionControlPost> GetPosts()
	{
		String content = null;
		URLConnection connection = null;
		try {
		  connection =  new URL(m_sGitURL).openConnection();
		  Scanner scanner = new Scanner(connection.getInputStream());
		  scanner.useDelimiter("\\Z");
		  content = scanner.next();
		}catch ( Exception ex ) {
		    ex.printStackTrace();
		}
		
		ArrayList<CVersionControlPost> hVersionControlPosts = new ArrayList<CVersionControlPost>();
		
		JSONArray MainArray = new JSONArray(content);
		JSONObject hCurrentJson = null;
		
		for(int i=0; i<MainArray.length(); i++)
		{
			hCurrentJson = (JSONObject)MainArray.get(i);
			
			hVersionControlPosts.add(readPost(hCurrentJson));
		}
		
		hVersionControlPosts.get(hVersionControlPosts.size());
		return hVersionControlPosts;
	}
	
	@Override
	public ArrayList<CVersionControlPost> GetPosts(String PreviousCall)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private CVersionControlPost readPost(JSONObject JSON_Object)
	{
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
		ArrayList<String> URL = new ArrayList<String>();
		
		String Commiter = JSON_Object.getJSONObject("commit").getJSONObject("committer").getString("date");
		Date CommitDate = new Date();
		try {
			CommitDate = formatter.parse(JSON_Object.getJSONObject("commit").getJSONObject("committer").getString("date").substring(0, 24));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Comment = JSON_Object.getJSONObject("commit").getString("message");
		URL.add("https://github.com/cm226/GroupCollabTool/commit/" + JSON_Object.getString("sha"));
		
		CVersionControlPost CurrentPost = new CVersionControlPost(
				Commiter,
				CommitDate,
				Comment,
				URL
			);
		
		return CurrentPost;
	}	
}
