package com.models.daos.interfaces;

import com.models.entities.Cours;
import com.models.entities.Matiere;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;

public interface ICoursDAO {

    public Cours findCourseByName(String courseName) throws SQLException;

    public boolean AjouterCours(Cours c1) throws SQLException;

    public boolean deleteCoursById(int idCours) throws SQLException;

    public boolean updateCours(Cours c1, int id) throws SQLException;

    public ObservableList<Cours> findAll() throws SQLException;

    public Cours findCoursById(int idCours) throws SQLException;

    public List<Cours> findCoursByIdFromateur(int idFormateur) throws SQLException;

    public List<Cours> findCoursByMatiere(Matiere m1) throws SQLException;

    public List findCoursByNomCours(String java);

    public List getCoursValid1EnAttente();

    public List getCoursValid2EnAttente();

    public List<Cours> listCoursCosulterParCoach(int id);

    public Cours findCourByIdFromateur(int idUtilisateur) throws SQLException;

    public Map<String, Integer> getCoursAndViews() throws SQLException;

    public List<Cours> getCoursAtt(int i) throws SQLException;

    void accRefCourV1(Cours c, int i);

    public Cours chercherCoursByNameCours(String chaine, int idutilisateur) throws SQLException;

    public List<Cours> chercherCoursByNameFormateur(String chaine, int idutilisateur) throws SQLException;

    public List<Cours> chercherCoursByNameOrganisme(String chaine, int idutilisateur) throws SQLException;

    public List<Cours> findAll(int idutilisateur) throws SQLException;

    public List<Cours> chercherCoursByNameMatiere(String chaine, int idutilisateur) throws SQLException;

    public int nbChapitreByCours(Cours cours) throws SQLException;

}
