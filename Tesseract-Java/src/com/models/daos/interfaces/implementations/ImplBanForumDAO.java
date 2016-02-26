/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.IBanForumDAO;
import com.database.DataSource;
import com.models.entities.BanForum;
import com.models.entities.Log;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ImplBanForumDAO implements IBanForumDAO{
    
    private Connection connection;

    public ImplBanForumDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean setBanForum(BanForum banforum) {
        String query = "Insert into ban_forum(`id`, `id_utilisateur`, `cause`, `date`,`duree`) "
                + "values (NULL, ?, ?, ?, ?);";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            pSt.setInt(1, banforum.getIdUtilisateur());
            pSt.setString(2, banforum.getCause());
            pSt.setDate(3, (Date) banforum.getDateBan());
            pSt.setInt(4,banforum.getDuree());
            pSt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("banforum non ajouté");
            return false;
        }
    }

    @Override
    public boolean supprimerBanForum(int idBan) {
         String query="delete from ban_forum where id =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idBan);
            ps.executeUpdate();
            System.out.println("ban supprimé");
            return true;
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean modifierBanForum(BanForum banforum) {
        String query="update ban_forum set id_utilisateur=?,cause=?,date=?,duree=? where id=?";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            pSt.setInt(1, banforum.getIdUtilisateur());
            pSt.setString(2, banforum.getCause());
            pSt.setDate(3, (Date) banforum.getDateBan());
            pSt.setInt(4,banforum.getDuree());
            pSt.setInt(5,banforum.getIdBan());
            pSt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("banforum non modifié");
            return false;
        }
    }

    @Override
    public List<BanForum> getallBanForum() {
        ArrayList<BanForum> listeBanForum = new ArrayList<BanForum>();
        
        String query = "select * from ban_forum";
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(query);

            while (resultat.next()) {
                BanForum banforum = new BanForum();                
                banforum.setIdBan(resultat.getInt(1));
                banforum.setIdUtilisateur(resultat.getInt(2));
                banforum.setCause(resultat.getString(3));
                banforum.setDateBan(resultat.getDate(4));
                banforum.setDuree(resultat.getInt(5));

                listeBanForum.add(banforum);
            }
            return listeBanForum;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Bans Forum " + ex.getMessage());
            return null;
        }
    }

    @Override
    public BanForum getBanForumbyid(int id) {
         BanForum banforum = new BanForum();
        String query = "select * from ban_forum where id=?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                banforum.setIdBan(resultat.getInt(1));
                banforum.setIdUtilisateur(resultat.getInt(2));
                banforum.setCause(resultat.getString(3));
                banforum.setDateBan(resultat.getDate(4));
                banforum.setDuree(resultat.getInt(5));
            }
            return banforum;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du ban " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<BanForum> getBanForumbyuser(int idUser) {
         ArrayList<BanForum> listeBanForum = new ArrayList<BanForum>();
        String query = "select * from ban_forum where id_utilisateur=?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idUser);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                BanForum banforum = new BanForum();
                banforum.setIdBan(resultat.getInt(1));
                banforum.setIdUtilisateur(resultat.getInt(2));
                banforum.setCause(resultat.getString(3));
                banforum.setDateBan(resultat.getDate(4));
                banforum.setDuree(resultat.getInt(5));
                listeBanForum.add(banforum);
            }
            return listeBanForum;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du log " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<BanForum> getBanForumbycause(String cause) {
        ArrayList<BanForum> listeBanForum = new ArrayList<BanForum>();
        String query = "select * from ban_forum where cause=?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, cause);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                BanForum banforum = new BanForum();
                banforum.setIdBan(resultat.getInt(1));
                banforum.setIdUtilisateur(resultat.getInt(2));
                banforum.setCause(resultat.getString(3));
                banforum.setDateBan(resultat.getDate(4));
                banforum.setDuree(resultat.getInt(5));
                listeBanForum.add(banforum);
            }
            return listeBanForum;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du log " + ex.getMessage());
            return null;
        }
    }
    
}
