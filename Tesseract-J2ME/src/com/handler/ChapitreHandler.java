/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handler;

import com.entities.Chapitre;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Sameh
 */
public class ChapitreHandler extends DefaultHandler {
  private Vector Chapitres;  
  public ChapitreHandler() {
       Chapitres = new Vector();
      
    } 

public Chapitre[] getChapitre() {
        Chapitre[] Chapitress = new Chapitre[Chapitres.size()];
       Chapitres.copyInto(Chapitress);
        return Chapitress;
    }

private Chapitre currentChapitre;
public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
if (qName.equals("chapitre")) {
    currentChapitre=new Chapitre();
 currentChapitre.setIdChapitre(Integer.parseInt(attributes.getValue("id")));
 currentChapitre.setIdCours(Integer.parseInt(attributes.getValue("id_cours")));
 currentChapitre.setNom(attributes.getValue("nom"));
 currentChapitre.setNumero(Integer.parseInt(attributes.getValue("numero")));
 currentChapitre.setDescription(attributes.getValue("description"));
 currentChapitre.setResume(attributes.getValue("resume"));
 
}    
}
 public void endElement(String uri, String localName, String qName) throws SAXException {
 if (qName.equals("chapitre")) {
  Chapitres.addElement(currentChapitre);
            currentChapitre = null;   
 }    
 }

 public void characters(char[] ch, int start, int length) throws SAXException {}


}
