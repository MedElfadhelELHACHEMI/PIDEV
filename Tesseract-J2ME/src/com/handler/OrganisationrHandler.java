/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handler;

import com.entities.Organisation;
import com.entities.Utilisateur;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Sameh
 */
public class OrganisationrHandler extends DefaultHandler{
 private Vector Organisations;
 
    public OrganisationrHandler() {
      Organisations = new Vector();
      
    }
    
     public Organisation [] getOrganisation() {
        Organisation[] Organisationss = new Organisation[Organisations.size()];
       Organisations.copyInto(Organisationss);
        return Organisationss;
    }
     private Organisation currentOrganisation;
     
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
if (qName.equals("organisation")) {

 currentOrganisation=new Organisation();
currentOrganisation.setIdOrganisation(Integer.parseInt(attributes.getValue("id")));
currentOrganisation.setNom(attributes.getValue("nom"));
currentOrganisation.setAdress(attributes.getValue("adress"));
currentOrganisation.setEmail(attributes.getValue("email"));
currentOrganisation.setMatricule(attributes.getValue("matricule"));
currentOrganisation.setTelephone(Integer.parseInt(attributes.getValue("telephone")));
}

    }
    
  public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("organisation")) {
            // we are no longer processing a <reg.../> tag
             Organisations.addElement(currentOrganisation);
            currentOrganisation = null;
        

}
}
  public void characters(char[] ch, int start, int length) throws SAXException {}   
}
