/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.database.DataSource;
import com.models.daos.interfaces.IInscriptionEvenementDAO;
import com.models.entities.Evenement;
import com.models.entities.InscriptionChallenge;
import com.models.entities.InscriptionEvenement;
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
public class ImplIInscriptionEvenementDAO implements IInscriptionEvenementDAO{
private Connection connection;

    public ImplIInscriptionEvenementDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean ajouterInscriptionEvenement(InscriptionEvenement challenge) throws SQLException {
       java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
         String query = "Insert into inscription_evenement(`id_utilisateur`, `id_evenement`,`date`) "
                + "values (?, ?, ?);";  
         try {
   PreparedStatement pSt = connection.prepareStatement(query);
            pSt.setInt(1, challenge.getIdUtilisateur());
            pSt.setInt(2, challenge.getIdEvenement());
            pSt.setDate(3, date_sql);
            pSt.executeUpdate();
     return true;
     } catch (SQLException ex) {
            System.out.println("Log non ajouté");
            return false;
        }  
         
    }

    @Override
    public List<InscriptionEvenement> getInscriptionEvenementbyUserid(int idUser) {
       ArrayList<InscriptionEvenement> listeInscriptionEvenement = new ArrayList<InscriptionEvenement>();
       String query = "select * from inscription_evenement where id_utilisateur=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idUser);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
            InscriptionEvenement evenement=new InscriptionEvenement();
            evenement.setId(resultat.getInt(1));
            evenement.setIdUtilisateur(resultat.getInt(2));
            evenement.setIdEvenement(resultat.getInt(3));
           evenement.setDate(resultat.getDate(4));
            listeInscriptionEvenement.add(evenement);
            }
             return listeInscriptionEvenement;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du log " + ex.getMessage());
            return null;
        }
    }

    @Override
    public int nbrUtilisateurInscrit(int idEvenement) {
       int nb=0;
       String requete = "select count(*) from inscription_evenement where id_evenement=?";
       try {
        PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idEvenement);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
              nb=resultat.getInt(1);
            }
           } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du log " + ex.getMessage());
           
           }
       return nb; 
    }
    
}
