package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IEvenementDAO;
import com.models.entities.Evenement;
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

public class ImplEvenementDAOTest {

    public ImplEvenementDAOTest() {
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
    public void TEST_CREATE_EVENTS_SHOULD_RETURN_TRUE() throws SQLException {
        IEvenementDAO evenementDAO = DAOFactory.getEvenementDAO();
        boolean test = evenementDAO.addEvenement(new Evenement(1, 1, "test", "desc", 5, "/home", new Date(151)));
        assertTrue(test);

    }

    @Ignore
    @Test
    public void TEST_DISPLAY_EVENTS_SHOULD_RETURN_LIST() throws SQLException {
        IEvenementDAO evenementDAO = DAOFactory.getEvenementDAO();
        List test = evenementDAO.displayEvenement();
        assertEquals(test.size(), 1);

    }
    @Ignore
    @Test
    public void TEST_DISPLAY_EVENTS_BY_ID_SHOULD_RETURN_LIST() throws SQLException {
        IEvenementDAO evenementDAO = DAOFactory.getEvenementDAO();
        List test = evenementDAO.displayByIdOrganisation(1);
        assertEquals(test.size(), 1);

    }
     @Ignore
     @Test
    public void TEST_DELETE_EVENTS_SHOULD_RETURN_TRUE() throws SQLException {
        IEvenementDAO evenementDAO = DAOFactory.getEvenementDAO();
        boolean test = evenementDAO.deleteEvenement(1);
         assertTrue(test);
    }
}
