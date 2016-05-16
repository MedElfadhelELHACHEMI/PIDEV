package tessearctntest;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Noor
 */
public class CourseHandler extends DefaultHandler {

    Cours course;
    Vector courses;

    boolean v1 = false;
    boolean v2 = false;
    boolean v3 = false;
    boolean v4 = false;
    boolean v5 = false;
    boolean v6 = false;
    boolean v7 = false;

    public CourseHandler() {
        courses = new Vector();
    }

    public Cours[] getCourses() {

        Cours[] coursesAux = new Cours[courses.size()];
        courses.copyInto(coursesAux);
        return coursesAux;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("Course")) {

            course = new Cours();
        }
        if (qName.equals("Name")) {
            v1 = true;
        }
        if (qName.equals("Category")) {
            v2 = true;
        }
        if (qName.equals("CategoryId")) {
            v3 = true;
        }
        if (qName.equals("Description")) {
            v4 = true;
        }
        if (qName.equals("Affiche")) {
            v5 = true;
        }

        if (qName.equals("Summary")) {
            v6 = true;
        }
        if (qName.equals("id")) {
            v7 = true;
        }

    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (v1) {
            course.setNomCours(new String(ch, start, length));
            v1 = false;
        }
        if (v2) {
            course.setNomMatiere(new String(ch, start, length));
            v2 = false;
        }
        if (v3) {
            course.setIdMatiere(Integer.parseInt(new String(ch, start, length)));
            v3 = false;
        }

        if (v4) {
            course.setDescriptionCours(new String(ch, start, length));
            v4 = false;
        }

        if (v5) {
            course.setAffiche(new String(ch, start, length));
            v5 = false;
        }
        if (v6) {
            course.setVideo(new String(ch, start, length));
            v6 = false;
        }
        if (v7) {
            course.setIdCours(Integer.parseInt(new String(ch, start, length)));
            v7 = false;
        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Course")) {
            courses.addElement(course);
        }

    }

}
