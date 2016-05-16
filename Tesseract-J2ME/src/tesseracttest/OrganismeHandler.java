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
public class OrganismeHandler extends DefaultHandler {
    
    Organisation organisation;
    Vector orgs;
    boolean v1 = false;
    boolean v2 = false;
    boolean v3 = false;
    boolean v4 = false;
    boolean v5 = false;
    boolean v6 = false;
    
    public OrganismeHandler() {
        orgs = new Vector();
        
    }
    
    public Organisation[] getOrganisme() {
        Organisation[] org = new Organisation[orgs.size()];
      
        orgs.copyInto(org);
        return org;
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Organisation")) {
            orgs.addElement(organisation);
        }
        
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if (qName.equals("Organisation")) {
            
            organisation = new Organisation();
        }
        if (qName.equals("Id")) {
            v1 = true;
        }
        if (qName.equals("Name")) {
            v2 = true;
        }
        if (qName.equals("Address")) {
            v3 = true;
        }
        if (qName.equals("Mail")) {
            v4 = true;
        }
        if (qName.equals("Number")) {
            v5 = true;
        }
        if (qName.equals("Picture")) {
            v6 = true;
        }
    }
    
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (v1) {
            organisation.setIdOrganisation(Integer.parseInt(new String(ch, start, length)));
            
            v1 = false;
        }
        if (v2) {
            organisation.setNom(new String(ch, start, length));
            v2 = false;            
        }
        if (v3) {
            organisation.setAdresse(new String(ch, start, length));
            v3 = false;            
            
        }
        if (v4) {
            organisation.seteMail(new String(ch, start, length));
            v4 = false;            
        }
        if (v5) {
            organisation.setMatricule(new String(ch, start, length));
            v5 = false;            
        }
        if (v6) {
            organisation.setPhoto(new String(ch, start, length));
            v6 = false;            
        }
        
    }
    
}
