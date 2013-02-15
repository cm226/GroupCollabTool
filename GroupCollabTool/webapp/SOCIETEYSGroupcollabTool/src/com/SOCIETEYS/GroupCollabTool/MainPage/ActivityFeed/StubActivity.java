package com.SOCIETEYS.GroupCollabTool.MainPage.ActivityFeed;

import java.util.Date;

import com.SOCIETEYS.Framework.IActivity;

public class StubActivity implements IActivity {

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Git";
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return new Date();
	}

	@Override
	public String getdescription() {
		// TODO Auto-generated method stub
		return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc malesuada molestie malesuada. Nulla ultricies viverra porttitor. Nulla ac metus ut orci venenatis interdum non in nunc. Ut et risus a mi dignissim tincidunt sed rutrum arcu. Morbi mattis metus vel tellus dapibus ornare. Pellentesque faucibus mauris lorem. Mauris luctus odio sed diam ornare sagittis. Vestibulum elementum volutpat ligula ut sollicitudin. Nullam dapibus consectetur tellus ac luctus. Sed rhoncus augue id diam pulvinar aliquam. Vivamus tellus purus, pulvinar non placerat tincidunt, dignissim at velit. Sed euismod semper neque, eget tempus leo facilisis vitae. Pellentesque bibendum bibendum lectus, in posuere ipsum luctus a. Aliquam quis eros augue, elementum posuere eros. Fusce lobortis ultrices ipsum ut tempor. ";
	}

	@Override
	public String getLinks() {
		// TODO Auto-generated method stub
		return "https://www.google.co.uk/";
	}

	@Override
	public String getImageURL() {
		// TODO Auto-generated method stub
		return "avatar3.jpg";
	}

}
