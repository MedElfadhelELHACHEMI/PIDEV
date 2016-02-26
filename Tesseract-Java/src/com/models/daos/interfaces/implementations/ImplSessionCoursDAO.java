/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.ISessionCoursDAO;
import com.database.DataSource;
import com.models.entities.SessionCours;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
}
