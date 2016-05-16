/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.ISessionCoursDAO;
import com.database.DataSource;
import com.models.entities.Cours;
import com.models.entities.SessionCours;
import com.models.enums.Difficulte;
import com.models.enums.Etat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author haikal
 */
public class ImplSessionCoursDAO implements ISessionCoursDAO{
    
    private Connection connection;

    public ImplSessionCoursDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean ajouterSessionCours(SessionCours SessionCours) {
        String query = "Insert into session_cours(`id_utilisateur`, `id_cours`,`date`) "
                + "values (?, ?, ?);";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            pSt.setInt(1, SessionCours.getId_utilisateur());
            pSt.setInt(2, SessionCours.getId_cours());
            pSt.setDate(3, (java.sql.Date) SessionCours.getDate_session());
            pSt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Log non ajouté");
            return false;
        }
    }

    @Override
    public List<SessionCours> getSessionCoursbyUserid(int idUser) {
        ArrayList<SessionCours> listeSessionCours = new ArrayList<SessionCours>();
        String query = "select * from session_cours where id_utilisateur=?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idUser);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                SessionCours sessioncours= new SessionCours();
                sessioncours.setId_utilisateur(resultat.getInt(1));
                sessioncours.setId_cours(resultat.getInt(2));
                sessioncours.setDate_session(resultat.getDate(3));
                listeSessionCours.add(sessioncours);
            }
            return listeSessionCours;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du log " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<SessionCours> getSessionCoursbyCoursid(int idCours) {
        ArrayList<SessionCours> listeSessionCours = new ArrayList<SessionCours>();
        String query = "select * from session_cours where id_cours=?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idCours);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                SessionCours sessioncours= new SessionCours();
                sessioncours.setId_utilisateur(resultat.getInt(1));
                sessioncours.setId_cours(resultat.getInt(2));
                sessioncours.setDate_session(resultat.getDate(3));
                listeSessionCours.add(sessioncours);
            }
            return listeSessionCours;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du log " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<SessionCours> getSessionCoursbyDate(Date date) {
        ArrayList<SessionCours> listeSessionCours = new ArrayList<SessionCours>();
        String query = "select * from session_cours where date=?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDate(1, (java.sql.Date) date);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                SessionCours sessioncours= new SessionCours();
                sessioncours.setId_utilisateur(resultat.getInt(1));
                sessioncours.setId_cours(resultat.getInt(2));
                sessioncours.setDate_session(resultat.getDate(3));
                listeSessionCours.add(sessioncours);
            }
            return listeSessionCours;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du log " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<SessionCours> listSessionCosulterParCoach(int id) {
     
     ArrayList<SessionCours> listeSessionCours = new ArrayList<SessionCours>();
        String query = "SELECT * from session_cours where id_cours in (select id from cours where id_utilisateur = ?)";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                SessionCours sessioncours= new SessionCours();
                sessioncours.setIdSessionCours(resultat.getInt(1));
                sessioncours.setId_utilisateur(resultat.getInt(2));
                sessioncours.setId_cours(resultat.getInt(3));
                sessioncours.setNbreChapitre(resultat.getInt(4));
                sessioncours.setDate_session(resultat.getDate(5));
                listeSessionCours.add(sessioncours);
            }
            return listeSessionCours;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du log " + ex.getMessage());
            return null;
        }
    }

  @Override
    public int afficherNombreVue(Cours cours) throws SQLException {
       int nb=0;
           Connection connection = DataSource.getInstance().getConnection();
           String requete = "select count(*) from session_cours where id_cours=?";
           PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, cours.getIdCours());
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
              nb=resultat.getInt(1);
            }
          if (nb != 0) {
            return nb;
        }
return nb;
      
    }

     @Override
    public List<Cours> getListeMesCoursTerminer(int idUtilisateur) throws SQLException {
            List<Cours> listeCours = new ArrayList<>();
        String requete = "select * from cours,session_cours where cours.id=session_cours.id_cours and session_cours.id_utilisateur=? and session_cours.badge  NOT like '%NULL%'";
          
        PreparedStatement ps = connection.prepareStatement(requete);
         ps.setInt(1, idUtilisateur);
        ResultSet resultat = ps.executeQuery();

        while (resultat.next()) {
        Cours  cours = new Cours();
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
    public List<Cours> getListeMesCoursEnCours(int idUtilisateur) throws SQLException {
     List<Cours> listeCours = new ArrayList<>();
        String requete = "select * from cours,session_cours where cours.id=session_cours.id_cours and session_cours.badge like '%NULL%' and session_cours.id_utilisateur=?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1, idUtilisateur);
        ResultSet resultat = ps.executeQuery();

        while (resultat.next()) {
   Cours  cours = new Cours();
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
    public int nbChapitreTerminerByCours(Cours cours,int idUtilisateur) throws SQLException {
     int nb=0;
           Connection connection = DataSource.getInstance().getConnection();
           String requete = "select nbr from session_cours where id_cours=? and id_utilisateur=?";
           PreparedStatement ps = connection.prepareStatement(requete);
           ps.setInt(1, cours.getIdCours());
             ps.setInt(2, idUtilisateur);
           ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
              nb=resultat.getInt(1);
            }
          if (nb != 0) {
            return nb;
        }
         return nb;    
    }


      @Override
    public List<Cours> chercherCoursByLoginApprenant(String chaine) throws SQLException{
    List<Cours> listeCours = new ArrayList<>();
    
     Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from cours,session_cours ,utilisateur where cours.id=session_cours.id_cours and utilisateur.id=session_cours.id_utilisateur and utilisateur.pseudo=?";

         PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, chaine);
        ResultSet resultat = ps.executeQuery();

        while (resultat.next()) {
   Cours  cours = new Cours();
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

       return listeCours;
    } 


       @Override
    public String LastCours(Cours cours, int idUtilisateur) throws SQLException {
         String ch=null;
           Connection connection = DataSource.getInstance().getConnection();
           String requete = "select LastCours from session_cours where id_cours=? and id_utilisateur=?";
           PreparedStatement ps = connection.prepareStatement(requete);
           ps.setInt(1, cours.getIdCours());
             ps.setInt(2, idUtilisateur);
           ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
              ch=resultat.getString(1);
            }
         
         return ch;    
    }


    @Override
    public int ScoreByCours(Cours cours, int idUtilisateur) throws SQLException {
       int score=0;
        String requete = "select note from session_cours ,cours where session_cours.id_cours=? and session_cours.id_utilisateur=? and session_cours.badge  NOT like '%NULL%'";
          
        PreparedStatement ps = connection.prepareStatement(requete);
         ps.setInt(1, cours.getIdCours());
         ps.setInt(2, idUtilisateur);
        ResultSet resultat = ps.executeQuery();

        while (resultat.next()) {
            score=resultat.getInt(1);
        }
        

        return score; 
    }

    @Override
    public String getNameBadge(Cours cours, int idUtilisateur) throws SQLException {
     String badge="";
        String requete = "select session_cours.badge from session_cours ,cours where session_cours.id_cours=? and session_cours.id_utilisateur=? and session_cours.badge  NOT like '%NULL%'";
          
        PreparedStatement ps = connection.prepareStatement(requete);
         ps.setInt(1, cours.getIdCours());
         ps.setInt(2, idUtilisateur);
        ResultSet resultat = ps.executeQuery();

        while (resultat.next()) {
            badge=resultat.getString(1);
        }
        

        return badge;   
    }

    @Override
    public boolean verifInscrireCours(Cours cours, int idUtilisateur) throws SQLException {
       boolean verif=false;
       int id=0;
        String requete = "select id_cours from session_cours ,cours where session_cours.id_cours=? and session_cours.id_utilisateur=?";
          
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1, cours.getIdCours()); 
        ps.setInt(2, idUtilisateur);
        ResultSet resultat = ps.executeQuery();

        while (resultat.next()) {
            id=resultat.getInt(1);
        }
        if (id!=0){
           verif=true; 
        }

        return verif;  
    }

    @Override
    public boolean modifierBadgeCours(Cours cours ,int idUtilisateur) throws SQLException {
         String requete = "update session_cours set badge =? where id_utilisateur=? and id_cours=? ";
     String a="true";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1,a);
       ps.setInt(2,idUtilisateur);
       ps.setInt(3,cours.getIdCours());
      int update = ps.executeUpdate();
      ps.close();
      return (update == 1);
    }

    @Override
    public boolean modifierNombreChapCours(Cours cours, int idUtilisateur,int nbr) throws SQLException {
         String requete = "update session_cours set nbr =? where id_utilisateur=? and id_cours=? ";
     
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1,nbr);
       ps.setInt(2,idUtilisateur);
       ps.setInt(3,cours.getIdCours());
      int update = ps.executeUpdate();
      ps.close();
      return (update == 1); 
    }

    
    
}
