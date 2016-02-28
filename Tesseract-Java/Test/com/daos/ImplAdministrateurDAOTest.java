package com.daos;


import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IAdministrateurDAO;
import com.models.daos.interfaces.IUtilisateurDAO;
import com.models.daos.interfaces.implementations.ImplUtilisateurDAO;
import com.models.entities.Administrateur;
import com.models.entities.Utilisateur;

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

public class ImplAdministrateurDAOTest {

    public ImplAdministrateurDAOTest() {
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
    public void TEST_UPDATE_ADMINISTRATEUR_BY_ID_SHOULD_RETURN_TRUE() throws SQLException {

        IAdministrateurDAO administrateurDAO = DAOFactory.getAdministrateurDAO();
        boolean result = administrateurDAO.modifierAdministrateur("aministrateur", new Administrateur());
        assertTrue(result);
    }

    @Ignore
    @Test
    public void TEST_GET_ALL_ADMINISTRATEUR_SHOULD_RETURN_LIST_ADMINISTRATEUR() throws SQLException {

        IAdministrateurDAO administrateurDAO = DAOFactory.getAdministrateurDAO();
        List<Administrateur> listAdministrateur = administrateurDAO.getAllAdministrateurs();
        assertEquals(1, listAdministrateur.size());
    }

    @Ignore
    @Test
    public void TEST_SUPPRIMER_ADMINISTRATEUR_BY_LOGIN_SHOULD_RETURN_TRUE() throws SQLException {

        IAdministrateurDAO AdministrateurDAO = DAOFactory.getAdministrateurDAO();
        boolean resultat = AdministrateurDAO.supprimerAdministrateurByLogin("administrateur");
        assertTrue(resultat);
    }

    @Ignore
    @Test
    public void TEST_AJOUTER_ADMINISTRATEUR_SHOULD_RETURN_TRUE() throws SQLException {

        IAdministrateurDAO administrateurDAO = DAOFactory.getAdministrateurDAO();
        Administrateur administrateur = new Administrateur("@secours", "administrateur", "unclebob", "bob", "bob", new Date(100), 123, " - ", " -  ", "  -  ");
        boolean test = administrateurDAO.ajouterAdministrateur(administrateur);
        assertTrue(test);

    }

    @Ignore
    @Test
    public void TEST_GET_ADMINISTRATEUR_BY_LOGIN_SHOULD_RETURN_ADMINISTRATEUR() throws SQLException {

        IAdministrateurDAO administrateurDAO = DAOFactory.getAdministrateurDAO();
        Administrateur administrateur = administrateurDAO.getAdministrateurByLogin("administrateur");
        assertNotNull(administrateur);
    }
        @Ignore
    @Test
    public void TEST_GET_USER_BY_MAIL_SHOULD_RETURN_USER() throws SQLException {

        IUtilisateurDAO x = new ImplUtilisateurDAO();
        Utilisateur u = x.getUtilisateurByMail("correctmail");
        System.out.println(u);
        assertTrue(u.getIdUtilisateur()==1);
    }
}
