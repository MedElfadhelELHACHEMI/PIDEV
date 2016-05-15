/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.Dao.ChallengeDao;
import com.Dao.EvenementDao;
import com.entities.Challenge;
import com.entities.Evenement;
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
public class orgnewevent extends Form implements CommandListener {

    EvenementDao ed = new EvenementDao();
    Evenement event;
    
    TextField tfnom = new TextField("Nom", "", 50, TextField.ANY);
    TextField tfdescription = new TextField("Description", "", 50, TextField.ANY);
    DateField tfdate = new DateField("Date", DateField.DATE);
    TextField tfnbrmax = new TextField("Nombre max", "", 50, TextField.ANY);

    Command cmddone = new Command("Back", Command.BACK, 0);
    Command cmdd = new Command("Ok", Command.OK, 0);

    public orgnewevent(String title) {
        super(title);
        this.event = new Evenement(0,0,"","","","", null);
        this.addCommand(cmdd);
        this.addCommand(cmddone);
        this.setCommandListener(this);
        this.append(tfnom);
        this.append(tfdescription);
        this.append(tfdate);
        this.append(tfnbrmax);
    }

    

    public void commandAction(Command c, Displayable d) {
        if (c == cmddone) {
            Midlet.INSTANCE.disp.setCurrent(new OrganisationMenu());
        }
        if (c == cmdd) {
            this.event.setNom(tfnom.getString().trim());
            this.event.setDescription(tfdescription.getString().trim());
            this.event.setNbr_max(Integer.parseInt(tfnbrmax.getString().trim()));
            this.event.setDate(tfdate.getDate().toString());
           
            this.ed.ajouterEvenement(this.event);
            Midlet.INSTANCE.disp.setCurrent(new OrganisationMenu());
        }
    }

}
