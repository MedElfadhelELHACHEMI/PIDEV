/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IReclamationDAO;
import com.models.entities.Reclamation;
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
public class ImplReclamationDAOTest {

    public ImplReclamationDAOTest() {
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
    public void TEST_AJOUT_RECLAMATION_SHOULD_RETRUN_TRUE() throws SQLException {

        IReclamationDAO iReclamationDAO = DAOFactory.getReclamationDAO();
        boolean result = iReclamationDAO.ajouterReclamation(new Reclamation(), 14);
        assertTrue(result);
    }

    @Ignore
    @Test
    public void TEST_GET_ALL_RECLAMATION_BY_ID_USER_SHOULD_RETRUN_LIST() throws SQLException {

        IReclamationDAO iReclamationDAO = DAOFactory.getReclamationDAO();
        List result = iReclamationDAO.getReclamationsByIdUser(14);
        assertEquals(result.size(), 1);
    }

    @Ignore
    @Test
    public void TEST_MODIFIER_RECLAMATION_BY_ID_RECLAMATION_SHOULD_RETRUN_LIST() throws SQLException {

        IReclamationDAO iReclamationDAO = DAOFactory.getReclamationDAO();

        boolean result = iReclamationDAO.modifierReclamation(1, new Reclamation(14, "oop", "sujett", Etat.ACC, new Date(100)));
        assertTrue(result);
    }

}
