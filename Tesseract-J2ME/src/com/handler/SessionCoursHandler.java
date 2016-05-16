/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handler;


import com.entities.SessionCours;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Sameh
 */
public class SessionCoursHandler extends DefaultHandler{
private Vector sessions;    
 public SessionCoursHandler() {
       sessions = new Vector();
      
}
  public SessionCours [] getSessionCours() {
        SessionCours[] sessionss = new SessionCours[sessions.size()];
      sessions.copyInto(sessionss);
        return sessionss;
    }
  private SessionCours currentSessionCours;
   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equals("session")) {  
      currentSessionCours=new SessionCours(); 
       currentSessionCours.setIdSession(Integer.parseInt(attributes.getValue("id")));
       currentSessionCours.setId_utilisateur(Integer.parseInt(attributes.getValue("id_utilisateur")));
        currentSessionCours.setId_cours(Integer.parseInt(attributes.getValue("id_cour")));
        currentSessionCours.setNbrchapitre(Integer.parseInt(attributes.getValue("nbr")));
        currentSessionCours.setBadge(attributes.getValue("badge"));
    } 
   }
 public void endElement(String uri, String localName, String qName) throws SAXException {
  if (qName.equals("session")) { 
   sessions.addElement(currentSessionCours);
            currentSessionCours = null;    
  }   
 } 
  public void characters(char[] ch, int start, int length) throws SAXException {}
}
