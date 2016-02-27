package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IBanForumDAO;
import com.models.entities.BanForum;
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
public class ImplBanForumDAOTest {

    public ImplBanForumDAOTest() {
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
    public void TEST_SETBANFORUM_SHOULD_RETURN_TRUE() {
        IBanForumDAO iBanForumDAO = DAOFactory.getBanForumDAO();
        BanForum banForum = new BanForum();
        banForum.setCause("Insult");
        banForum.setDateBan(new Date(13));
        banForum.setDuree(12);
        banForum.setIdUtilisateur(14);
        boolean test = iBanForumDAO.setBanForum(banForum);
        assertTrue(test);
    }

    @Ignore
    @Test
    public void TEST_SUPPRIMERBANFORUM_SHOULD_RETURN_TRUE() {
        IBanForumDAO iBanForumDAO = DAOFactory.getBanForumDAO();

        boolean test = iBanForumDAO.supprimerBanForum(1);
        assertTrue(test);
    }

    @Ignore
    @Test
    public void TEST_GET_ALL_BANFORUM_SHOULD_EQUAL_TO_0() {

        IBanForumDAO iBanForumDAO = DAOFactory.getBanForumDAO();
        List<BanForum> list = iBanForumDAO.getallBanForum();
        assertEquals(0, list.size());

    }

    @Ignore
    @Test(expected = AssertionError.class)
    public void TEST_GET_FORUM_BY_ID_SHOULD_RETURN_NULL_BECAUSE_BD_IS_EMPTY() {

        IBanForumDAO iBanForumDAO = DAOFactory.getBanForumDAO();
        BanForum banForum = iBanForumDAO.getBanForumbyid(2);
        assertEquals(banForum, null);

    }

}
