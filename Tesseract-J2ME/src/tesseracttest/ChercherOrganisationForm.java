/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesseracttest;

import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Graphics;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.List;
import com.sun.lwuit.TextField;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.list.DefaultListModel;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;

/**
 *
 * @author haikal
 *
 */
public class ChercherOrganisationForm extends Form implements Runnable {

    private Command ok;
    private Command back;
    TextField textField;
    Label label;
    OrganismeContainer oc = new OrganismeContainer();
    static List dlm;
    Image img;

    public ChercherOrganisationForm(String title) {
        super();
        getStyle().setFgColor(158);

        ok = new Command("OK");
        textField = new TextField("");
        label = new Label("Organisation's name");
        label.getStyle().setBgTransparency(255);
        textField.setLabelForComponent(label);

//        
        addComponent(label);

        back = new Command("BACK");
        this.addCommandListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (ae.getCommand().equals(back)) {
                    Midlet.INSTANCE.disp.setCurrent(new BoiteMailCanvas());
                } else if (ae.getCommand().equals(ok)) {
                    Midlet.INSTANCE.disp.setCurrent(new ListOrganismeResearch(textField.getText()));
                }
            }
        });
        addCommand(back);
        addCommand(ok);

        addComponent(textField);
        addComponent(oc);
        repaint();
    }

    public void paintBackground(Graphics g) {

    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void paint(Graphics g) {
        g.setColor(0xDD624B);
        g.fillRect(0, 0, getWidth(), getHeight());
        try {
            img = Image.createImage("/tesseracttest/images/chercher.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        g.drawImage(img, 0, 0);
        textField.setY(getHeight() / 2);

        getMenuBar().getStyle().setBgColor(0xDD624B);

    }

    public void keyPressed(int keyCode) {
        int code = Display.getInstance().getGameAction(keyCode);
        if (code == Canvas.FIRE) {
            Midlet.INSTANCE.disp.setCurrent(new ListOrganismeResearch(textField.getText()));

        }
    }

}
