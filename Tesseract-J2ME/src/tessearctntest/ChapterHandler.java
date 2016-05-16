
package tessearctntest;


import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Noor
 */
public class ChapterHandler extends DefaultHandler{
    Chapitre chapter;
    Vector chapters;

    boolean v1 = false;
    boolean v2 = false;
    boolean v3 = false;
    boolean v4 = false;
    boolean v5 = false;
    

    public ChapterHandler() {
        chapters=new Vector();
    }
    
     public Chapitre[] getChapters() {

        Chapitre[] aux = new Chapitre[chapters.size()];
        chapters.copyInto(aux);
        return aux;
    }
     
      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("Chapter")) {

            chapter = new Chapitre();
        }
        if (qName.equals("Id")) {
            v1 = true;
        }
        if (qName.equals("Description")) {
            v2 = true;
        }
        if (qName.equals("Nom")) {
            v3 = true;
        }
        if (qName.equals("Resume")) {
            v4 = true;
        }
        if (qName.equals("Numero")) {
            v5 = true;
        }

    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (v1) {
            chapter.setIdChapitre(Integer.parseInt(new String(ch, start, length)));
            v1 = false;
        }
        if (v2) {
            chapter.setDescription(new String(ch, start, length));
            v2 = false;
        }
        if (v3) {
            chapter.setNom(new String(ch, start, length));
            v3 = false;
        }

        if (v4) {
            chapter.setResume(new String(ch, start, length));
            v4 = false;
        }

        if (v5) {
            chapter.setNumero(Integer.parseInt(new String(ch, start, length)));
            v5 = false;
        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Chapter")) {
            chapters.addElement(chapter);
        }

    }


    
    
    
}
