package com.handler;

import com.Dao.OrganisationDao;
import com.entities.Evenement;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EvenementHandler extends DefaultHandler {

    private Vector evenementVector;

    public EvenementHandler() {
        evenementVector = new Vector();
    }

    public Evenement[] getEvenement() {
        Evenement[] evenementTab = new Evenement[evenementVector.size()];
        evenementVector.copyInto(evenementTab);
        return evenementTab;
    }
    private Evenement currentEvenement;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("evenement")) {

            int id = Integer.parseInt(attributes.getValue("id"));
            int id_organisation = Integer.parseInt(attributes.getValue("id_organisation"));
            String nom = attributes.getValue("nom");
            String description = attributes.getValue("description");
            int nbr_max = Integer.parseInt(attributes.getValue("nbr_max"));
            String affiche = attributes.getValue("affiche");
            String date = attributes.getValue("date");

            OrganisationDao od = new OrganisationDao();
            currentEvenement = new Evenement(id, nbr_max, nom, description, affiche, date, od.findByiId(id_organisation));
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("evenement")) {
            evenementVector.addElement(currentEvenement);
            currentEvenement = null;
        }
    }

}
