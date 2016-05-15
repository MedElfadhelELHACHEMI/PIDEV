/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.Dao.InvitationDao;
import com.entities.Invitations;
import com.entities.Utilisateur;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import tesseract.Midlet;

/**
 *
 * @author BoB
 */
public class Orginvitations extends Canvas {

    int w = getWidth();
    int h = getHeight();

    int i;
    Image choice;

    int invit = 1;

    Invitations[] invits;
    Utilisateur[] coaches;
    HttpConnection httpConnection;
    DataInputStream dataInputStream;
    StringBuffer stringBuffer = new StringBuffer("");

    public Orginvitations(Invitations invits[], Utilisateur coaches[]) {
        this.invits = invits;
        this.coaches = coaches;
        try {
            choice = Image.createImage("/com/gui/images/buttons/invitation_choice.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        i = 0;
        verifList();
    }

    protected void paint(Graphics g) {
        g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE));
        if (invit == 1) {
            if(invits[i].getUtilisateur().getSexe().equals("F"))
            {
                g.setColor(233,30,99);
            }
            else if(invits[i].getUtilisateur().getSexe().equals("M"))
            {
                g.setColor(21,101,192);
            }
            else{
                g.setColor(57,58,60);
            }
            
            g.fillRect(0, 0, w, h);
            g.setColor(255,255,255);
            g.drawString(invits[i].getUtilisateur().getNom(), w / 2, (h - 42) / 4, Graphics.HCENTER | Graphics.BASELINE);
            g.drawString(invits[i].getUtilisateur().getPrenom(), w / 2, (h - 42) * 2 / 4, Graphics.HCENTER | Graphics.BASELINE);
            g.drawString(String.valueOf(invits[i].getUtilisateur().getTel()), w / 2, (h - 42) * 3 / 4, Graphics.HCENTER | Graphics.BASELINE);
            g.drawString(invits[i].getUtilisateur().getMail(), w / 2, (h - 42), Graphics.HCENTER | Graphics.BASELINE);

        }
        if (invit == 0) {
            g.setColor(57, 58, 60);
            g.fillRect(0, 0, w, h);
            g.setColor(36, 199, 207);
            g.drawString("no invitations", w / 2, w / 2, Graphics.HCENTER | Graphics.BASELINE);
        }
        g.drawImage(choice, 0, 268, g.TOP | g.LEFT);
    }

    protected void keyPressed(int keyCode) {
        int gameAction = getGameAction(keyCode);

        if (gameAction == DOWN) {
            if (invit == 1) {

                i++;
                if (i >= invits.length) {
                    i = 0;
                }
                verifList();
            }

        } else if (gameAction == UP) {
            if (invit == 1) {
                i--;
                if (i < 0) {
                    i = 0;
                }
                verifList();
            }
        } else if (gameAction == RIGHT) {
            if (invit == 1) {
                invits[i].setEtat("VAL");
                InvitationDao id = new InvitationDao();
                id.modifierInvitation(invits[i]);
                this.mail(1,invits[i].getUtilisateur().getMail().trim());
                verifList();
            }
        } else if (gameAction == LEFT) {
            if (invit == 1) {
                invits[i].setEtat("NON");
                InvitationDao id = new InvitationDao();
                id.modifierInvitation(invits[i]);
                this.mail(0,invits[i].getUtilisateur().getMail());
                verifList();
            }

        } else if (gameAction == FIRE) {
            Midlet.INSTANCE.disp.setCurrent(new Orgcoaches(coaches));
            verifList();

        }

    }

    public void verifList() {
        int count = 0;
        while (invits[i].getEtat().equals("VAL")) {
            i++;
            count++;

            if (count >= invits.length) {
                invit = 0;
                break;
            }
            if (i >= invits.length) {
                i = 0;
            }
        }
        repaint();
    }
    public void mail(int etat,String mail)
    {
        String msg="";
     if(etat==1){
          msg="you_have_been_accepted_into_tesseract_Corp_organisation"+Midlet.INSTANCE.ApprenantCurrent.getOrganisation().getNom().trim();
       
     }else if(etat==0){
          msg="your_request_to_join_organisation"+Midlet.INSTANCE.ApprenantCurrent.getOrganisation().getNom().trim()+"has_been_rejected";
     }
     
     String str = "http://localhost/tesseractj2me/tesseract_php/mailController.php?adresse="+mail.trim()
               +"&message="+msg.trim();
    try {
                httpConnection = (HttpConnection) Connector.open(str);
                dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
                StringBuffer sb = new StringBuffer();
                int ch;
                httpConnection.close();
                dataInputStream.close();
                
                while ((ch = dataInputStream.read()) != -1) {
                    sb.append((char) ch);
                }
                System.out.println(sb);
                if ("Message sent!".equals(sb.toString().trim())) {
                    System.out.println("Message sent!");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
