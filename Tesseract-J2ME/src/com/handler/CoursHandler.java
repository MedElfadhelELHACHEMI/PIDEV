/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handler;

import com.entities.Cours;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Sameh
 */
public class CoursHandler extends DefaultHandler {
 private Vector Courss;
 public CoursHandler() {
       Courss = new Vector();
      
}
 public Cours [] getCours() {
        Cours[] Coursss = new Cours[Courss.size()];
      Courss.copyInto(Coursss);
        return Coursss;
    }
  private Cours currentCours;
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
  if (qName.equals("cours")) {
      currentCours=new Cours();
    currentCours.setIdCours(Integer.parseInt(attributes.getValue("id")));
  currentCours.setNomCours(attributes.getValue("nom"));
  currentCours.setIdMatiere(Integer.parseInt(attributes.getValue("id_matiere")));
  currentCours.setIdFormateur(Integer.parseInt(attributes.getValue("id_utilisateur")));
  currentCours.setDifficulte(attributes.getValue("difficulte"));
  currentCours.setDescriptionCours(attributes.getValue("description"));
  currentCours.setAffiche(attributes.getValue("affiche"));
  currentCours.setVideo(attributes.getValue("video"));
  }
  
  }
  public void endElement(String uri, String localName, String qName) throws SAXException {
  
  if (qName.equals("cours")) {
    Courss.addElement(currentCours);
            currentCours = null;   
  }
  
  }
    public void characters(char[] ch, int start, int length) throws SAXException {}
}
