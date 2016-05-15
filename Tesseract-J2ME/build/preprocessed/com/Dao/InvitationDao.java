/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dao;

import com.entities.Invitations;
import com.entities.Organisation;
import com.handler.InvitationHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Maynoo
 */
public class InvitationDao {

    Invitations[] invitationTab;
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/tesseractj2me/tesseract_php/InvitationDao.php?action=";

    public void ajouterInvitation(Invitations i) {
        try {

            hc = (HttpConnection) Connector.
                        open(url + "ajouterInvitation"
                                    + "&id=" + i.getIdInvitation()
                                    + "&id_organisation=" + i.getOrganisation().getIdOrganisation()
                                    + "&id_utilisateur=" + i.getUtilisateur().getIdUtilisateur()
                                    + "&sens=" + i.getSens().trim()
                                    + "&etat=" + i.getEtat().trim()
                                    + "&date=" + i.getDate().trim());
            dis = hc.openDataInputStream();
            dis.close();
            hc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void modifierInvitation(Invitations i) {
        try {

            hc = (HttpConnection) Connector.
                        open(url + "modifierInvitation"
                                    + "&id=" + i.getIdInvitation()
                                    + "&id_organisation=" + i.getOrganisation().getIdOrganisation()
                                    + "&id_utilisateur=" + i.getUtilisateur().getIdUtilisateur()
                                    + "&sens=" + i.getSens().trim()
                                    + "&etat=" + i.getEtat().trim()
                                    + "&date=" + i.getDate().trim());
            dis = hc.openDataInputStream();

            dis.close();
            hc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public Invitations[] findByOrganisation(Organisation o) {
        try {

            InvitationHandler invitationHandler = new InvitationHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            hc = (HttpConnection) Connector.open(url + "findByOrganisation&id=" + o.getIdOrganisation());//people.xml est un exemple
            dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, invitationHandler);
            // display the result
            invitationTab = invitationHandler.getInvitation();
            dis.close();
            hc.close();
            return invitationTab;
        } catch (ParserConfigurationException ex) {
            return null;
        } catch (SAXException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }
}
