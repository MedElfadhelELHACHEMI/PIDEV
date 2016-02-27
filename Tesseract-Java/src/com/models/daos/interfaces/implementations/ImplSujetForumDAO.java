/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.ISujetForumDAO;
import com.database.DataSource;
import com.models.entities.Apprenant;
import com.models.entities.Log;
import com.models.entities.Matiere;
import com.models.entities.SujetForum;
import com.models.entities.Utilisateur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;

/**
 *
 * @author haikal
 */
public class ImplSujetForumDAO implements ISujetForumDAO{
    private Connection connection;

    public ImplSujetForumDAO() {
        connection = DataSource.getInstance().getConnection();
    }
    
    @Override
    public boolean addSujetForum(SujetForum sujetf) throws SQLException {
        
        String query = "Insert into sujet_forum(`id_utilisateur`,`id_matiere`, `titre`, `description`, `date`) "
                + "values (?, ?, ?, ?,?);";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            pSt.setInt(1,sujetf.getUtilisateur().getIdUtilisateur());
            pSt.setInt(2,sujetf.getMatiere().getIdMatiere());
            pSt.setString(3,sujetf.getTitre());
            pSt.setString(4,sujetf.getDescription());
            pSt.setDate(5, (Date) sujetf.getDate());
            pSt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error to add sujet forum !");
            return false;
        }
    }

    @Override
    public boolean deleteSujetForum(int idSujet) throws SQLException {
        String requete = "delete from sujet_forum where id=?";
        PreparedStatement ps = connection.prepareCall(requete);
        ps.setInt(1, idSujet);
        int reslt = ps.executeUpdate();
        return reslt == 1;
    }

    @Override
    public boolean updateSujetForum(SujetForum sujetf) throws SQLException {
        String requete = "update sujet_forum set id_utilisateur=?,id_matiere=?,titre=?,description=?,date=? where id=?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1,sujetf.getUtilisateur().getIdUtilisateur());
        ps.setInt(2, sujetf.getMatiere().getIdMatiere());
        ps.setString(3,sujetf.getTitre());
        ps.setString(4,sujetf.getDescription());
        ps.setDate(5, sujetf.getDate());
        ps.setInt(6,sujetf.getId());
        int resultat = ps.executeUpdate();
        return resultat == 1;
    }
    

    @Override
    @SuppressWarnings("empty-statement")
    public ArrayList<SujetForum> displaySujetForum() throws SQLException {
        ArrayList<SujetForum> liste = new ArrayList<SujetForum>();
        String query = "select * from sujet_forum";   
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(query);
            while (resultat.next()) {
                SujetForum sj=new SujetForum();
                ImplUtilisateurDAO ud=new ImplUtilisateurDAO();               
                Utilisateur usr = (Utilisateur) ud.getUtilisateurByID(resultat.getInt(2));
                ImplMatiereDAO md=new ImplMatiereDAO();
                Matiere m=new Matiere();
                m=md.findMatiereById(resultat.getInt(3));
                sj.setId(resultat.getInt(1));
                sj.setUtilisateur(usr);
                sj.setMatiere(m);
                sj.setTitre(resultat.getString(4));
                sj.setDescription(resultat.getString(5));
                sj.setDate(resultat.getDate(6));
                liste.add(sj);
            }
            return liste;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("______________________________________ERROR______________________________________");
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            System.out.println("______________________________________ERROR______________________________________");
            return null;
        }
    }

    @Override
    public ArrayList<SujetForum> displayByUtilisateur(Utilisateur utilisateur) throws SQLException {
         ArrayList<SujetForum> liste = new ArrayList<SujetForum>();
        String query = "select * from sujet_forum where id_utilisateur=?";    
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, utilisateur.getIdUtilisateur());
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                SujetForum sj=new SujetForum();
                ImplUtilisateurDAO ud=new ImplUtilisateurDAO();               
                Utilisateur usr = (Utilisateur) ud.getUtilisateurByID(resultat.getInt(2));
                ImplMatiereDAO md=new ImplMatiereDAO();
                Matiere m=new Matiere();
                m=md.findMatiereById(resultat.getInt(3));
                sj.setId(resultat.getInt(1));
                sj.setUtilisateur(usr);
                sj.setMatiere(m);
                sj.setTitre(resultat.getString(4));
                sj.setDescription(resultat.getString(5));
                sj.setDate(resultat.getDate(6));
                liste.add(sj);
            }
            return liste;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("______________________________________ERROR______________________________________");
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            System.out.println("______________________________________ERROR______________________________________");
            return null;
        }
    }

    @Override
    public ArrayList<SujetForum> searchSujetForum(String rch) throws SQLException {
        ArrayList<SujetForum> liste = new ArrayList<SujetForum>();
        liste=this.displaySujetForum();
        for(int i = 0;i<liste.size();i++){
            System.out.println(liste.get(i).getTitre());
            if(compareStrings(liste.get(i).getTitre(), rch)<0.5){
            liste.remove(liste.get(i));
            i--;
                
            }
        }
        return liste;
    }

    @Override
    public ArrayList<SujetForum> displayByMatiere(Matiere m) throws SQLException {
                 ArrayList<SujetForum> liste = new ArrayList<SujetForum>();
        String query = "select * from sujet_forum where id_matiere=?";      
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, m.getIdMatiere());
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                SujetForum sj=new SujetForum();
                ImplUtilisateurDAO ud=new ImplUtilisateurDAO();               
                Utilisateur usr = (Utilisateur) ud.getUtilisateurByID(resultat.getInt(2));
                ImplMatiereDAO md=new ImplMatiereDAO();
                Matiere mt=new Matiere();
                mt=md.findMatiereById(resultat.getInt(3));
                sj.setId(resultat.getInt(1));
                sj.setUtilisateur(usr);
                sj.setMatiere(mt);
                sj.setTitre(resultat.getString(4));
                sj.setDescription(resultat.getString(5));
                sj.setDate(resultat.getDate(6));
                liste.add(sj);
            }
            return liste;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("______________________________________ERROR______________________________________");
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            System.out.println("______________________________________ERROR______________________________________");
            return null;
        }
    }
    private static double compareStrings(String stringA, String stringB) {
        JaroWinkler algorithm = new JaroWinkler();
        return algorithm.getSimilarity(stringA, stringB);
    }

    @Override
    public SujetForum getSujetForum(int id) throws SQLException {
        String query = "select * from sujet_forum where id=?";      
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            resultat.next();
            SujetForum sj=new SujetForum();
            ImplUtilisateurDAO ud=new ImplUtilisateurDAO();               
            Utilisateur usr = (Utilisateur) ud.getUtilisateurByID(resultat.getInt(2));
            ImplMatiereDAO md=new ImplMatiereDAO();
                Matiere mt=new Matiere();
                mt=md.findMatiereById(resultat.getInt(3));
                sj.setId(resultat.getInt(1));
                sj.setUtilisateur(usr);
                sj.setMatiere(mt);
                sj.setTitre(resultat.getString(4));
                sj.setDescription(resultat.getString(5));
                sj.setDate(resultat.getDate(6));
               return sj;
            
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("______________________________________ERROR______________________________________");
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            System.out.println("______________________________________ERROR______________________________________");
            return null;
        }
    }
    
}
