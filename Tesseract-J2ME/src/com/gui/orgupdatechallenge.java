/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.Dao.ChallengeDao;
import com.entities.Challenge;
import java.util.Date;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import tesseract.Midlet;

/**
 *
 * @author BoB
 */
public class orgupdatechallenge extends Form implements CommandListener {

    ChallengeDao cd = new ChallengeDao();
    Challenge challenge;
    Command cmdOk = new Command("OK", Command.SCREEN, 0);
    Command cmdBack = new Command("back", Command.EXIT, 0);
    TextField tfnom = new TextField("Nom", "", 50, TextField.ANY);
    TextField tfdescription = new TextField("Description", "", 50, TextField.ANY);
    ChoiceGroup theme = new ChoiceGroup("Theme", Choice.POPUP);
    TextField tfdate = new TextField("Date", "", 50, TextField.ANY);

    Command cmddone = new Command("Back", Command.BACK, 0);
    Command cmdd = new Command("Ok", Command.OK, 0);

    public orgupdatechallenge(String title, Challenge challenge) {
        super(title);
        theme.append("desktop", null);
        theme.append("mobile", null);
        this.challenge = challenge;
        this.tfnom.setString(this.challenge.getNom());
        this.tfdescription.setString(this.challenge.getDescription());
        if (this.challenge.getTheme().equals("desktop")) {
            this.theme.setSelectedIndex(0, true);
        }
        if (this.challenge.getTheme().equals("mobile")) {
            this.theme.setSelectedIndex(1, true);
        }

        this.tfdate.setString(this.challenge.getDate());
        this.addCommand(cmdd);
        this.addCommand(cmddone);
        this.setCommandListener(this);
        this.append(tfnom);
        this.append(tfdescription);
        this.append(theme);
        this.append(tfdate);

    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmddone) {
            Midlet.INSTANCE.disp.setCurrent(new OrganisationMenu());
        }
        if (c == cmdd) {
            this.challenge.setNom(tfnom.getString().trim());
            this.challenge.setDescription(tfdescription.getString().trim());
            this.challenge.setTheme(theme.getString(theme.getSelectedIndex()));
            this.challenge.setDate(tfdate.getString());
            System.out.println(this.challenge.getNom());
            this.cd.modifierChallenge(this.challenge);
            Midlet.INSTANCE.disp.setCurrent(new OrganisationMenu());
        }
    }

}
