/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handler;

import com.Dao.OrganisationDao;
import com.Dao.UtilisateurDao;
import com.entities.Invitations;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Maynoo
 */
public class InvitationHandler extends DefaultHandler {

    private Vector invitationVector;

    public InvitationHandler() {
        invitationVector = new Vector();
    }

    public Invitations[] getInvitation() {
        Invitations[] invitationTab = new Invitations[invitationVector.size()];
        invitationVector.copyInto(invitationTab);
        return invitationTab;
    }
    private Invitations currentInvitation;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("invitation")) {

            int id = Integer.parseInt(attributes.getValue("id"));
            int id_organisation = Integer.parseInt(attributes.getValue("id_organisme"));
            int id_utilisateur = Integer.parseInt(attributes.getValue("id_utilisateur"));
            String sens = attributes.getValue("sens");
            String etat = attributes.getValue("etat");
            String date = attributes.getValue("date");

            OrganisationDao od = new OrganisationDao();
            UtilisateurDao ud = new UtilisateurDao();
            currentInvitation = new Invitations(id, sens, etat, date, od.findByiId(id_organisation), ud.findById(id_utilisateur));
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("invitation")) {
            invitationVector.addElement(currentInvitation);
            currentInvitation = null;
        }
    }

}
