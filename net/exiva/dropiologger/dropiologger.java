package net.exiva.dropiologger;

import danger.app.Application;
import danger.app.AppResources;

import danger.net.*;

import danger.util.*;
public class dropiologger extends Application implements Resources, Commands {
	static public dropiologgerView mWindow;
	
	public dropiologger() {
		mWindow = dropiologgerView.create();
		mWindow.show();
	}

	public void launch() {}

	public void resume() {}

	public void suspend() {}
	
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