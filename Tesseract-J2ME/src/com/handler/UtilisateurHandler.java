/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handler;

import com.entities.Apprenant;
import com.entities.Utilisateur;
import java.util.Date;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Sameh
 */
public class UtilisateurHandler extends DefaultHandler {

    private Vector Utilisateurs;

    public UtilisateurHandler() {
        Utilisateurs = new Vector();

    }

    public Utilisateur[] getUtilisateur() {
        Utilisateur[] Utilisateurss = new Utilisateur[Utilisateurs.size()];
        Utilisateurs.copyInto(Utilisateurss);
        return Utilisateurss;
    }
    private Utilisateur currentUtilisateur;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("utilisateur")) {
		    // create new Person object

            // "attributes" holds name and value pairs from inside the tag
            currentUtilisateur = new Apprenant(Integer.parseInt(attributes.getValue("score")));
            currentUtilisateur.setIdUtilisateur(Integer.parseInt(attributes.getValue("id")));
            currentUtilisateur.setNomUtilisateur(attributes.getValue("pseudo"));
            currentUtilisateur.setMotDePass(attributes.getValue("mdp"));
            currentUtilisateur.setNom(attributes.getValue("nom"));
            currentUtilisateur.setPrenom(attributes.getValue("prenom"));
            currentUtilisateur.setMail(attributes.getValue("mail"));
            currentUtilisateur.setDateNaissance(attributes.getValue("date_naissance"));
            currentUtilisateur.setTel(Integer.parseInt(attributes.getValue("telephone")));
            currentUtilisateur.setAdresse(attributes.getValue("adresse"));
            currentUtilisateur.setPhoto(attributes.getValue("photo"));
            currentUtilisateur.setSexe(attributes.getValue("sexe"));
            currentUtilisateur.setRole(attributes.getValue("roles"));
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("utilisateur")) {
            // we are no longer processing a <reg.../> tag
            Utilisateurs.addElement(currentUtilisateur);
            currentUtilisateur = null;

        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
    }

}
