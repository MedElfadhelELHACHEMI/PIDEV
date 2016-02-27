package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IChallengeDAO;
import com.models.entities.Challenge;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class ImplChallengeDAOTest {

    public ImplChallengeDAOTest() {
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
    public void TEST_ADD_CHALLENGE_SHOULD_RETURN_TRUE() {
        Challenge c = new Challenge();
        c.setIdOrganisation(1);
        IChallengeDAO iChallengeDAO = DAOFactory.getChallengeDAO();
        boolean test = iChallengeDAO.addChallenge(c);
        assertTrue(test);

    }
  @Ignore
    @Test
    public void TEST_SUPPRIMER_CHALLENGE_SHOULD_RETURN_TRUE() {

        IChallengeDAO iChallengeDAO = DAOFactory.getChallengeDAO();
        boolean test = iChallengeDAO.deleteChallenge(1);
        assertTrue(test);

    }

    @Ignore
    @Test
    public void TEST_DISPLAY_CHALLENGE_BY_ID_ORG_SHOULD_RETURN_lST() {
        IChallengeDAO iChallengeDAO = DAOFactory.getChallengeDAO();
        List<Challenge> test = iChallengeDAO.displayChallengeByOrganisation(1);
        assertEquals(1, test.size());

    }

    @Ignore
    @Test
    public void TEST_DISPLAY_CHALLENGE_SHOULD_RETURN_TRUE() {

        IChallengeDAO iChallengeDAO = DAOFactory.getChallengeDAO();
        List<Challenge> test = iChallengeDAO.displayChallenge();
        assertEquals(1, test.size());

    }
}
