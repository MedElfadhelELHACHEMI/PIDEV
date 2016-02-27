package com.daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.models.daos.interfaces.DAOFactory;
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
public class ImplMatiereDAOTest {

    public ImplMatiereDAOTest() {
    }
    // boolean test=matiereDao.AjouterMatiere(matiere);
    // boolean test=matiereDao.deleteMatiereById(4);
    //boolean test=matiereDao.deleteMatiereByName("dlp");
    //Matiere a=matiereDao.findMatiereById(1);
    //assertNotNull(a);
    // boolean test=matiereDao.updateMatiere(matiere,1);
    // assertTrue(test);
    //List<Cours> test=matiereDao.affichageCoursByMatiere(1);
    //assertEquals(test.size(),1);
    //Matiere test=matiereDao.findMatiereByName(matiere.getNomMatiere());
    //assertNotNull(test);

    @BeforeClass
    public static void setUpClass() {
    }

    @Ignore
    @Test
    public void TEST_GET_ALL_MATIERE_SHOULD_RETURN_LIST_IF_SUCCESS() throws SQLException {
        Matiere matiere = new Matiere(1, "haikel", "yassmine");
        IMatiereDAO matiereDao = DAOFactory.getMatiereDAO();

        List<Matiere> test = matiereDao.findAll();
        assertEquals(test.size(), 1);

    }

    @Ignore
    @Test
    public void TEST_CREATE_MATIERE_SHOULD_RETURN_TRUE_IF_SUCCESS() throws SQLException {
        Matiere matiere = new Matiere("haikel", "yassmine");
        IMatiereDAO matiereDao = DAOFactory.getMatiereDAO();

        boolean test = matiereDao.AjouterMatiere(matiere);
        assertTrue(test);

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
