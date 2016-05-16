package com.models.daos.interfaces.implementations;

import static java.lang.System.*;
import com.models.daos.interfaces.IChallengeDAO;
import com.database.DataSource;
import com.models.entities.Challenge;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haikal
 */
public class ImplChallengeDAO implements IChallengeDAO {

    private Connection connection;

    public ImplChallengeDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean addChallenge(Challenge chl) {
        String query = "Insert into challenge(`id`, `id_organisation`, `nom`, `description` , `theme` , `date`) "
                + "values (NULL, ?, ?, ? ,? , ?)";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);

            pSt.setInt(1, chl.getIdOrganisation());
            pSt.setString(2, chl.getNom());
            pSt.setString(3, chl.getDescription());
            pSt.setString(4, chl.getTheme());
            pSt.setDate(5, chl.getDateChallenge());
            pSt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("error to add a challenge !!");
            return false;
        }
    }

    @Override
    public boolean deleteChallenge(int idChl) {

        String query = "delete from challenge where id =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idChl);
            ps.executeUpdate();
            System.out.println("challenge supprimé");
            return true;
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateChallenge(Challenge chl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Challenge> displayChallenge() {
        ArrayList<Challenge> liste = new ArrayList<Challenge>();

        String query = "select * from challenge";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(query);

            while (resultat.next()) {
                Challenge ch = new Challenge();
                ch.setIdChallenge(resultat.getInt(1));
                ch.setIdOrganisation(resultat.getInt(2));
                ch.setNom(resultat.getString(3));
                ch.setDescription(resultat.getString(4));
                ch.setTheme(resultat.getString(5));
                ch.setDateChallenge(resultat.getDate(6));

                liste.add(ch);
            }
            return liste;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Challenge> displayChallengeByOrganisation(int idChl) {
        ArrayList<Challenge> liste = new ArrayList<Challenge>();

        String query = "select * from challenge where id_organisation=?";

        try {
            PreparedStatement pSt = connection.prepareStatement(query);
           pSt.setInt(1, idChl);
            ResultSet resultat = pSt.executeQuery();

            while (resultat.next()) {
                Challenge ch = new Challenge();
                ch.setIdChallenge(resultat.getInt(1));
                ch.setIdOrganisation(resultat.getInt(2));
                ch.setNom(resultat.getString(3));
                ch.setDescription(resultat.getString(4));
                ch.setTheme(resultat.getString(5));
                ch.setDateChallenge(resultat.getDate(6));

                liste.add(ch);
            }
            return liste;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Challenge getChallengeByid(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Challenge getChallengeByNom(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Challenge> displayChallengeByFormateur(int idChl) {
        ArrayList<Challenge> liste = new ArrayList<Challenge>();
 java.util.Date date_util = new java.util.Date();
  java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        String query = "select * from challenge where id_utilisateur=? and date >?";

        try {
       PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idChl);
             ps.setDate(2,date_sql );
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Challenge ch = new Challenge();
               ch.setIdChallenge(resultat.getInt(1));
                ch.setIdOrganisation(resultat.getInt(2));
                ch.setNom(resultat.getString(3));
                ch.setDescription(resultat.getString(4));
                ch.setTheme(resultat.getString(5));
                ch.setDateChallenge(resultat.getDate(6));
                ch.setIdUtilisateur(resultat.getInt(7));
                ch.setDurée(resultat.getInt(8));

                liste.add(ch);
            }
            return liste;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            return null;
        }
    }
        @Override
    public List<Challenge> displayChallengeByDate(Date date,int id) {
        ArrayList<Challenge> liste = new ArrayList<Challenge>();

        String query = "select * from challenge where  date ='" +date+" 00:00:00' and id IN(select id_challenge from inscription_challenge where id_utilisateur=?)";

        try {
       PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
           
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Challenge ch = new Challenge();
               ch.setIdChallenge(resultat.getInt(1));
                ch.setIdOrganisation(resultat.getInt(2));
                ch.setNom(resultat.getString(3));
                ch.setDescription(resultat.getString(4));
                ch.setTheme(resultat.getString(5));
                ch.setDateChallenge(resultat.getDate(6));
                ch.setIdUtilisateur(resultat.getInt(7));
                ch.setDurée(resultat.getInt(8));

                liste.add(ch);
            }
            return liste;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            return null;
        }
    }
 

    @Override
    public List<Challenge>  getChallengeByNomOrganisation(String nom,int id) {
          ArrayList<Challenge> liste = new ArrayList<Challenge>();
       
 java.util.Date date_util = new java.util.Date();
  java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        String query = "select * from challenge,organisation where organisation.id = challenge.id_organisation and organisation.nom=? and challenge.id NOT IN(select id_challenge from inscription_challenge where id_utilisateur=?)  and date >?";

        try {
       PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nom);
            ps.setInt(2, id);
             ps.setDate(3,date_sql );
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Challenge ch = new Challenge();
               ch.setIdChallenge(resultat.getInt(1));
                ch.setIdOrganisation(resultat.getInt(2));
                ch.setNom(resultat.getString(3));
                ch.setDescription(resultat.getString(4));
                ch.setTheme(resultat.getString(5));
                ch.setDateChallenge(resultat.getDate(6));
                ch.setIdUtilisateur(resultat.getInt(7));
                ch.setDurée(resultat.getInt(8));
        liste.add(ch);
               
            }
            return liste;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            return liste;
        }  
    }
    @Override
    public List<Challenge> displayChallengeUtilisateur(int id) {
        ArrayList<Challenge> liste = new ArrayList<Challenge>();
java.util.Date date_util = new java.util.Date();
  java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        String query = "select * from challenge where id IN(select id_challenge from inscription_challenge where id_utilisateur=?) and  date >?";

        try {
          PreparedStatement ps = connection.prepareStatement(query);
             ps.setInt(1, id);
             ps.setDate(2,date_sql );
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Challenge ch = new Challenge();
                ch.setIdChallenge(resultat.getInt(1));
                ch.setIdOrganisation(resultat.getInt(2));
                ch.setNom(resultat.getString(3));
                ch.setDescription(resultat.getString(4));
                ch.setTheme(resultat.getString(5));
                ch.setDateChallenge(resultat.getDate(6));
                ch.setIdUtilisateur(resultat.getInt(7));
                ch.setDurée(resultat.getInt(8));
                liste.add(ch);
            }
            return liste;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Challenge> displayChallenge(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
