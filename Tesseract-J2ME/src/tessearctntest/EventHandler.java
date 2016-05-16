
package tessearctntest;

import java.util.Date;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Noor
 */
class EventHandler extends DefaultHandler {

    Evenement event;
    Vector events;
    boolean v1 = false;
    boolean v2 = false;
    boolean v3 = false;
    boolean v4 = false;
    boolean v5 = false;
    boolean v6 = false;
    boolean v7 = false;

    public EventHandler() {
        events = new Vector();
    }

    public Evenement[] getEvents() {
        Evenement[] eventsAux = new Evenement[events.size()];
        events.copyInto(eventsAux);
        return eventsAux;

    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("Event")) {

            event = new Evenement();
        }
        if (qName.equals("Id")) {
            v1 = true;
        }
        if (qName.equals("IdOrganisation")) {
            v2 = true;
        }
        if (qName.equals("EventName")) {
            v3 = true;
        }
        if (qName.equals("Description")) {
            v4 = true;
        }
        if (qName.equals("MaxNumber")) {
            v5 = true;
        }

        if (qName.equals("Affiche")) {
            v6 = true;
        }

        if (qName.equals("Date")) {
            v7 = true;
        }

    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (v1) {
            event.setIdEvenement(Integer.parseInt(new String(ch, start, length)));
            v1 = false;
        }
        if (v2) {
            event.setIdOrganisation(Integer.parseInt(new String(ch, start, length)));
            v2 = false;
        }
        if (v3) {
            event.setNom(new String(ch, start, length));
            v3 = false;

        }
        if (v4) {
            event.setDescription(new String(ch, start, length));
            v4 = false;
        }
        if (v5) {
            event.setNbrMax(Integer.parseInt(new String(ch, start, length)));
            v5 = false;
        }
        if (v6) {
            event.setAffiche(new String(ch, start, length));
            v6 = false;
        }
        if (v7) {
            event.setDateEvenement(null);
            v7 = false;
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Event")) {
            events.addElement(event);
        }

    }

}
