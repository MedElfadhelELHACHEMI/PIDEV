package com.gui;

import Outils.ImageToByteArray;
import Outils.Outils;
import com.jappit.midmaps.googlemaps.GoogleMaps;
import com.jappit.midmaps.googlemaps.GoogleMapsCoordinates;
import com.jappit.midmaps.googlemaps.GoogleMapsMarker;
import com.jappit.midmaps.googlemaps.GoogleStaticMap;
import com.jappit.midmaps.googlemaps.GoogleStaticMapHandler;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.m3g.Graphics3D;
import javax.microedition.midlet.MIDlet;

public class GoogleMapsZoomCanvas extends GoogleMapsTestCanvas implements GoogleStaticMapHandler {

    GoogleMaps gMaps = null;
    GoogleStaticMap map = null;
    GoogleMapsMarker redMarker = null;
    Command zoomInCommand, zoomOutCommand, saveCommand;
    public static GoogleMapsCoordinates googCor = null;

    public GoogleMapsZoomCanvas( Displayable testListScreen) {
        super( testListScreen);

        addCommand(zoomInCommand = new Command("Zoom avant", Command.OK, 1));
        addCommand(zoomOutCommand = new Command("Zoom arrière", Command.OK, 2));
        addCommand(saveCommand = new Command("Marquer ma position", Command.OK, 3));

        gMaps = new GoogleMaps();

        map = gMaps.createMap(getWidth(), getHeight(), GoogleStaticMap.FORMAT_PNG);

        map.setHandler(this);

        map.setCenter(new GoogleMapsCoordinates(36.899313, 10.188750));
        map.setZoom(15);

        map.update();

    }

    void saveImage2File(byte[] photo) {
        // Receive a photo as byte array
        // Save Image to file
        FileConnection fileConn = null;
        DataOutputStream dos = null;

        try {
            fileConn = (FileConnection) Connector.open(
                    "file:///roo/map00.jpg", Connector.READ_WRITE);
            if (!fileConn.exists()) {
                fileConn.create();
            }
            dos = new DataOutputStream(fileConn.openOutputStream());
            dos.write(photo);
            dos.flush();
            dos.close();
            fileConn.close();

        } catch (IOException ioe) {
            System.out.println("Error!" + ioe);
        }
    }

    protected void paint(Graphics g) {
        map.draw(g, 0, 0, Graphics.TOP | Graphics.LEFT);
    }

    public void GoogleStaticMapUpdateError(GoogleStaticMap map, int errorCode, String errorMessage) {
        showError("map error: " + errorCode + ", " + errorMessage);
    }

    public void GoogleStaticMapUpdated(GoogleStaticMap map) {
        repaint();
    }

    protected void keyPressed(int key) {
        int gameAction = getGameAction(key);

        if (gameAction == Canvas.UP || gameAction == Canvas.RIGHT || gameAction == Canvas.DOWN || gameAction == Canvas.LEFT) {
            map.move(gameAction);
        }
    }

    public void commandAction(Command c, Displayable d) {
        super.commandAction(c, d);

        if (c == zoomInCommand) {
            System.out.println("\n\n zoomInCommand");
            map.zoomIn();
        } else if (c == zoomOutCommand) {
            System.out.println("\n\n zoomOutCommand");
            map.zoomOut();
        } else if (c == saveCommand) {
            System.out.println(" saveCommand");
            googCor = new GoogleMapsCoordinates(899313, 899313);//just for init
            googCor = map.adjust(0, 0);
            //System.out.println("\n\n ===>" + googCor.latitude + "   ,  " + googCor.longitude);
            if (redMarker != null) {
                if (redMarker != null) {
                    map.removeMarker(redMarker);
                }
            }
            redMarker = new GoogleMapsMarker(new GoogleMapsCoordinates(googCor.latitude, googCor.longitude));

            redMarker.setColor(GoogleStaticMap.COLOR_RED);
            redMarker.setSize(GoogleMapsMarker.SIZE_MID);
            redMarker.setLabel('A');

            map.addMarker(redMarker);
            map.update();
            repaint();
            }/*else if (c == saveCommand) {
//         try {
//         System.out.println("\n Listening to save command ..\n\n");
//         Image img = Image.createImage("/logoLogin.png");
//         saveImage2File(ImageToByteArray.getByteArray(img));
//         System.out.println("\n Image saved !\n\n");
//         } catch (IOException ex) {
//         ex.printStackTrace();
//         }
//         }*/

    }
}
