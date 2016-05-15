/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dao;

import com.entities.Evenement;
import com.handler.EvenementHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Maynoo
 */
public class EvenementDao {

    Evenement[] evenementTab;
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/tesseractj2me/tesseract_php/EvenementDao.php?action=";

    public void ajouterEvenement(Evenement e) {
        try {

            hc = (HttpConnection) Connector.
                        open(url + "ajouterEvenement"
                                    + "&id=" + e.getIdEvenement()
                                    + "&id_organisation=" + e.getOrganisation().getIdOrganisation()
                                    + "&nom=" + e.getNom().trim()
                                    + "&description=" + e.getDescription().trim()
                                    + "&nbr_max=" + e.getNbr_max()
                                    + "&affiche=" + e.getAffiche().trim()
                                    + "&date=" + e.getDate().trim());
            dis = hc.openDataInputStream();
            dis.close();
            hc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void modifierEvenement(Evenement e) {
        try {

            hc = (HttpConnection) Connector.
                        open(url + "modifierEvenement"
                                    + "&id=" + e.getIdEvenement()
                                    + "&id_organisation=" + e.getOrganisation().getIdOrganisation()
                                    + "&nom=" + e.getNom().trim()
                                    + "&description=" + e.getDescription().trim()
                                    + "&nbr_max=" + e.getNbr_max()
                                    + "&affiche=" + e.getAffiche().trim()
                                    + "&date=" + e.getDate().trim());
            dis = hc.openDataInputStream();
            dis.close();
            hc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public Evenement findById(int id) {
        try {

            EvenementHandler evenementHandler = new EvenementHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            hc = (HttpConnection) Connector.open(url + "findById&id=" + id);//people.xml est un exemple
            dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, evenementHandler);
            // display the result
            evenementTab = evenementHandler.getEvenement();
            dis.close();
            hc.close();
            return evenementTab[0];
        } catch (ParserConfigurationException ex) {
            return null;
        } catch (SAXException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }
    public Evenement[] findByIdorg(int id) {
        try {

            EvenementHandler evenementHandler = new EvenementHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            hc = (HttpConnection) Connector.open(url + "findByIdOrg&id=" + id);//people.xml est un exemple
            dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, evenementHandler);
            // display the result
            evenementTab = evenementHandler.getEvenement();
            dis.close();
            hc.close();
            return evenementTab;
        } catch (ParserConfigurationException ex) {
            return null;
        } catch (SAXException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }
}
