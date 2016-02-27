/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IFormateurDAO;
import com.models.entities.Formateur;
import com.models.enums.Etat;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author haikal
 */
public class ImplFormateurDAOTest {

    public ImplFormateurDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Ignore
    @Test
    public void TEST_UPDATE_FORMATEUR_BY_ID_SHOULD_RETURN_TRUE() throws SQLException {

        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        boolean result = formateurDAO.modifierFormateur("formateur", new Formateur());
        assertTrue(result);
    }

    @Ignore
    @Test
    public void TEST_GET_ALL_FORMATEUR_SHOULD_RETURN_LIST_FORMATEUR() throws SQLException {

        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        List<Formateur> listFormateur = formateurDAO.getAllFormateurs();
        assertEquals(1, listFormateur.size());
    }

    @Ignore
    @Test
    public void TEST_SUPPRIMER_FORMATEUR_BY_LOGIN_SHOULD_RETURN_TRUE() throws SQLException {

        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        boolean resultat = formateurDAO.supprimerFormateurByLogin("formateur");
        assertTrue(resultat);
    }
    
    
    @Ignore
    @Test
    public void TEST_AJOUTER_FORMATEUR_SHOULD_RETURN_TRUE() throws SQLException {

        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        Formateur formateur = new Formateur(null, Etat.ACC, "ffff", "1f23", "1f23", "1f23", new Date(1000), 123, null, null, null);
        boolean test = formateurDAO.ajouterFormateur(formateur, 1);
        assertTrue(test);

    }

    @Ignore
    @Test
    public void TEST_GET_FORMATEUR_BY_LOGIN_SHOULD_RETURN_FORMATEUR() throws SQLException {

        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        Formateur formateur = formateurDAO.getFormateurByLogin("formateur");
        assertNotNull(formateur);
    }
     @Ignore
    @Test
    public void TEST_UPDATE_FORMATEUR_BY_LOGIN_IDORG_SHOULD_RETURN_TRUE() throws SQLException {

        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
  
        assertTrue(formateurDAO.affecterOrganismeFormateur(1, 1))
                ;}
        @Ignore
    @Test
    public void afficher_formateu_By_id() throws SQLException {

        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        assertNotNull(formateurDAO.getFormateurById(1));
        
        

    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

}
