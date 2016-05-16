package tessearctntest;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tesseracttest.Midlet;

/**
 *
 * @author Noor
 */
class TestsList extends List implements Runnable,CommandListener{

    Chapitre chapter;
    Cours cours;
    Test[] tests;
    Command back, view, addNew;

    public TestsList(String title, int listType, Chapitre auxChapter, Cours coursAux) {
        super(title, List.IMPLICIT);
        chapter = auxChapter;
        cours=coursAux;
        view = new Command("View", Command.SCREEN, 0);
        back = new Command("Back", Command.BACK, 0);
        addNew = new Command("Add New", Command.SCREEN, 0);
        Thread thread = new Thread(this);
        thread.start();

        addCommand(view);
        addCommand(back);
        addCommand(addNew);

        setCommandListener(this);
    }

    public void run() {
    

        try {
            TestHandler handler = new TestHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection connection;
            connection = (HttpConnection) Connector.open("http://localhost/Business/TestsList.php?chapterId=" +chapter.getIdChapitre());
            DataInputStream dis;
            dis = new DataInputStream(connection.openDataInputStream());
            parser.parse(dis, handler);
            tests = handler.getTests();
            for (int i = 0; i < tests.length; i++) {
                insert(i, String.valueOf(tests[i].getId()), null);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    
    
   

    public void commandAction(Command c, Displayable d) {
    
       if (c == back) {
         
                Midlet.INSTANCE.disp.setCurrent(new ChapterDetails(chapter.getNom(),chapter,cours));
         

        } else if (c == view) {

            Midlet.INSTANCE.disp.setCurrent(new TestDetails(getString(getSelectedIndex()),0, tests[getSelectedIndex()],chapter,null));

        }
        else if (c==addNew)
        {
              Midlet.INSTANCE.disp.setCurrent(new AddTest(chapter.getNom(), cours , "Objectif"));
        }
}

}
