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
class AddTest extends Form implements CommandListener {

    Cours chapter;
    Test test;
    TextField tfDiff, tfDuree;
    String diff, duree, type;
    HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb;
    
    Command cmdNext = new Command("Add", Command.OK, 0);
    Command back = new Command("Back", Command.BACK, 0);

    public AddTest(String title, Cours auxChapter, String Type) {
        super(title);
        chapter = auxChapter;
        tfDiff = new TextField("Difficulte \n", "", 100, TextField.ANY);
        tfDuree = new TextField("duree \n", "", 100, TextField.NUMERIC);
        type = Type;
        

        append(tfDiff);
        append(tfDuree);
        addCommand(cmdNext);
        addCommand(back);
        setCommandListener(this);

    }

    public void commandAction(Command c, Displayable d) {

        if (c == back) {
           
       
            Midlet.INSTANCE.disp.setCurrent(new CourseList("",0));
 }
    
    if (c == cmdNext ) {
            try {

           diff=tfDiff.getString();
           duree=tfDuree.getString();
            hc = (HttpConnection) Connector.open("http://localhost/Business/AddTest.php?cours=" + chapter.getIdCours()+ "&type=" + type + "&diff=" + diff + "&duree=" + duree);
            dis = hc.openDataInputStream();

            int ascii;
            sb = new StringBuffer();

            while ((ascii = dis.read()) != -1) {

                sb.append((char) ascii);

            }

            if (sb.toString().equals("successfully added")) {
                Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                a.setTimeout(3000);
                Midlet.INSTANCE.disp.setCurrent(a);
            } else {
                Alert a = new Alert("Information", sb.toString(), null, AlertType.ERROR);
                a.setTimeout(3000);

                Midlet.INSTANCE.disp.setCurrent(a);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}}
