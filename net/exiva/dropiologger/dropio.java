package net.exiva.dropiologger;

import danger.app.Application;
import danger.app.AppResources;
import danger.app.Bundle;
import danger.app.Event;
import danger.app.EventType;

import danger.net.HTTPConnection;
import danger.net.HTTPTransaction;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import danger.util.DEBUG;

public class dropio extends Application implements Resources, Commands {

	//these are the values you'll want to change to configure the class
	//obtain an API key at http://api.drop.io/
	// private static String dropio_api_key="b677e1ad7512dab1720bd5ee1702585fc6e894dc";
	private static String dropio_api_key="adsfsadfjlaksdjfl;kasfkasjf;s;lafakekey";
	//create a drop at Http://drop.io
	private static String dropio_drop_name="exiva_testing";
	//set a password for your drop
	private static String dropio_token="test";
	//api settings, these don't need to be changed.
	private static String version = "1.0";
	private static String format = "json";

	public dropio() {
	}
	
	public static void CreateDropNote(String title, String content) {
		String headers = "User-Agent: Danger Hiptop\r\n" + "Content-type: application/x-www-form-urlencoded\r\n";
		HTTPConnection.post("http://api.drop.io/drops/"+dropio_drop_name+"/assets", headers, "version="+version+"&api_key="+dropio_api_key+"&format="+format+"&token="+dropio_token+"&title="+title+"&contents="+content, (short) 0, 1);
	}
	
	public void networkEvent(Object object) {
		if (object instanceof HTTPTransaction) {
			HTTPTransaction t = (HTTPTransaction) object;
				DEBUG.p("Response Code: "+t.getResponse());
				DEBUG.p("Response: "+t.getString());
			if((t.getSequenceID() == 1)) {
				if (t.getResponse() == 200) {
				}
			}
			t = null;
		}
	}
}