
package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IApprenantDAO;
import com.models.entities.Apprenant;
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


public class ImplApprenantDAOTest {

    public ImplApprenantDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("start test");

    }

    @Ignore
    @Test
    public void TEST_UPDATE_APPRENANT_BY_ID_SHOULD_RETURN_TRUE() throws SQLException {

        IApprenantDAO apprenantDAO = DAOFactory.getApprenantDAO();
        boolean result = apprenantDAO.modifierApprenant("haikalinfo", new Apprenant());
        assertTrue(result);
    }

    @Ignore
    @Test
    public void TEST_GET_ALL_APPRENANT_SHOULD_RETURN_LIST_APPRENANT() throws SQLException {

        IApprenantDAO apprenantDAO = DAOFactory.getApprenantDAO();
        List<Apprenant> listApprenant = apprenantDAO.getAllApprenants();
        assertEquals(1, listApprenant.size());
    }

    @Ignore
    @Test
    public void TEST_SUPPRIMER_APPRENANT_BY_LOGIN_SHOULD_RETURN_TRUE() throws SQLException {

        IApprenantDAO apprenantDAO = DAOFactory.getApprenantDAO();
        boolean resultat = apprenantDAO.supprimerApprennantByLogin("haikalinfo");
        assertTrue(resultat);
    }

    @Ignore
    @Test
    public void TEST_AJOUTER_APPRENANT_SHOULD_RETURN_TRUE() throws SQLException {

        IApprenantDAO apprenantDAO = DAOFactory.getApprenantDAO();
        Apprenant apprenant = new Apprenant(25, Etat.ACC, "haikalinfo", "kakashi", "haikal", "magrahi", new Date(100), 21998090, "...", "444", "555");
        boolean test = apprenantDAO.ajouterApprenant(apprenant);
        assertTrue(test);

    }

    @Ignore
    @Test
    public void TEST_GET_APPRENANT_BY_LOGIN_SHOULD_RETURN_APPRENANT() throws SQLException {

        IApprenantDAO apprenantDAO = DAOFactory.getApprenantDAO();
        Apprenant apprenant = apprenantDAO.getApprenantByLogin("haikalinfo");
        assertNotNull(apprenant);
    }
     @Ignore
     @Test
    public void TEST_GET_APPRENANT_BY_ID_SHOULD_RETURN_TRUE() throws SQLException {

        IApprenantDAO apprenantDAO = DAOFactory.getApprenantDAO();
        Apprenant result = apprenantDAO.getApprenantsById(1);
         assertNotNull(result);
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

}
