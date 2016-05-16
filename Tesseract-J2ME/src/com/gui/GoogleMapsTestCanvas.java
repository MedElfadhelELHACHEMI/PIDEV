package com.gui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import tesseract.Midlet;

abstract public class GoogleMapsTestCanvas extends Canvas implements CommandListener
{
	Command back;
	
	Displayable testListScreen;
	
	
	public GoogleMapsTestCanvas( Displayable testListScreen)
	{
		
		this.testListScreen = testListScreen;
		
		addCommand(back = new Command("Retourner", Command.BACK, 1));
		
		setCommandListener(this);
                
	}
	
	public void commandAction(Command c, Displayable d)
	{
		if(c == back)
		{
			Midlet.INSTANCE.disp.setCurrent(testListScreen);
		}
	}
	void showError(String message)
	{
		Alert error = new Alert("Error", message, null, AlertType.ERROR);
		
		Midlet.INSTANCE.disp.setCurrent(error, this);
	}
}
