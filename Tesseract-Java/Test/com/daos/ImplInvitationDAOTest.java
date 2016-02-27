package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IInvitationDAO;
import com.models.entities.Invitation;
import com.models.enums.Etat;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
 * @author Noor
 */
public class ImplInvitationDAOTest {

    public ImplInvitationDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

   @Ignore
    @Test
    public void TEST_CREATE_INVITATION_SHOULD_RETURN_TRUE() throws SQLException {
        IInvitationDAO invitationDAO = DAOFactory.getInvitationDAO();
        Invitation i = new Invitation();
        i.setIdUtilisateur(1);
        i.setIdOrganisation(5);
        i.setEtat(Etat.ATT.name());
        i.setSens("b");
        i.setDateInvitation(Date.valueOf(LocalDate.now()));
        boolean test = invitationDAO.createInvitation(i);
        assertTrue(test);
    }

    @Ignore
    @Test
    public void TEST_DISPLAY_INVITATION_BY_USER__ID_SHOULD_RETRUN_LIST() throws SQLException {

        IInvitationDAO invitationDAO = DAOFactory.getInvitationDAO();
        List result = invitationDAO.displayInvitationByUserId(1);
        assertEquals(result.size(), 1);

    }

   @Ignore
    @Test
    public void TEST_DISPLAY_INVITATION_BY_ORGANISATION_ID_SHOULD_RETRUN_LIST() throws SQLException {

        IInvitationDAO invitationDAO = DAOFactory.getInvitationDAO();
        List result = invitationDAO.displayInvitationByOrganisationId(1);
        assertEquals(result.size(), 1);

    }

    @Ignore
    @Test
    public void TEST_GET_ALL_INVITATIONS_SHOULD_RETRUN_LIST() throws SQLException {

        IInvitationDAO invitationDAO = DAOFactory.getInvitationDAO();
        List result = invitationDAO.getAllInvitations();
        assertEquals(result.size(), 1);

    }
     @Ignore
      @Test
    public void TEST_GET_EM_INVITATIONS_SHOULD_RETRUN_LIST() throws SQLException {

        IInvitationDAO invitationDAO = DAOFactory.getInvitationDAO();
        List result = invitationDAO.displayInvitationByUserIdEmetteur(1);
        assertEquals(result.size(), 1);

    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

}
