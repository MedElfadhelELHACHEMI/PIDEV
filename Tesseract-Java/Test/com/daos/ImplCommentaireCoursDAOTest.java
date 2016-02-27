/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.ICommentaireCoursDAO;
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
public class ImplCommentaireCoursDAOTest {

    public ImplCommentaireCoursDAOTest() {
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
    public void display_commentaire_by_cours() {

        ICommentaireCoursDAO commentaireCoursDAO = DAOFactory.getCommentaireCoursDAO();
        assertNotNull(commentaireCoursDAO.afficherCommentaireByCours(1));
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
