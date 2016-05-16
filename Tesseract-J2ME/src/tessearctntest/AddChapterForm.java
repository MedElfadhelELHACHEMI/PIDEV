/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tessearctntest;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import tesseracttest.Midlet;

/**
 *
 * @author Noor
 */

class AddChapterForm extends Form implements CommandListener {

    TextField tfNom = new TextField("Title \n", "", 10, TextField.ANY);
    TextField tfNumero = new TextField("Number \n", "", 10, TextField.NUMERIC);
    TextField tfDesc = new TextField("Description \n", "", 100, TextField.ANY);
    TextField tfResume = new TextField("Summary \n", "", 100, TextField.ANY);
    String title, number, desc, sum;
    Command cmdNext = new Command("Add", Command.OK, 0);
    Command back = new Command("Back", Command.BACK, 0);
    HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb;
    Cours c1;

    

    public AddChapterForm(String string, Cours cours) {
        super(string);
        append(tfNom);
        append(tfNumero);

        append(tfDesc);
        append(tfResume);
        addCommand(cmdNext);
        addCommand(back);
        setCommandListener(this);

        c1 = cours;

    }

    public void commandAction(Command c, Displayable d) {
        if (c == back) {
            Midlet.INSTANCE.disp.setCurrent(new ChaptersList("", 0, c1));

        }
        if (c == cmdNext) {
            try {
                
               
                title = tfNom.getString();
                number = tfNumero.getString();
                desc = tfDesc.getString();
                sum = tfResume.getString();
                hc = (HttpConnection) Connector.open("http://localhost/Business/AddChapter.php?cours=" +c1.getIdCours() + "&title=" + title  + "&desc=" + desc + "&sum=" + sum);
                dis = hc.openDataInputStream();

                int ascii;
                sb = new StringBuffer();

                while ((ascii = dis.read()) != -1) {

                    sb.append((char) ascii);

                }

                if (sb.toString().equals("successfully added")) {
                    Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                    a.setTimeout(Alert.FOREVER);
                    Midlet.INSTANCE.disp.setCurrent(a);
                   
                } else {
                    Alert a = new Alert("Information", sb.toString(), null, AlertType.ERROR);
                    a.setTimeout(Alert.FOREVER);
                   
                    Midlet.INSTANCE.disp.setCurrent(a);
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

}
