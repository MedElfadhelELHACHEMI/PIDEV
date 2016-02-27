package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.ISessionCoursDAO;
import com.models.daos.interfaces.implementations.ImplSessionCoursDAO;
import com.models.entities.SessionCours;
import java.sql.Date;
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
public class ImplSessionCoursDAOTest {

    public ImplSessionCoursDAOTest() {
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
    public void TEST_AJOUT_SESSIONCOURS_SHOULD_RETURN_TRUE() {
        SessionCours cours = new SessionCours();
        cours.setId_cours(1);
        cours.setId_utilisateur(14);
        cours.setDate_session(new Date(122));

        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        boolean test = iSessionCoursDAO.ajouterSessionCours(cours);
        assertTrue(test);

    }

    @Ignore
    @Test
    public void TEST_GET_SESSIONCOURS_BY_ID_SHOULD_RETURN_TRUE() {

        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        List test = iSessionCoursDAO.getSessionCoursbyCoursid(1);
        assertEquals(test.size(), 1);

    }

}
