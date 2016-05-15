/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handler;

import com.Dao.OrganisationDao;
import com.Dao.UtilisateurDao;
import com.entities.Challenge;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Maynoo
 */
public class ChallengeHandler extends DefaultHandler {

    private Vector challengeVector;

    public ChallengeHandler() {
        challengeVector = new Vector();
    }

    public Challenge[] getChallenge() {
        Challenge[] challengeTab = new Challenge[challengeVector.size()];
        challengeVector.copyInto(challengeTab);
        return challengeTab;
    }
    private Challenge currentChallenge;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("challenge")) {

            int id = Integer.parseInt(attributes.getValue("id"));
            int id_organisation = Integer.parseInt(attributes.getValue("id_organisation"));
            String nom = attributes.getValue("nom");
            String description = attributes.getValue("description");
            String theme = attributes.getValue("theme");
            String date = attributes.getValue("date");
            int id_utilisateur = Integer.parseInt(attributes.getValue("id_utilisateur"));
            int duree = Integer.parseInt(attributes.getValue("duree"));

            UtilisateurDao ud = new UtilisateurDao();
            OrganisationDao od = new OrganisationDao();
            currentChallenge = new Challenge(id, id, nom, theme, date, description, od.findByiId(id_organisation), ud.findById(id_utilisateur));
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("challenge")) {
            challengeVector.addElement(currentChallenge);
            currentChallenge = null;
        }
    }

}
