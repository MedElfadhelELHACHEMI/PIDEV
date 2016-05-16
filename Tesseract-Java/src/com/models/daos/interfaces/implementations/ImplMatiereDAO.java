/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;





import com.models.daos.interfaces.IMatiereDAO;
import com.database.DataSource;

import com.models.entities.*;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ImplMatiereDAO implements IMatiereDAO{

    private Connection cnx=null;
    private Statement stm = null;
  
    public ImplMatiereDAO(){
        cnx = DataSource.getInstance().getConnection();
    }
    @Override
    public boolean AjouterMatiere(Matiere m1) throws SQLException{
        
          String req = "insert into matiere (id ,nom,description) values (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,m1.getIdMatiere());
            ps.setString(2,m1.getNomMatiere());
            ps.setString(3,m1.getDescriptionMatiere());
            int ajout=  ps.executeUpdate();
            ps.close();
            return (ajout == 1);
    }

    @Override
    public boolean deleteMatiereByName(String nomMatiere) throws SQLException{
    
      String requete = "delete from matiere where nom=?";
        
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, nomMatiere);
       int delete= ps.executeUpdate();
           ps.close();
        return (delete == 1);
    
    
    }
    
        
 @Override
    public boolean deleteMatiereById(int idMatiere) throws SQLException {
          int delete;
        String requete = "delete from matiere where id='"+idMatiere+"'";
       if (cnx==null){
           delete=0;
       }
       else{     stm = cnx.createStatement();
             delete = stm.executeUpdate(requete);
       }
        return (delete == 1);
    }

    @Override
    public boolean updateMatiere(Matiere m1,int idMatiere) throws SQLException{
         String requete = "update matiere set description=? where id=?";
        
            PreparedStatement ps = cnx.prepareStatement(requete);
            
            ps.setString(1,m1.getDescriptionMatiere());
            ps.setInt(2,idMatiere);
          int update= ps.executeUpdate();
           ps.close();
        return (update == 1);
    }

    @Override
    public List<Matiere> findAll() throws SQLException{
      List<Matiere> listeMatiere = new ArrayList<>();
        String requete = "select * from matiere";
       
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Matiere matiere = new Matiere();
                matiere.setIdMatiere(resultat.getInt(1));
                matiere.setNomMatiere(resultat.getString(2));
                matiere.setDescriptionMatiere(resultat.getString(3));
                listeMatiere.add(matiere);
            }
           if (Objects.nonNull(listeMatiere)) {
            return listeMatiere;
        }

        throw new UnsupportedOperationException();
    
    }
    
    public ObservableList<String> findMatiereName() throws SQLException{
        ObservableList<String> listeNomMatiere = FXCollections.observableArrayList();
        String req = "SELECT nom FROM matiere";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()){
            listeNomMatiere.add(rs.getString(1));
        }
        return listeNomMatiere;
    }
   

    @Override
    public Matiere findMatiereById(int idMatiere) throws SQLException{
       
    Matiere matiere = new Matiere();
        String requete = "select * from matiere where id=?";
        
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, idMatiere);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                matiere.setIdMatiere(resultat.getInt(1));
                matiere.setNomMatiere(resultat.getString(2));
                matiere.setDescriptionMatiere(resultat.getString(3)); 
            }
          if (Objects.nonNull(matiere)) {
            return matiere;
        }

        throw new UnsupportedOperationException();
    
    }

    @Override
    public Matiere findMatiereByName(String nomMatiere) throws SQLException{
    Matiere matiere = new Matiere();
        String requete = "select * from matiere where nom=?";
       
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, nomMatiere);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                matiere.setIdMatiere(resultat.getInt(1));
                matiere.setNomMatiere(resultat.getString(2));
                matiere.setDescriptionMatiere(resultat.getString(2)); 
            }
          if (Objects.nonNull(matiere)) {
            return matiere;
        }

        throw new UnsupportedOperationException();
    
    }
    

    @Override
    public List<Cours> affichageCoursByMatiere(int idMatiere) throws SQLException{
        List<Cours> listeCours = new ArrayList<>();
        String requete = "select * from cours where id=?";
       
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, idMatiere);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Cours cours = new Cours();
                cours.setIdCours(resultat.getInt(1));
                cours.setNomCours(resultat.getString(3));
          listeCours.add(cours);
            }
           if (Objects.nonNull(listeCours)) {
            return listeCours;
        }

        throw new UnsupportedOperationException();
    
    }

    @Override
    public String getNameMatiere(Cours cours) throws SQLException {
      
        String name =null;
        String requete = "select nom from matiere where id=?";
        
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, cours.getIdMatiere());
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
              name=resultat.getString(1);
            }
          if (Objects.nonNull(name)) {
            return name;
        }

        throw new UnsupportedOperationException();
   
    }

   
    }
