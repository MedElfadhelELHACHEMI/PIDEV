/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handler;

import com.Dao.OrganisationDao;
import com.Dao.UtilisateurDao;
import com.entities.Utilisateur;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Maynoo
 */
public class UtilisateurHandler extends DefaultHandler {

    private Vector utilisateurVector;

    public UtilisateurHandler() {
        utilisateurVector = new Vector();
    }

    public Utilisateur[] getUtilisateur() {
        Utilisateur[] utilisateurTab = new Utilisateur[utilisateurVector.size()];
        utilisateurVector.copyInto(utilisateurTab);
        return utilisateurTab;
    }
    private Utilisateur currentUtilisateur;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("utilisateur")) {

            int id = Integer.parseInt(attributes.getValue("id"));
            int id_organisation = Integer.parseInt(attributes.getValue("id_organisation"));
            String pseudo = attributes.getValue("pseudo");
            String mdp = attributes.getValue("mdp");
            String sexe = attributes.getValue("sexe");
            String nom = attributes.getValue("nom");
            String prenom = attributes.getValue("prenom");
            String date_naissance = attributes.getValue("date_naissance");
            int telephone = Integer.parseInt(attributes.getValue("telephone"));
            String adresse = attributes.getValue("adresse");
            String mail = attributes.getValue("mail");
            String photo = attributes.getValue("photo");
            String role = attributes.getValue("role");
            int score = Integer.parseInt(attributes.getValue("score"));
            OrganisationDao od = new OrganisationDao();
            
            currentUtilisateur = new Utilisateur(id, od.findByiId(id_organisation), pseudo, mdp, nom, prenom, date_naissance, telephone, adresse, mail, photo, role, sexe);
            currentUtilisateur.setScore(score);
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("utilisateur")) {
            utilisateurVector.addElement(currentUtilisateur);
            currentUtilisateur = null;
        }
    }

}
