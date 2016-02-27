package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.ICoursDAO;
import com.models.daos.interfaces.IMatiereDAO;

import com.models.entities.Cours;
import com.models.entities.Matiere;
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
 * @author Sameh
 */
public class ImplCoursDAOTest {

    public ImplCoursDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @Ignore
    @Test
    public void TEST_CREATE_COURS_SHOULD_RETURN_TRUE_IF_SUCCESS() throws SQLException, Exception {
        Matiere matiere = new Matiere(1, "haikel", "yassmine");
        ICoursDAO coursDao = DAOFactory.getCoursDAO();
        Cours cours = new Cours("java", "tuto", 25, 1, "/..", "certView");
        boolean test = coursDao.AjouterCours(cours, matiere);
        assertTrue(test);

    }

    @Ignore
    @Test
    public void TEST_GET_COURS_BY_ID_SHOULD_RETURN_COURS() throws SQLException, Exception {
        Matiere matiere = new Matiere(1, "haikel", "yassmine");
        ICoursDAO coursDao = DAOFactory.getCoursDAO();
        Cours cours = new Cours("java", "tuto", 25, 1, "/..", "certView");
        Cours test = coursDao.findCoursById(1);
        assertNotNull(test);

    }

    @Ignore
    @Test
    public void TEST_GET_COURS_BY_NAME_SHOULD_RETURN_LIST() throws SQLException, Exception {

        ICoursDAO coursDao = DAOFactory.getCoursDAO();

        List test = coursDao.findCoursByNomCours("java");
        assertNotNull(test);

    }

    @Ignore
    @Test
    public void TEST_GET_COURS_BY_IDFORMATEUR_SHOULD_RETURN_LIST() throws SQLException, Exception {

        ICoursDAO coursDao = DAOFactory.getCoursDAO();

        List test = coursDao.findCoursByIdFromateur(1);
        assertNotNull(test);

    }

    @Ignore
    @Test
    public void TEST_GET_COURS_BY_VAL1_SHOULD_RETURN_LIST() throws SQLException, Exception {

        ICoursDAO coursDao = DAOFactory.getCoursDAO();

        List test = coursDao.getCoursValid1EnAttente();
        assertEquals(3, test.size());

    }

    @Ignore
    @Test
    public void TEST_GET_COURS_BY_VAL2_SHOULD_RETURN_LIST() throws SQLException, Exception {

        ICoursDAO coursDao = DAOFactory.getCoursDAO();

        List test = coursDao.getCoursValid2EnAttente();
        assertEquals(0, test.size());

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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
