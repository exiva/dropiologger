package net.exiva.dropiologger;

import danger.app.Application;
import danger.app.Event;
import danger.app.Timer;

import danger.ui.AlertWindow;
import danger.ui.Button;
import danger.ui.DialogWindow;
import danger.ui.ProgressBar;
import danger.ui.ScreenWindow;
import danger.ui.TextField;
import danger.ui.EditText;

import danger.util.DEBUG;

public class dropiologgerView extends ScreenWindow implements Resources, Commands {
	DialogWindow bugReport, suggestion;
	TextField bugTitle, suggestionTitle;
	EditText bugText, suggestionText;
	
	public dropiologgerView() {
	}

	public void onDecoded() {
		bugReport = getApplication().getDialog(ID_BUG_REPORT, this);
		suggestion = getApplication().getDialog(ID_SUGGESTION, this);
		bugTitle = (TextField)bugReport.getDescendantWithID(ID_BUG_TITLE);
		bugText = (EditText)bugReport.getDescendantWithID(ID_BUG_TEXT);
		suggestionTitle = (TextField)suggestion.getDescendantWithID(ID_SUGGESTION_TITLE);
		suggestionText = (EditText)suggestion.getDescendantWithID(ID_SUGGESTION_TEXT);
	}

	public static dropiologgerView create() {
		dropiologgerView me = (dropiologgerView) Application.getCurrentApp().getResources().getScreen(ID_MAIN_SCREEN, null);
		return me;
	}

	public boolean receiveEvent(Event e) {
		switch (e.type) {
			//generate an exception, send this exception as a note to drop.io
			case EVENT_GENERATE_EXCEPTION: {
				//The possibilities for this are not just limited to exceptions however,
				//This is what I've used in this example... but you can use it for any type
				//of remote debugging.
				try {
					int numerator = 10;
					int denominator = 0;
					int oops = numerator/denominator;
				} catch (java.lang.ArithmeticException ex) {
					dropio.CreateDropNote("Exception: "+ex.getMessage(), ex.toString());
				}
				return true;
			}
			//as you can see, the drop.io use has been extended out to a bug reporter
			//and feature/suggestion box. There's really no limit on what you can do with this
			//but this is how I've used the implementation.
			case EVENT_BUG: {
				bugReport.show();
				return true;
			}
			case EVENT_SUGGESTION: {
				suggestion.show();
				return true;
			}
			case EVENT_SEND_BUG: {
				dropio.CreateDropNote("Bug: "+bugTitle.toString(), bugText.toString());
				return true;
			}
			case EVENT_SEND_SUGGESTION: {
				dropio.CreateDropNote("Suggestion: "+suggestionTitle.toString(), suggestionText.toString());
				return true;
			}
			default:
			break;
		}
		return super.receiveEvent(e);
	}

    public boolean eventWidgetUp(int inWidget, Event e) {
		switch (inWidget) {
			case Event.DEVICE_BUTTON_CANCEL:
			Application.getCurrentApp().returnToLauncher();
			return true;

			case Event.DEVICE_BUTTON_BACK:
			Application.getCurrentApp().returnToLauncher();
			return true;
		}
		return super.eventWidgetUp(inWidget, e);
	}
}