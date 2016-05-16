/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tessearctntest;

import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.StringItem;
import tesseracttest.Midlet;

/**
 *
 * @author Noor
 */
class CourseDetails extends Form implements CommandListener {

    Command chapters, back, finalTest, trainingTest;
    ImageItem imgItem;
    StringItem details = null;
    Cours cours;

    public CourseDetails(String title, Cours course) throws IOException {
        super(title);
        cours = course;
        back = new Command("Back", Command.BACK, 0);
        chapters = new Command("Chapters", Command.SCREEN, 0);
        finalTest = new Command("Add Final Test", Command.SCREEN, 0);
        trainingTest = new Command("Add Training Test", Command.SCREEN, 0);

        addCommand(back);
        addCommand(chapters);
        addCommand(finalTest);
        addCommand(trainingTest);
        System.out.println("!!!!" + course.getAffiche());
        imgItem = new ImageItem("", Image.createImage(course.getAffiche()), 0, title);
        append(imgItem);

        details = new StringItem("", " Title = " + course.getNomCours()
                + " \n Category : " + course.getNomMatiere()
                + "\n Description : " + course.getDescriptionCours());

        append(details);
        setCommandListener(this);

    }

    public void commandAction(Command c, Displayable d) {
        if (c == back) {
            Midlet.INSTANCE.disp.setCurrent(new CourseList("", 0));
        }
        if (c == chapters) {
            Midlet.INSTANCE.disp.setCurrent(new ChaptersList("", 0, cours));
        }
        if (c == finalTest) {
            Midlet.INSTANCE.disp.setCurrent(new AddTest("Add the training test", cours, "Training"));
        }
        if (c == trainingTest) {
            Midlet.INSTANCE.disp.setCurrent(new AddTest("Add the final test", cours, "Final"));
        }
    }

}
