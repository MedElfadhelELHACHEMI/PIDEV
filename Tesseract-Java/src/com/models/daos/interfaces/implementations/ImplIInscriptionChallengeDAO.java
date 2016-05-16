/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.database.DataSource;
import com.models.daos.interfaces.IInscriptionChallengeDAO;
import com.models.entities.Challenge;
import com.models.entities.InscriptionChallenge;
import com.models.entities.SessionCours;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sameh
 */
public class ImplIInscriptionChallengeDAO implements IInscriptionChallengeDAO{
private Connection connection;

    public ImplIInscriptionChallengeDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean ajouterInscriptionChallenge(InscriptionChallenge challenge) throws SQLException {
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
         String query = "Insert into inscription_challenge(`id_utilisateur`, `id_challenge`,`date`) "
                + "values (?, ?, ?);";
  
         try {
   PreparedStatement pSt = connection.prepareStatement(query);
            pSt.setInt(1, challenge.getIdUtilisateur());
            pSt.setInt(2, challenge.getIdchallenge());
            pSt.setDate(3, date_sql);
            pSt.executeUpdate();
     return true;
     } catch (SQLException ex) {
            System.out.println("Log non ajouté");
            return false;
        } 
    }

    @Override
    public List<InscriptionChallenge> getInscriptionChallengebyUserid(int idUser) {
       ArrayList<InscriptionChallenge> listeInscriptionChallenge = new ArrayList<InscriptionChallenge>();
       String query = "select * from inscription_challenge where id_utilisateur=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idUser);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
            InscriptionChallenge challenge=new InscriptionChallenge();
            challenge.setId(resultat.getInt(1));
            challenge.setIdUtilisateur(resultat.getInt(2));
            challenge.setIdchallenge(resultat.getInt(3));
            challenge.setDate(resultat.getDate(4));
            challenge.setSolution(resultat.getString(5));
            listeInscriptionChallenge.add(challenge);
            }
             return listeInscriptionChallenge;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du log " + ex.getMessage());
            return null;
        }   
    }

    @Override
    public int nbrUtilisateurInscrit(int idChallenge) {
       int nb=0;
       String requete = "select count(*) from inscription_challenge where id_challenge=?";
       try {
        PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idChallenge);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
              nb=resultat.getInt(1);
            }
           } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du log " + ex.getMessage());
           
           }
       return nb;
    
    
}}
