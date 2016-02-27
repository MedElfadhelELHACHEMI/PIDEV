package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IFormateurDAO;
import com.models.daos.interfaces.ILogDAO;
import com.models.entities.Formateur;
import com.models.entities.Log;
import com.models.enums.Etat;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ImplLogDAOTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

  

    @Ignore
    @Test
    public void TEST_GET_ALL_LOGGER_SHOULD_RETURN_LIST_LOGGER() throws SQLException {

        ILogDAO iLogDAO = DAOFactory.getLogDAO();
        List list = iLogDAO.getLogbyuser(14);
        assertEquals(list.size(), 1);
    }

    @Ignore
    @Test
    public void TEST_GET_LOGGER_BY_LOGIN_SHOULD_RETURN_LOG() throws SQLException {

        ILogDAO iLogDAO = DAOFactory.getLogDAO();
        Log log = iLogDAO.getLogbyid(1);
        assertNotNull(log);
    }

    @Ignore
    @Test
    public void TEST_AJOUTER_LOG_SHOULD_RETURN_TRUE() throws SQLException {

        ILogDAO iLogDAO = DAOFactory.getLogDAO();
        Log log = new Log();
        log.setDateTache(new Date(123));
        log.setIdUtilisateur(14);
        log.setTache("helllo");
        boolean test = iLogDAO.ajouterLog(log);
        assertTrue(test);

    }

    @Ignore
    @Test
    public void TEST_SUPPRIMER_LOGGER_BY_LOGIN_SHOULD_RETURN_TRUE() throws SQLException {

     ILogDAO iLogDAO = DAOFactory.getLogDAO();
     boolean test = iLogDAO.supprimerLog(1);
        assertTrue(test);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

}
