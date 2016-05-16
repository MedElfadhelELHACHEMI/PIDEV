/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handler;

import com.entities.Matiere;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Sameh
 */
public class MatiereHandler extends DefaultHandler {
   private Vector Matiers;  

public MatiereHandler() {
       Matiers = new Vector();
      
}

 public Matiere[] getMatiere() {
        Matiere[] Matierss = new Matiere[Matiers.size()];
      Matiers.copyInto(Matierss);
        return Matierss;
    }

 private Matiere currentMatiere;
public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
if (qName.equals("matiere")) {
   currentMatiere=new Matiere();
currentMatiere.setIdMatiere(Integer.parseInt(attributes.getValue("id")));
currentMatiere.setNomMatiere(attributes.getValue("nom"));
currentMatiere.setDescriptionMatiere(attributes.getValue("description"));
}
}
 public void endElement(String uri, String localName, String qName) throws SAXException {
 if (qName.equals("matiere")) {
 Matiers.addElement(currentMatiere);
 currentMatiere=null;
 }
}
   public void characters(char[] ch, int start, int length) throws SAXException {}






}