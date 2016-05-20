/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.ICoursDAO;
import com.database.DataSource;

import com.models.entities.Cours;
import com.models.entities.Matiere;
import com.models.enums.Difficulte;
import com.models.enums.Etat;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Sameh
 */
public class ImplCoursDAO implements ICoursDAO {

    private Connection cnx;

    public ImplCoursDAO() {
        cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean AjouterCours(Cours c1) throws SQLException {
        
        String req = "insert into cours (nom,description,id_matiere,badge,affiche,video,validation1,validation2) values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, c1.getNomCours());

        ps.setString(2, c1.getDescriptionCours());
        ps.setInt(3, c1.getIdMatiere());
        ps.setString(4, c1.getBadge());
        ps.setString(5, c1.getAffiche());
        ps.setString(6, c1.getVideo());
        ps.setString(7, String.valueOf( Etat.ATT));
        ps.setString(8, String.valueOf( Etat.ATT) );
        int ajout = ps.executeUpdate();
        ps.close();
        return (ajout == 1);

    }

    @Override
    public boolean deleteCoursById(int idCours) throws SQLException {
        String requete = "delete from cours where id=?";

        PreparedStatement ps = cnx.prepareStatement(requete);
        ps.setInt(1, idCours);
        ps.executeUpdate();
        int delete = ps.executeUpdate();
        ps.close();
        return (delete == 1);
    }

    @Override
    public ObservableList<Cours> findAll() {
        ObservableList<Cours> listeCours = FXCollections.observableArrayList();
        try {
            String requete = "select * from cours";

            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Cours cours = new Cours();
                cours.setIdCours(resultat.getInt(1));
                cours.setIdMatiere(resultat.getInt(2));
                cours.setIdFormateur(resultat.getInt(3));
                cours.setNomCours(resultat.getString(4));
                cours.setDifficulte(Difficulte.valueOf(resultat.getString(5)));
                cours.setDescriptionCours(resultat.getString(6));
                cours.setAffiche(resultat.getString(8));
                cours.setBadge(resultat.getString(7));

                listeCours.add(cours);
            }
            return listeCours;
        } catch (SQLException ex) {
            Logger.getLogger(ImplCoursDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeCours;
    }
// problÃ¨me bacem

    @Override
    public Cours findCoursById(int idCours) throws SQLException {
        Cours cours = new Cours();
        String requete = "select * from cours where id=?";

        PreparedStatement ps = cnx.prepareStatement(requete);
        ps.setInt(1, idCours);
        ResultSet resultat = ps.executeQuery();
        while (resultat.next()) {
            cours.setIdCours(resultat.getInt(1));
            cours.setNomCours(resultat.getString(3));
            cours.setDescriptionCours(resultat.getString(5));
        }
        if (Objects.nonNull(cours)) {
            return cours;
        }

        throw new UnsupportedOperationException();

    }
// problÃ¨me  bacem

    @Override
    public List<Cours> findCoursByIdFromateur(int idFormateur) throws SQLException {
        List<Cours> listeCours = new ArrayList<>();
        String requete = "select * from cours where id_utilisateur=?";
        PreparedStatement ps = cnx.prepareStatement(requete);
        ps.setInt(1, idFormateur);
        ResultSet resultat = ps.executeQuery();

        while (resultat.next()) {
            Cours cours = new Cours();
            cours.setIdCours(resultat.getInt(1));
            cours.setIdMatiere(resultat.getInt(2));
            cours.setIdFormateur(resultat.getInt(3));
            cours.setNomCours(resultat.getString(4));
            cours.setDifficulte(Difficulte.valueOf(resultat.getString(5)));
            cours.setDescriptionCours(resultat.getString(6));
            cours.setBadge(resultat.getString(7));
            cours.setAffiche(resultat.getString(8));
            cours.setVideo(resultat.getString(9));
            cours.setValidation1(Etat.valueOf(resultat.getString(10)));
            cours.setValidation2(Etat.valueOf(resultat.getString(11)));
            cours.setLanguage(resultat.getString(12));
            cours.setUploadDate(resultat.getDate(13).toLocalDate());

            listeCours.add(cours);

        }
        if (Objects.nonNull(listeCours)) {
            return listeCours;
        }

        throw new UnsupportedOperationException();

    }

    @Override
    public boolean updateCours(Cours c1, int id) throws SQLException {
        String requete = "update cours set nom =?, difficulte=?, description=?, badge=?, affiche=? where id=?";

        PreparedStatement ps = cnx.prepareStatement(requete);
        ps.setString(1, c1.getNomCours());
        ps.setString(2, c1.getDifficulte().toString());
        ps.setString(3, c1.getDescriptionCours());
        ps.setString(4, c1.getBadge());
        ps.setString(5, c1.getAffiche());
        ps.setInt(6, id);

        int update = ps.executeUpdate();
        ps.close();
        return (update == 1);
    }

    @Override
    public List<Cours> findCoursByMatiere(Matiere m1) throws SQLException {
        List<Cours> listeCours = new ArrayList<>();
        String requete = "select * from cours where id_matiere=?";

        PreparedStatement ps = cnx.prepareStatement(requete);
        ps.setInt(1, m1.getIdMatiere());
        ResultSet resultat = ps.executeQuery();

        while (resultat.next()) {
            Cours cours = new Cours();
            cours.setIdCours(resultat.getInt(1));
            cours.setNomCours(resultat.getString(2));
            listeCours.add(cours);
        }
        if (Objects.nonNull(listeCours)) {
            return listeCours;
        }

        throw new UnsupportedOperationException();

    }

    @Override
    public List findCoursByNomCours(String java) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public List getCoursValid2EnAttente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cours> listCoursCosulterParCoach(int id) {
        try {
            List<Cours> listeCours = new ArrayList<>();
            String requete = "select * from cours where id_utilisateur=?";
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Cours cours = new Cours();
                cours.setIdCours(resultat.getInt(1));
                cours.setIdMatiere(resultat.getInt(2));
                cours.setIdFormateur(resultat.getInt(3));
                cours.setNomCours(resultat.getString(4));
                cours.setDifficulte(Difficulte.valueOf(resultat.getString(5)));
                cours.setDescriptionCours(resultat.getString(6));
                cours.setBadge(resultat.getString(7));
                cours.setAffiche(resultat.getString(8));
                cours.setVideo(resultat.getString(9));
                cours.setValidation1(Etat.valueOf(resultat.getString(10)));
                cours.setValidation2(Etat.valueOf(resultat.getString(11)));
                cours.setLanguage(resultat.getString(12));
                cours.setUploadDate(resultat.getDate(13).toLocalDate());

                listeCours.add(cours);

            }
            if (Objects.nonNull(listeCours)) {
                return listeCours;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImplCoursDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Cours findCourByIdFromateur(int idUtilisateur) throws SQLException {
        String requete = "select * from cours where id=(SELECT id_cours FROM `session_cours` inner join `cours` on `cours`.id=`session_cours`.id_cours where `cours`.id_utilisateur=? group by id_cours order by count(id_cours) desc LIMIT 1 )";
        PreparedStatement ps = cnx.prepareStatement(requete);
        ps.setInt(1, idUtilisateur);
        ResultSet resultat = ps.executeQuery();
        Cours cours = new Cours();
        while (resultat.next()) {
            cours.setIdCours(resultat.getInt(1));
            cours.setNomCours(resultat.getString(4));
            cours.setDescriptionCours(resultat.getString(6));
        }
        if (Objects.nonNull(cours)) {
            return cours;
        }

        throw new UnsupportedOperationException();

    }

    @Override
    public Map<String, Integer> getCoursAndViews() throws SQLException {
        String requete = "select nom, count(id_cours) from session_cours sc inner join cours c on sc.id_cours = c.id group by id_cours";
        PreparedStatement ps = cnx.prepareStatement(requete);
        ResultSet resultat = ps.executeQuery();
        Map<String, Integer> m = new HashMap();
        while (resultat.next()) {
            m.put(resultat.getString(1), resultat.getInt(2));
        }
        return m;
    }

    @Override
    public List<Cours> getCoursAtt(int i) throws SQLException {
        String requete = "";
        if (i == 1) {
            requete = "SELECT * FROM cours WHERE validation1 = 'ATT'";
        } else if (i == 2) {
            requete = "SELECT * FROM cours WHERE validation2 = 'ATT'";
        }
        PreparedStatement ps = cnx.prepareStatement(requete);
        ResultSet resultat = ps.executeQuery();
        List<Cours> listeCours = new ArrayList<>();
        while (resultat.next()) {
            Cours cours = new Cours();
            cours.setIdCours(resultat.getInt(1));
            cours.setIdMatiere(resultat.getInt(2));
            cours.setIdFormateur(resultat.getInt(3));
            cours.setNomCours(resultat.getString(4));
            cours.setDescriptionCours(resultat.getString(6));
            cours.setBadge(resultat.getString(7));
            cours.setAffiche(resultat.getString(8));
            cours.setVideo(resultat.getString(9));
            cours.setValidation1(Etat.valueOf(resultat.getString(10)));
            cours.setValidation2(Etat.valueOf(resultat.getString(11)));
            cours.setLanguage(resultat.getString(12));
            //cours.setUploadDate(resultat.getDate(13).toLocalDate());

            listeCours.add(cours);
        }
        return listeCours;
    }

    public void accRefCourV1(Cours c, int i) {
        try {
            Connection connection = DataSource.getInstance().getConnection();
            String requete = "UPDATE cours SET validation1 = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(2, c.getIdCours());
            if (i == 0) {
                ps.setString(1, "REF");
            } else if (i == 1) {
                ps.setString(1, "ACC");
            }
            int resultat = ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImplFormateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Cours chercherCoursByNameCours(String chaine, int idutilisateur) throws SQLException {
        Cours cours = new Cours();
        String requete = "select * from cours where nom=? and cours.id  NOT IN (select id_cours from session_cours where id_utilisateur=?)";

        PreparedStatement ps = cnx.prepareStatement(requete);
        ps.setString(1, chaine);
        ps.setInt(2, idutilisateur);
        ResultSet resultat = ps.executeQuery();
        while (resultat.next()) {

            cours.setIdCours(resultat.getInt(1));
            cours.setIdMatiere(resultat.getInt(2));
            cours.setIdFormateur(resultat.getInt(3));
            cours.setNomCours(resultat.getString(4));
            cours.setDifficulte(Difficulte.valueOf(resultat.getString(5)));
            cours.setDescriptionCours(resultat.getString(6));
            cours.setBadge(resultat.getString(7));
            cours.setAffiche(resultat.getString(8));
            cours.setVideo(resultat.getString(9));
            cours.setValidation1(Etat.valueOf(resultat.getString(10)));
            cours.setValidation2(Etat.valueOf(resultat.getString(11)));
            cours.setLanguage(resultat.getString(12));

        }
        if (Objects.nonNull(cours)) {
            return cours;
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public List<Cours> chercherCoursByNameFormateur(String chaine, int idutilisateur) throws SQLException {
        List<Cours> listeCours = new ArrayList<>();
        String requete = "select * from cours ,utilisateur where cours.id_utilisateur=utilisateur.id and utilisateur.nom=? and cours.id  NOT IN (select id_cours from session_cours where id_utilisateur=?)";

        PreparedStatement ps = cnx.prepareStatement(requete);

        ps.setString(1, chaine);
        ps.setInt(2, idutilisateur);
        ResultSet resultat = ps.executeQuery();

        while (resultat.next()) {
            Cours cours = new Cours();

            cours.setIdCours(resultat.getInt(1));
            cours.setIdMatiere(resultat.getInt(2));
            cours.setIdFormateur(resultat.getInt(3));
            cours.setNomCours(resultat.getString(4));
            cours.setDifficulte(Difficulte.valueOf(resultat.getString(5)));
            cours.setDescriptionCours(resultat.getString(6));
            cours.setBadge(resultat.getString(7));
            cours.setAffiche(resultat.getString(8));
            cours.setVideo(resultat.getString(9));
            cours.setValidation1(Etat.valueOf(resultat.getString(10)));
            cours.setValidation2(Etat.valueOf(resultat.getString(11)));
            cours.setLanguage(resultat.getString(12));

            listeCours.add(cours);
        }
        if (Objects.nonNull(listeCours)) {
            return listeCours;
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public List<Cours> chercherCoursByNameOrganisme(String chaine, int idutilisateur) throws SQLException {
        List<Cours> listeCours = new ArrayList<>();
        String requete = "select * from cours ,utilisateur,organisation where cours.id_utilisateur=utilisateur.id and utilisateur.id_organisation=organisation.id and organisation.nom=?and cours.id  NOT IN (select id_cours from session_cours where id_utilisateur=?)";

        PreparedStatement ps = cnx.prepareStatement(requete);
        ps.setString(1, chaine);
        ps.setInt(2, idutilisateur);
        ResultSet resultat = ps.executeQuery();

        while (resultat.next()) {
            Cours cours = new Cours();
            cours.setIdCours(resultat.getInt(1));
            cours.setIdMatiere(resultat.getInt(2));
            cours.setIdFormateur(resultat.getInt(3));
            cours.setNomCours(resultat.getString(4));
            cours.setDifficulte(Difficulte.valueOf(resultat.getString(5)));
            cours.setDescriptionCours(resultat.getString(6));
            cours.setBadge(resultat.getString(7));
            cours.setAffiche(resultat.getString(8));
            cours.setVideo(resultat.getString(9));
            cours.setValidation1(Etat.valueOf(resultat.getString(10)));
            cours.setValidation2(Etat.valueOf(resultat.getString(11)));
            cours.setLanguage(resultat.getString(12));

            listeCours.add(cours);
        }
        if (Objects.nonNull(listeCours)) {
            return listeCours;
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public List<Cours> findAll(int idutilisateur) {
        List<Cours> listeCours = new ArrayList();
        try {
            String requete = "select * from cours where cours.id  NOT IN (select id_cours from session_cours where id_utilisateur=?)";

            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, idutilisateur);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Cours cours = new Cours();
                cours.setIdCours(resultat.getInt(1));
                cours.setIdMatiere(resultat.getInt(2));
                cours.setIdFormateur(resultat.getInt(3));
                cours.setNomCours(resultat.getString(4));
                cours.setDifficulte(Difficulte.valueOf(resultat.getString(5)));
                cours.setDescriptionCours(resultat.getString(6));
                cours.setBadge(resultat.getString(7));
                cours.setAffiche(resultat.getString(8));
                cours.setVideo(resultat.getString(9));
                cours.setValidation1(Etat.valueOf(resultat.getString(10)));
                cours.setValidation2(Etat.valueOf(resultat.getString(11)));
                cours.setLanguage(resultat.getString(12));

                listeCours.add(cours);
            }
            return listeCours;
        } catch (SQLException ex) {
            Logger.getLogger(ImplCoursDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeCours;
    }

    @Override
    public List<Cours> chercherCoursByNameMatiere(String chaine, int idutilisateur) throws SQLException {
        List<Cours> listeCours = new ArrayList<>();
        String requete = "select * from cours ,matiere where cours.id_matiere=matiere.id and matiere.nom=? and cours.id  NOT IN (select id_cours from session_cours where id_utilisateur=?)";

        PreparedStatement ps = cnx.prepareStatement(requete);

        ps.setString(1, chaine);
        ps.setInt(2, idutilisateur);
        ResultSet resultat = ps.executeQuery();

        while (resultat.next()) {
            Cours cours = new Cours();
            cours.setIdCours(resultat.getInt(1));
            cours.setIdMatiere(resultat.getInt(2));
            cours.setIdFormateur(resultat.getInt(3));
            cours.setNomCours(resultat.getString(4));
            cours.setDifficulte(Difficulte.valueOf(resultat.getString(5)));
            cours.setDescriptionCours(resultat.getString(6));
            cours.setBadge(resultat.getString(7));
            cours.setAffiche(resultat.getString(8));
            cours.setVideo(resultat.getString(9));
            cours.setValidation1(Etat.valueOf(resultat.getString(10)));
            cours.setValidation2(Etat.valueOf(resultat.getString(11)));
            cours.setLanguage(resultat.getString(12));

            listeCours.add(cours);
        }
        if (Objects.nonNull(listeCours)) {
            return listeCours;
        }

        throw new UnsupportedOperationException();

    }

    @Override
    public int nbChapitreByCours(Cours cours) throws SQLException {
        int nb = 0;
        String requete = "select count(*) from chapitre where chapitre.	id_cours=?";

        PreparedStatement ps = cnx.prepareStatement(requete);
        ps.setInt(1, cours.getIdCours());
        ResultSet resultat = ps.executeQuery();

        while (resultat.next()) {
            nb = resultat.getInt(1);

        }

        return nb;

    }

    public Cours findCourseByName(String courseName) throws SQLException {

        Cours cours = new Cours();
        String requete = "select * from cours where nom=?";

        PreparedStatement ps = cnx.prepareStatement(requete);
        ps.setString(1, courseName);
        ResultSet resultat = ps.executeQuery();
        while (resultat.next()) {
            cours.setIdCours(resultat.getInt(1));
            cours.setIdMatiere(resultat.getInt(2));
            cours.setIdFormateur(resultat.getInt(3));
            cours.setNomCours(resultat.getString(4));
//            cours.setDifficulte(Difficulte.valueOf(resultat.getString(5)));
            cours.setDescriptionCours(resultat.getString(6));
            cours.setBadge(resultat.getString(7));
            cours.setAffiche(resultat.getString(8));
            cours.setVideo(resultat.getString(9));
            cours.setValidation1(Etat.valueOf(resultat.getString(10)));
            cours.setValidation2(Etat.valueOf(resultat.getString(11)));
            cours.setLanguage(resultat.getString(12));
        }
        if (Objects.nonNull(cours)) {
            return cours;
        }

        throw new UnsupportedOperationException();

    }

    @Override
    public List<Cours> getCoursValid1EnAttente(int idCoach)  throws SQLException{
        List courses = new ArrayList();
        Cours cours ;
        String req= "select * from cours where id_utilisateur = ? and validation1 like 'ATT' ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idCoach);
        ResultSet resultat = ps.executeQuery();
        
        while (resultat.next()) {
            cours = new Cours();
            cours.setIdCours(resultat.getInt(1));
            cours.setNomCours(resultat.getString(4));
            cours.setVideo(resultat.getString(9));
            courses.add(cours);
      
        }
        return courses;
    
    
    
    }
}
