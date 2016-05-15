/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.Dao.ChallengeDao;
import com.Dao.EvenementDao;
import com.Dao.InvitationDao;
import com.entities.Challenge;
import com.entities.Evenement;
import com.entities.Invitations;
import com.entities.Organisation;
import com.entities.Utilisateur;
import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.m3g.Image2D;
import tesseract.Midlet;

/**
 *
 * @author BoB
 */
public class OrganisationMenu extends Canvas implements Runnable {
    int w = getWidth();
    int h = getHeight();
    
    Image right[] = new Image[2];
    Image profiletxt[]= new Image[2];
    Image coachestxt[]= new Image[2];
    Image challengestxt[]= new Image[2];
    Image eventstxt[]= new Image[2];
    
    int slcprofile = 1,slccoaches = 0,slcchallenges = 0,slcevents = 0;
    int slccounter = 0;
    int animate=-1;
    

    public OrganisationMenu(){
        try {
            right[0] = Image.createImage("/com/gui/images/icons/selectedRight.png");
            right[1] = Image.createImage("/com/gui/images/icons/right.png");
            profiletxt[0] = Image.createImage("/com/gui/images/text/profile.png");
            profiletxt[1] = Image.createImage("/com/gui/images/text/profile-selected.png");
            coachestxt[0] = Image.createImage("/com/gui/images/text/coaches.png");
            coachestxt[1] = Image.createImage("/com/gui/images/text/coaches-selected.png");
            challengestxt[0] = Image.createImage("/com/gui/images/text/challenges.png");
            challengestxt[1] = Image.createImage("/com/gui/images/text/challenges-selected.png");
            eventstxt[0] = Image.createImage("/com/gui/images/text/events.png");
            eventstxt[1] = Image.createImage("/com/gui/images/text/events-selected.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    
    }

    protected void paint(Graphics g) {
        if(animate==-1){
        g.setColor(57,58,60);
        g.fillRect(0, 0, w, h);
        g.setColor(36,199,207);
        g.drawRect(10, 10, w-20,75);
        g.drawRect(10, 85, w-20,75);
        g.drawRect(10, 160, w-20,75);
        g.drawRect(10, 235, w-20,75);
        g.drawImage(right[slcprofile], w-50, 23, g.TOP |g.LEFT);
        g.drawImage(right[slccoaches], w-50, 98, g.TOP |g.LEFT);
        g.drawImage(right[slcchallenges], w-50, 173, g.TOP |g.LEFT);
        g.drawImage(right[slcevents], w-50, 248, g.TOP |g.LEFT);
        g.drawImage(profiletxt[slcprofile], 10, 10, g.TOP |g.LEFT);
        g.drawImage(coachestxt[slccoaches], 10, 85, g.TOP |g.LEFT);
        g.drawImage(challengestxt[slcchallenges], 10, 160, g.TOP |g.LEFT);
        g.drawImage(eventstxt[slcevents], 10, 235, g.TOP |g.LEFT);
        }
        if(animate==0){
        g.setColor(57,58,60);
        g.fillRect(0, 0, w, h);
        g.setColor(36,199,207);
        g.drawRect(10, 112, w-20,75);
        g.drawImage(right[1], w-50, 125, g.TOP |g.LEFT);
        g.drawImage(profiletxt[1], 10, 112, g.TOP |g.LEFT);
        }
        if(animate==1){
        g.setColor(57,58,60);
        g.fillRect(0, 0, w, h);
        g.setColor(36,199,207);
        g.drawRect(10, 112, w-20,75);
        g.drawImage(right[1], w-50, 125, g.TOP |g.LEFT);
        g.drawImage(coachestxt[1], 10, 112, g.TOP |g.LEFT);
        }
        if(animate==2){
        g.setColor(57,58,60);
        g.fillRect(0, 0, w, h);
        g.setColor(36,199,207);
        g.drawRect(10, 112, w-20,75);
        g.drawImage(right[1], w-50, 125, g.TOP |g.LEFT);
        g.drawImage(challengestxt[1], 10, 112, g.TOP |g.LEFT);
        }
        if(animate==3){
        g.setColor(57,58,60);
        g.fillRect(0, 0, w, h);
        g.setColor(36,199,207);
        g.drawRect(10, 112, w-20,75);
        g.drawImage(right[1], w-50, 125, g.TOP |g.LEFT);
        g.drawImage(eventstxt[1], 10, 112, g.TOP |g.LEFT);
        }
    }
    
    protected void keyPressed(int keyCode){
        int gameAction = getGameAction(keyCode);
        if (gameAction==DOWN){
            slccounter++;
            if(slccounter==4){
                slccounter=0;
            }
            changeSelection();
            repaint();
        }
        else if (gameAction==UP){
            slccounter--;
            if(slccounter==-1){
                slccounter=3;
            }
            changeSelection();
            repaint();
        }
        else if(gameAction==RIGHT){
            animate(slccounter);
            repaint();
            if(slccounter==0){
            Thread th = new Thread(this, "profile");
            th.start();
            }
            else if(slccounter==1){
            Thread th = new Thread(this, "coaches");
            th.start();
            }
            else if(slccounter==2){
            Thread th = new Thread(this, "challenges");
            th.start();
            }
            else if(slccounter==3){
            Thread th = new Thread(this, "events");
            th.start();
            }
        }
        else if(gameAction==LEFT){
            animate=-1;
            repaint();
        }
    }
    protected void changeSelection(){
        switch (slccounter){
            case 0 :
                slcprofile=1;
                slcchallenges=slccoaches=slcevents=0;
                break;
            case 1:
                slccoaches=1;
                slcprofile=slcchallenges=slcevents=0;
                break;
            case 2:
                slcchallenges=1;
                slcprofile=slccoaches=slcevents=0;
                break;
            case 3:
                slcevents=1;
                slcprofile=slcchallenges=slccoaches=0;
                break;
        }
    }
    protected void animate(int slccounter)
    {
        if(slccounter == 0)
        {
            animate=0;
        }
        if(slccounter == 1)
        {
            animate=1;
        }
        if(slccounter == 2)
        {
            animate=2;
        }
        if(slccounter == 3)
        {
            animate=3;
        }
    }

    public void run() {
        System.out.println(Thread.currentThread().getName());
        if(Thread.currentThread().getName().equals("profile")){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Midlet.INSTANCE.disp.setCurrent(new Orgprofile(Midlet.INSTANCE.ApprenantCurrent.getOrganisation()));
        }
        if(Thread.currentThread().getName().equals("coaches")){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            InvitationDao id = new InvitationDao();
            Invitations[] invTab = id.findByOrganisation(Midlet.INSTANCE.ApprenantCurrent.getOrganisation());
            Utilisateur[]utilisateur = new Utilisateur[invTab.length];
            for (int x = 0 ;x<invTab.length;x++)
            {
                utilisateur[x]=invTab[x].getUtilisateur();
            }
            Midlet.INSTANCE.disp.setCurrent(new Orgcoaches(utilisateur));
        }
        if(Thread.currentThread().getName().equals("challenges")){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            ChallengeDao cd = new ChallengeDao();
            Challenge[]challenge = cd.findByiIdOrg(Midlet.INSTANCE.ApprenantCurrent.getOrganisation().getIdOrganisation());
            Midlet.INSTANCE.disp.setCurrent(new Orgchallenges(challenge));
        }
        if(Thread.currentThread().getName().equals("events")){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            EvenementDao ed = new EvenementDao();
            Evenement[] evenements = ed.findByIdorg(Midlet.INSTANCE.ApprenantCurrent.getOrganisation().getIdOrganisation());
            System.out.println(evenements.length);
            Midlet.INSTANCE.disp.setCurrent(new Orgevents(evenements));
        }
    }
}
