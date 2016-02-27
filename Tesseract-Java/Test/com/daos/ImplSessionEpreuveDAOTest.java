/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.ISessionCoursDAO;
import com.models.daos.interfaces.ISessionEpreuveDAO;
import com.models.entities.SessionEpreuve;
import java.sql.Date;
import java.util.Collections;
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
public class ImplSessionEpreuveDAOTest {

    public ImplSessionEpreuveDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Ignore
    @Test
    public void TEST_INSERT_SESSIONEPREUVE_SHOULD_RETURN_TRUE() {
        SessionEpreuve sessionEpreuve = new SessionEpreuve();
        sessionEpreuve.setId_epreuve(2);
        sessionEpreuve.setId_utilisateur(14);
        sessionEpreuve.setDate_Session(new Date(1500));
        sessionEpreuve.setNbr_tentative(12);

        ISessionEpreuveDAO iSessionEPDAO = DAOFactory.getSessionEpreuveDAO();
        boolean test = iSessionEPDAO.ajouterSessionEpreuve(sessionEpreuve);

        assertTrue(test);
    }
       @Ignore
     @Test
    public void TEST_GET_ALL_SESSIONEPREUVE_BY_ID_SUCCESS() {
       

        ISessionEpreuveDAO iSessionEPDAO = DAOFactory.getSessionEpreuveDAO();
         List test = iSessionEPDAO.getSessoEpreuvebynote(0.15f);

         assertEquals(test.size(), 1);
    }
}
