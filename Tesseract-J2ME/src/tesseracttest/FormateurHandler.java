/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesseracttest;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author haikal
 */
public class FormateurHandler extends DefaultHandler {
    
    Formateur formateur;
    Vector formateurs;
    boolean v1 = false;
    boolean v2 = false;
    boolean v3 = false;
    boolean v4 = false;
    boolean v5 = false;
    private boolean v6 = false;
    private boolean v7 = false;
    
    public FormateurHandler() {
        formateurs = new Vector();
        
    }
    
    public Formateur[] getFormateurs() {
        Formateur[] f = new Formateur[formateurs.size()];
        formateurs.copyInto(f);
        return f;
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Utilisateur")) {
            formateurs.addElement(formateur);
        }
        
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if (qName.equals("Utilisateur")) {
            
            formateur = new Formateur();
        }
        if (qName.equals("Id")) {
            v1 = true;
        }
        if (qName.equals("IdOrganisation")) {
            v2 = true;
        }
        if (qName.equals("Name")) {
            v3 = true;
        }
        if (qName.equals("FamilyName")) {
            v4 = true;
        }
        if (qName.equals("Mail")) {
            v5 = true;
        }
        if (qName.equals("Score")) {
            v6 = true;
        }
        if (qName.equals("Photo")) {
            v7 = true;
        }
        
    }
    
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (v1) {
            formateur.setIdUtilisateur(Integer.parseInt(new String(ch, start, length)));
            
            v1 = false;
        }
        if (v2) {
            formateur.setIdOrgganisation(Integer.parseInt(new String(ch, start, length)));
            
            v2 = false;
        }
        if (v3) {
            formateur.setNom(new String(ch, start, length));
            
            v3 = false;
            
        }
        if (v4) {
            formateur.setPrenom(new String(ch, start, length));
            
            v4 = false;
        }
        if (v5) {
            formateur.setMail(new String(ch, start, length));
            
            v5 = false;
        }
        if (v6) {
            formateur.setScore(Integer.parseInt(new String(ch, start, length)));
            v6 = false;
        }
        if (v7) {
            formateur.setPhoto(new String(ch, start, length));
            
            v7 = false;
        }
        
    }
}
