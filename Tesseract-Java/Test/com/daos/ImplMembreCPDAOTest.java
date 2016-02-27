/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IMembreCPDAO;

import com.models.entities.MembreCP;

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
public class ImplMembreCPDAOTest {

    public ImplMembreCPDAOTest() {
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
    public void TEST_UPDATE_MEMBRECP_BY_ID_SHOULD_RETURN_TRUE() throws SQLException {

        IMembreCPDAO iMembreCPDAO = DAOFactory.getMembreCPDAO();
        boolean result = iMembreCPDAO.modifierMembreCP("test", new MembreCP());
        assertTrue(result);
    }

    @Ignore
    @Test
    public void TEST_GET_ALL_MEMBRECP_SHOULD_RETURN_LIST_MEMBRECP() throws SQLException {

        IMembreCPDAO membreCPDAO = DAOFactory.getMembreCPDAO();
        List<MembreCP> listmembreCPDAO = membreCPDAO.getAllMembreCPs();
        assertEquals(1, listmembreCPDAO.size());
    }

    @Ignore
    @Test
    public void TEST_SUPPRIMER_MEMBRECP_BY_LOGIN_SHOULD_RETURN_TRUE() throws SQLException {

        IMembreCPDAO membreCPDAO = DAOFactory.getMembreCPDAO();
        boolean resultat = membreCPDAO.supprimerMembreCPByLogin("membrecp");
        assertTrue(resultat);
    }

    @Ignore
    @Test
    public void TEST_AJOUTER_MEMBRECP_SHOULD_RETURN_TRUE() throws SQLException {

        IMembreCPDAO membreCPDAO = DAOFactory.getMembreCPDAO();
        // MembreCP membreCP = new MembreCP("test", "test", "test", "test", new Date(1000), 123, "test", "test", "test");
        MembreCP membreCP = new MembreCP();
        boolean test = membreCPDAO.ajouterMembreCP(membreCP);
        assertTrue(test);

    }

    @Ignore
    @Test
    public void TEST_GET_MEMBRECP_BY_LOGIN_SHOULD_RETURN_MEMBRECP() throws SQLException {

        IMembreCPDAO membreCPDAO = DAOFactory.getMembreCPDAO();
        MembreCP membreCP = membreCPDAO.getMembreCPByLogin("test");
        assertNotNull(membreCP);
    }

}
