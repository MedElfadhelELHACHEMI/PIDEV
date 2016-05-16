/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.IObjectifDAO;
import com.database.DataSource;
import com.models.entities.Objectif;
import com.models.enums.Difficulte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ImplObjectifDAO implements IObjectifDAO{

    private Connection cnx;
    public ImplObjectifDAO(){
        cnx = DataSource.getInstance().getConnection();
    }
    @Override
    public boolean addObjectif(Objectif o) {
        String request ="INSERT INTO objectif (id_chapitre, numero, nom, description, difficulte) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            System.out.println(o.getIdChapitre());
            ps.setInt(1, o.getIdChapitre());
            ps.setInt(2, o.getNumero());
            ps.setString(3, o.getNom());
            ps.setString(4, o.getDescription());
            System.out.println(o.getDifficulte().toString());
            ps.setObject(5, o.getDifficulte().toString());
            int add = ps.executeUpdate();
            ps.close();
            return add==1;
        } catch (SQLException ex) {
            Logger.getLogger(ImplChapitreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteObjectif(int id) {
        try {
            String request = "DELETE FROM objectif WHERE id=?";
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
    public boolean updateObjectif(Objectif o) {
        String request = "UPDATE objectif SET numero=?, nom=?, description=?, difficulte=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(2, o.getNom());
            ps.setInt(1, o.getNumero());
            ps.setString(3, o.getDescription());
            ps.setString(4, o.getDifficulte().toString());
            int add = ps.executeUpdate();
            ps.close();
            return add==1;
        } catch (SQLException ex) {
            Logger.getLogger(ImplChapitreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ObservableList<Objectif> displayObjectifs(int idChapitre) {
        ObservableList<Objectif> listeObjectif = FXCollections.observableArrayList();
        String request = "SELECT * FROM objectif WHERE id_chapitre=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, idChapitre);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                Objectif o = new Objectif();
                o.setId(res.getInt(1));
                o.setIdChapitre(res.getInt(2));
                o.setNumero(res.getInt(3));
                o.setNom(res.getString(4));
                o.setDescription(res.getString(5));
                o.setDifficulte(Difficulte.valueOf(res.getString(6)));
                listeObjectif.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplChapitreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeObjectif;
    }

    @Override
    public Objectif searchObjectif(String nom, int idChapitre) {
        Objectif o = new Objectif();
        String request = "SELECT * FROM chapitre WHERE id_chapitre=? and nom=?";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(request);
            ps.setInt(1, idChapitre);
            ps.setString(2, nom);
            ResultSet res=ps.executeQuery();
            while (res.next()){
                o.setId(res.getInt(1));
                o.setIdChapitre(res.getInt(2));
                o.setNom(res.getString(4));
                o.setNumero(res.getInt(3));
                o.setDescription(res.getString(5));
                o.setDifficulte(Difficulte.valueOf(res.getString(6)));
            }
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(ImplChapitreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return o;
    }

    @Override
    public boolean isNumberThere(int number, int idChapitre) {
        Objectif c = new Objectif();
        String request = "SELECT * FROM objectif WHERE id_chapitre=? and numero=?";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(request);
            ps.setInt(1, idChapitre);
            ps.setInt(2, number);
            ResultSet res=ps.executeQuery();
            while (res.next()){
                c.setId(res.getInt(1));
                c.setIdChapitre(res.getInt(2));
                c.setNom(res.getString(3));
                c.setNumero(res.getInt(4));
                c.setDescription(res.getString(5));
                c.setDifficulte(Difficulte.valueOf(res.getString(6)));
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplChapitreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Objectif searchOBJByChapitre(int number, int idChapitre) throws SQLException {
        Objectif c = new Objectif();
        String request = "SELECT * FROM objectif WHERE id_chapitre=? and numero=?";
        PreparedStatement ps;
        
            ps = cnx.prepareStatement(request);
            ps.setInt(1, idChapitre);
            ps.setInt(2, number);
            ResultSet res=ps.executeQuery();
            while (res.next()){
                c.setId(res.getInt(1));
                c.setIdChapitre(res.getInt(2));
                c.setNom(res.getString(4));
                c.setNumero(res.getInt(3));
                c.setDescription(res.getString(5));
                c.setDifficulte(Difficulte.valueOf(res.getString(6)));
                
            }
             if (Objects.nonNull(c)) {
            return c;
        }
       
       throw new UnsupportedOperationException(); 
    }

}
