package tessearctntest;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tesseracttest.Midlet;

/**
 *
 * @author Noor
 */
class TestDetails extends List implements CommandListener, Runnable {

    Command cmdRefresh = new Command("refresh", Command.SCREEN, 0);

    Command back;
    Chapitre chapter;
    Cours cours;
    Test test;

    Question[] questions;
    StringBuffer sb;
    Answer[] answers;

    public TestDetails(String title, int listType, Test auxTest, Chapitre auxChapter, Cours coursAux) {
        super(title, List.IMPLICIT);
        chapter = auxChapter;
        cours = coursAux;
        test = auxTest;
        back = new Command("Back", Command.BACK, 0);
        addCommand(back);
        chapter.toString();

        Thread thread = new Thread(this);
        thread.start();

        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == back) {
            Midlet.INSTANCE.disp.setCurrent(new TestsList(chapter.getNom() + " Tests", 0, chapter, null));
            System.out.println(chapter.toString());
        }
        if (c == List.SELECT_COMMAND) {
            AnswersForm form = new AnswersForm("answers");
            form.append("Answers \n");
            form.append(showAnswers(this.getSelectedIndex()));
            Midlet.INSTANCE.disp.setCurrent(form);

        }

    }

    public void run() {
        try {
            setCommandListener(this);
            addCommand(cmdRefresh);
            TestDetailsHandler handler = new TestDetailsHandler();

            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/Business/TestDetails.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, handler);
            questions = handler.getQuestions();

            if (questions.length > 0) {
                deleteAll();
                for (int i = 0; i < questions.length; i++) {
                    append(questions[i].getTask(), null);
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private String showAnswers(int i) {
        sb = new StringBuffer();
        String res = "";
        answers = questions[i].getAnswers();
        if (answers.length > 0) {
            for (int j = 0; j < answers.length; j++) {
                sb.append("* ");
                sb.append(answers[j].getResponse());
                sb.append("\n");
            }
        }
        res = sb.toString();
        return res;

    }

}
