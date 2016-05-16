package tessearctntest;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Noor
 */
public class TestHandler extends DefaultHandler{

    Test test;
    Vector tests;

    boolean v1 = false;
    boolean v2 = false;
    boolean v3 = false;
    boolean v4 = false;
    boolean v5 = false;

    public TestHandler() {
        tests=new Vector();
        
    }
    
     public Test[] getTests() {

        Test[] aux = new Test[tests.size()];
        tests.copyInto(aux);
        return aux;
    }
     
      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("Epreuve")) {

           test=new Test();
        }
        if (qName.equals("Id")) {
            v1 = true;
        }
        if (qName.equals("IdChapter")) {
            v2 = true;
        }
        if (qName.equals("Type")) {
            v3 = true;
        }
        if (qName.equals("Difficulte")) {
            v4 = true;
        }
        if (qName.equals("Duree")) {
            v5 = true;
        }

    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (v1) {
           test.setId(Integer.parseInt(new String(ch, start, length)));
            v1 = false;
        }
        if (v2) {
            test.setIdChapitre(Integer.parseInt(new String(ch, start, length)));
            v2 = false;
        }
        if (v3) {
            test.setType(new String(ch, start, length));
            v3 = false;
        }

        if (v4) {
             test.setDifficulte(new String(ch, start, length));
            v4 = false;
        }

        if (v5) {
            test.setDuration(Integer.parseInt(new String(ch, start, length)));
            v5 = false;
        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Epreuve")) {
            tests.addElement(test);
        }

}
}