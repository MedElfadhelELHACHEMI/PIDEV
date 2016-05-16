/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesseracttest;

import java.util.Date;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author haikal
 */
public class InvitationHandler extends DefaultHandler{
     Invitation invitation;
    Vector invitations;
    boolean v1 = false;
    boolean v2 = false;
    boolean v3 = false;
    boolean v4 = false;
  
    
    public InvitationHandler() {
        invitations = new Vector();
        
    }
    
    public Invitation[] getInvitations() {
        Invitation[] invt = new Invitation[invitations.size()];
     
        invitations.copyInto(invt);
        return invt;
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Invitation")) {
            invitations.addElement(invitation);
        }
        
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if (qName.equals("Invitation")) {
            
            invitation = new Invitation();
        }
        if (qName.equals("Id")) {
            v1 = true;
        }
        if (qName.equals("IdOrganisation")) {
            v2 = true;
        }
        if (qName.equals("IdUtilisateur")) {
            v3 = true;
        }
        if (qName.equals("Date")) {
            v4 = true;
        }
      
    }
    
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (v1) {
            invitation.setIdInvitation(Integer.parseInt(new String(ch, start, length)));
            
            v1 = false;
        }
        if (v2) {
          invitation.setIdOrganisation(Integer.parseInt(new String(ch, start, length)));
           
            v2 = false;            
        }
        if (v3) {
            invitation.setIdUtilisateur(Integer.parseInt(new String(ch, start, length)));
           
            v3 = false;            
            
        }
        if (v4) {
          //invitation.setDateInvitation();
           
            v4 = false;            
        }
      
        
    }
    
}
