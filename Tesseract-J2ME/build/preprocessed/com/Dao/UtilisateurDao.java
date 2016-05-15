/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dao;

import com.entities.Utilisateur;
import com.entities.Organisation;
import com.entities.Utilisateur;
import com.handler.UtilisateurHandler;
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
public class UtilisateurDao {

    Utilisateur[] utilisateurTab;
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/tesseractj2me/tesseract_php/UtilisateurDao.php?action=";

    public Utilisateur findById(int id) {
        try {

            UtilisateurHandler utilisateurHandler = new UtilisateurHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            hc = (HttpConnection) Connector.open(url + "findById&id=" + id);//people.xml est un exemple
            dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, utilisateurHandler);
            // display the result
            utilisateurTab = utilisateurHandler.getUtilisateur();
            dis.close();
            hc.close();
            return utilisateurTab[0];
        } catch (ParserConfigurationException ex) {
            return null;
        } catch (SAXException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }
}
