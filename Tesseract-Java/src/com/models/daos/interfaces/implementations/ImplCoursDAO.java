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
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

        String req = "insert into cours (nom,difficulte,description,id_matiere,badge,affiche) values (?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, c1.getNomCours());
        ps.setObject(2, c1.getDifficulte().toString());
        ps.setString(3, c1.getDescriptionCours());
        ps.setInt(4, c1.getIdMatiere());
        ps.setString(5, c1.getBadge());
        ps.setString(6, c1.getAffiche());
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
    public ObservableList<Cours> findAll(){
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
            cours.setNomCours(resultat.getString(2));
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

}
