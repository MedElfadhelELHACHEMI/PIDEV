package com.handler;

import com.entities.Organisation;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class OrganisationHandler extends DefaultHandler{

    private Vector organisationVector;

    public OrganisationHandler() {
        organisationVector = new Vector();
    }

    public Organisation[] getOrganisation() {
        Organisation[] organisationTab = new Organisation[organisationVector.size()];
        organisationVector.copyInto(organisationTab);
        return organisationTab;
    }
    private Organisation currentOrganisation;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("organisation")) {

            int id = Integer.parseInt(attributes.getValue("id"));
            String nom = attributes.getValue("nom");
            String adress = attributes.getValue("adress");
            String email = attributes.getValue("email");
            String matricule = attributes.getValue("matricule");
            int telephone = Integer.parseInt(attributes.getValue("telephone"));
            String photo = attributes.getValue("photo");
            
            currentOrganisation = new Organisation(id, telephone, nom, adress, email, matricule, photo);
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("organisation")) {
            organisationVector.addElement(currentOrganisation);
            currentOrganisation = null;
        }
    }

}
