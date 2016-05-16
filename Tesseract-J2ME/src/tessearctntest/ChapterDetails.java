package tessearctntest;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import tessearctntest.Chapitre;
import tessearctntest.ChaptersList;
import tessearctntest.Cours;
import tessearctntest.TestsList;
import tesseracttest.Midlet;

/**
 *
 * @author Noor
 */
public class ChapterDetails extends Form implements CommandListener {

    Command back,  viewTestsCmd;
    StringItem details = null;
    Chapitre chapter;
    Cours cours;

    public ChapterDetails(String title, Chapitre auxChap, Cours auxCours) {
        super(title);
        chapter = auxChap;
        cours = auxCours;
        back = new Command("Back", Command.BACK, 0);
        addCommand(back);
        viewTestsCmd = new Command("View Tests", Command.SCREEN, 0);
        addCommand(viewTestsCmd);
               details = new StringItem("Chapter n° " + chapter.getNumero(), chapter.getNom() + "\n Description : " + chapter.getDescription() + "\n Summary " + chapter.getResume());

        append(details);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == back) {
            Midlet.INSTANCE.disp.setCurrent(new ChaptersList("", 0, cours));
        }
        if (c==viewTestsCmd)
        {
               Midlet.INSTANCE.disp.setCurrent(new TestsList(chapter.getNom()+"  Tests",0,chapter,cours));
        }

    }

}
