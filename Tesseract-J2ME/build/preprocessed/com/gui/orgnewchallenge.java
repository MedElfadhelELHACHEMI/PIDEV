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
public class orgnewchallenge extends Form implements CommandListener {

    ChallengeDao cd = new ChallengeDao();
    Challenge challenge;
    Command cmdOk = new Command("OK", Command.SCREEN, 0);
    Command cmdBack = new Command("back", Command.EXIT, 0);
    TextField tfnom = new TextField("Nom", "", 50, TextField.ANY);
    TextField tfdescription = new TextField("Description", "", 50, TextField.ANY);
    ChoiceGroup theme = new ChoiceGroup("Theme", Choice.POPUP);
    DateField tfdate = new DateField("Date", DateField.DATE);

    Command cmddone = new Command("Back", Command.BACK, 0);
    Command cmdd = new Command("Ok", Command.OK, 0);

    public orgnewchallenge(String title) {
        super(title);
        this.challenge = new Challenge(0,0, "", "", "", "", Midlet.INSTANCE.ApprenantCurrent.getOrganisation(), Midlet.INSTANCE.ApprenantCurrent);
        this.addCommand(cmdd);
        this.addCommand(cmddone);
        this.setCommandListener(this);
        theme.append("desktop", null);
        theme.append("mobile", null);
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
            this.challenge.setDate(tfdate.getDate().toString());
            System.out.println(this.challenge.getNom());
            this.cd.ajouterChallenge(this.challenge);
            Midlet.INSTANCE.disp.setCurrent(new OrganisationMenu());
        }
    }

}
