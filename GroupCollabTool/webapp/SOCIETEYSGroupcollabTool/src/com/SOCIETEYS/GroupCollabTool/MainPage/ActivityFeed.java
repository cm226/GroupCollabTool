package com.SOCIETEYS.GroupCollabTool.MainPage;

import java.io.PrintWriter;

import com.SOCIETEYS.Framework.IPageComponent;

public class ActivityFeed implements IPageComponent {

	@Override
	public void writePage(PrintWriter out) {
		out.write("<div id=\"activityfeed\">");
	        
		out.write("<div id=\"post\">");
		out.write("<img src=\"avatar1.jpg\" class=\"profile\">");
		out.write("<b>Bryce Dickson:</b> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc malesuada molestie malesuada. Nulla ultricies viverra porttitor. Nulla ac metus ut orci venenatis interdum non in nunc. Ut et risus a mi dignissim tincidunt sed rutrum arcu. Morbi mattis metus vel tellus dapibus ornare. Pellentesque faucibus mauris lorem. Mauris luctus odio sed diam ornare sagittis. Vestibulum elementum volutpat ligula ut sollicitudin. Nullam dapibus consectetur tellus ac luctus. Sed rhoncus augue id diam pulvinar aliquam. Vivamus tellus purus, pulvinar non placerat tincidunt, dignissim at velit. Sed euismod semper neque, eget tempus leo facilisis vitae. Pellentesque bibendum bibendum lectus, in posuere ipsum luctus a. Aliquam quis eros augue, elementum posuere eros. Fusce lobortis ultrices ipsum ut tempor. <br />");
		out.write("<h6>  - Posted 14/02/2013  </h6>");
		out.write("</div> <!-- post -->");
 
		out.write("<div id=\"post\">");
		out.write("<img src=\"avatar2.jpg\" class=\"profile\">");
		out.write("<b>Craig Matear:</b> Praesent semper commodo porttitor. Quisque mollis sollicitudin neque in tempus. Sed faucibus dignissim mi. Nulla interdum est sed ligula accumsan suscipit. Sed eu laoreet tellus. Mauris vel ante neque, at iaculis tellus. Duis vitae dictum tellus. Integer quis eros orci. Maecenas vel arcu non libero adipiscing cursus vitae non massa. Ut nibh metus, hendrerit et euismod sed, convallis varius lorem. Praesent et metus risus. Praesent in dolor in leo lacinia vulputate id vel augue. Ut porta porttitor dui sit amet rutrum. <br />");
		out.write("<h6>  - Posted 11/02/2013  </h6>");
		out.write("</div> <!-- post -->");
 
		out.write("<div id=\"post\">");
		out.write("<img src=\"avatar3.jpg\" class=\"profile\">");
		out.write("<b>Git:</b> In convallis placerat nisi, ultricies aliquet ligula fermentum nec. Etiam sollicitudin, massa vel egestas placerat, sem diam cursus augue, quis molestie velit nunc ac dolor. Nulla semper nunc nulla, sit amet tincidunt enim. Phasellus vehicula, justo sit amet condimentum aliquet, arcu massa volutpat metus, vitae lobortis odio diam ut tortor. Fusce sit amet lorem purus. Curabitur vitae elementum velit. Duis et odio erat, venenatis dictum tellus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. <br />");
		out.write("<h6>  - Posted 10/02/2013  </h6>");
		out.write("</div> <!-- post -->");
 
		out.write("</div>  <!-- activityfeed -->");
	}

}
