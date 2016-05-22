/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.IChapitreDAO;
import com.database.DataSource;
import com.models.entities.Chapitre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author haikal
 */
public class ImplChapitreDAO implements IChapitreDAO {

    private Connection cnx;

    public ImplChapitreDAO() {
        cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean addChapitre(Chapitre c) {
        String request = "INSERT INTO chapitre (id_cours, nom,  description, resume) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);

            ps.setInt(1, c.getIdCours());
            ps.setString(2, c.getNom());
            ps.setString(3, c.getDescription());
            ps.setString(4, c.getResume());
            int add = ps.executeUpdate();
            ps.close();
            return add == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ImplChapitreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteChapitre(int id) {
        try {
            String request = "DELETE FROM chapitre WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, id);
            ps.executeUpdate();
            int delete = ps.executeUpdate();
            ps.close();
            return (delete == 1);
        } catch (SQLException ex) {
            Logger.getLogger(ImplChapitreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateChapitre(Chapitre c) {
        String request = "UPDATE chapitre SET nom=?, numero=?, description=?, resume=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1, c.getNom());
            ps.setInt(2, c.getNumero());
            ps.setString(3, c.getDescription());
            ps.setString(4, c.getResume());
            int add = ps.executeUpdate();
            ps.close();
            return add == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ImplChapitreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ObservableList<Chapitre> displayChapitre(int idCours) {
        ObservableList<Chapitre> listeChapitre = FXCollections.observableArrayList();
        String request = "SELECT * FROM chapitre WHERE id_cours=? ORDER BY numero";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, idCours);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                Chapitre c = new Chapitre();
                c.setId(res.getInt(1));
                c.setIdCours(res.getInt(2));
                c.setNom(res.getString(3));
                c.setNumero(res.getInt(4));
                c.setDescription(res.getString(5));
                c.setResume(res.getString(6));
                listeChapitre.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplChapitreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeChapitre;
    }

    @Override
    public Chapitre searchChapitre(String nom, int idCours) {
        Chapitre c = new Chapitre();
        String request = "SELECT * FROM chapitre WHERE id_cours=? and nom=?";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(request);
            ps.setInt(1, idCours);
            ps.setString(2, nom);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                c.setId(res.getInt(1));
                c.setIdCours(res.getInt(2));
                c.setNom(res.getString(3));
                c.setNumero(res.getInt(4));
                c.setDescription(res.getString(5));
                c.setResume(res.getString(6));
            }
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ImplChapitreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return c;
    }

    @Override
    public boolean isNumberThere(int number, int idCours) {
        Chapitre c = new Chapitre();
        String request = "SELECT * FROM chapitre WHERE id_cours=? and numero=?";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(request);
            ps.setInt(1, idCours);
            ps.setInt(2, number);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                c.setId(res.getInt(1));
                c.setIdCours(res.getInt(2));
                c.setNom(res.getString(3));
                c.setNumero(res.getInt(4));
                c.setDescription(res.getString(5));
                c.setResume(res.getString(6));
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplChapitreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Chapitre> findChapitreByIdCours(int idCours) throws SQLException {
        List<Chapitre> listeChapitre = new ArrayList<>();
        String requete = "select * from chapitre where id_cours=?";
        PreparedStatement ps = cnx.prepareStatement(requete);
        ps.setInt(1, idCours);
        ResultSet resultat = ps.executeQuery();
        while (resultat.next()) {
            Chapitre c = new Chapitre();
            c.setId(resultat.getInt(1));
            c.setIdCours(resultat.getInt(2));
            c.setNom(resultat.getString(3));
            c.setNumero(resultat.getInt(4));
            c.setDescription(resultat.getString(5));
            c.setResume(resultat.getString(6));
            listeChapitre.add(c);
        }
        if (Objects.nonNull(listeChapitre)) {
            return listeChapitre;
        }

        throw new UnsupportedOperationException();
    }

}
