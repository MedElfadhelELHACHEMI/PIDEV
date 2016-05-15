/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dao;

import com.entities.Challenge;
import com.handler.ChallengeHandler;
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
public class ChallengeDao {

    Challenge[] challengeTab;
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/tesseractj2me/tesseract_php/ChallengeDao.php?action=";

    public void ajouterChallenge(Challenge c) {
        try {

            hc = (HttpConnection) Connector.
                    open(url + "ajouterChallenge"
                            + "&id=" + c.getIdChallenge()
                            + "&id_organisation=" + c.getOrganisation().getIdOrganisation()
                            + "&nom=" + c.getNom().trim()
                            + "&description=" + c.getDescription().trim()
                            + "&theme=" + c.getTheme().trim()
                            + "&date=" + c.getDate().trim()
                            + "&id_utilisateur=" + c.getUtilisateur().getIdUtilisateur()
                            + "&duree=" + c.getDuree());
            dis = hc.openDataInputStream();
            dis.close();
            hc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void modifierChallenge(Challenge c) {
        try {

            hc = (HttpConnection) Connector.
                    open(url + "modifierChallenge"
                            + "&id=" + c.getIdChallenge()
                            + "&id_organisation=" + c.getOrganisation().getIdOrganisation()
                            + "&nom=" + c.getNom().trim()
                            + "&description=" + c.getDescription().trim()
                            + "&theme=" + c.getTheme().trim()
                            + "&date=" + c.getDate().trim()
                            + "&id_utilisateur=" + c.getUtilisateur().getIdUtilisateur()
                            + "&duree=" + c.getDuree());
            dis = hc.openDataInputStream();
            dis.close();
            hc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public Challenge findByiId(int id) {
        try {

            ChallengeHandler challengeHandler = new ChallengeHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            hc = (HttpConnection) Connector.open(url + "findById&id=" + id);//people.xml est un exemple
            dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, challengeHandler);
            // display the result
            challengeTab = challengeHandler.getChallenge();
            dis.close();
            hc.close();
            return challengeTab[0];
        } catch (ParserConfigurationException ex) {
            return null;
        } catch (SAXException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }

    public Challenge[] findByiIdOrg(int id) {
        try {

            ChallengeHandler challengeHandler = new ChallengeHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            hc = (HttpConnection) Connector.open(url + "findByIdorg&id=" + id);//people.xml est un exemple
            dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, challengeHandler);
            // display the result
            challengeTab = challengeHandler.getChallenge();
            dis.close();
            hc.close();
            return challengeTab;
        } catch (ParserConfigurationException ex) {
            return null;
        } catch (SAXException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }
}
