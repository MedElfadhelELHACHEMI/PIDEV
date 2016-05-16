package tessearctntest;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Noor
 */
public class TestDetailsHandler extends DefaultHandler {

    Vector questions;
    Question currentQuestion;
    Answer currentAnwser;
    Answer[] answers;
    int i=0;

    public TestDetailsHandler() {
        questions = new Vector();
    }

    public Question[] getQuestions() {
        Question[] qTab = new Question[questions.size()];
        questions.copyInto(qTab);
        return qTab;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("Question")) {

            currentQuestion.setId(Integer.parseInt(attributes.getValue("Id")));
            currentQuestion.setTask(attributes.getValue("Task"));

        } else if (qName.equals("Answer")) {
            
            currentAnwser = new Answer();
            currentAnwser.setIdQuestion(currentQuestion.getId());
            currentAnwser.setEtat(attributes.getValue("Etat"));
            currentAnwser.setJustif(attributes.getValue("Justification"));
            currentAnwser.setResponse(attributes.getValue("Response"));
            currentAnwser.setId(Integer.parseInt(attributes.getValue("IdResponse")));

        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Question")) {

            questions.addElement(currentQuestion);

              currentQuestion = null;
        } else if (qName.equals("Answer")) {

            
            answers[i]=currentAnwser;
            i++;
            currentAnwser = null;
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {

        if (currentAnwser != null) {

            System.out.println("nth");
        }
    }

}
