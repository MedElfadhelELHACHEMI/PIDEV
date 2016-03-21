/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.ILogDAO;
import com.database.DataSource;
import com.models.entities.Log;
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
public class ImplLogDAO implements ILogDAO{
    
    private Connection connection;

    public ImplLogDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean ajouterLog(Log Log) {
        String query = "Insert into log(`id`, `id_utilisateur`, `tache`, `date`) "
                + "values (NULL, ?, ?, ?);";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            pSt.setInt(1, Log.getIdUtilisateur());
            pSt.setString(2, Log.getTache());
            pSt.setDate(3, (Date) Log.getDateTache());
            pSt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Log non ajouté");
            return false;
        }
    }

    @Override
    public boolean supprimerLog(int id) {
        String query="delete from log where id =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Log supprimé");
            return true;
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
            return false;
        }
    }


    @Override
    public List<Log> getallLog() {
        ArrayList<Log> listeLog = new ArrayList<Log>();
        
        String query = "select * from log";
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(query);

            while (resultat.next()) {
                Log log = new Log();                
                log.setIdLog(resultat.getInt(1));
                log.setIdUtilisateur(resultat.getInt(2));
                log.setTache(resultat.getString(3));
                log.setDateTache(resultat.getDate(4));

                listeLog.add(log);
            }
            return listeLog;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            return null;
        }
        
    }

    @Override
    public Log getLogbyid(int id) {
        Log log = new Log();
        String query = "select * from log where id=?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                log.setIdLog(resultat.getInt(1));
                log.setIdUtilisateur(resultat.getInt(2));
                log.setTache(resultat.getString(3));
                log.setDateTache(resultat.getDate(4));
            }
            return log;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du log " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Log> getLogbyuser(int idUser) {
        ArrayList<Log> listeLog = new ArrayList<Log>();
        String query = "select * from log where id_utilisateur=?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idUser);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Log log = new Log();
                log.setIdLog(resultat.getInt(1));
                log.setIdUtilisateur(resultat.getInt(2));
                log.setTache(resultat.getString(3));
                log.setDateTache((Date)resultat.getDate(4));
                listeLog.add(log);
            }
            return listeLog;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du log " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Log> getLogbytache(String Tache) {
        ArrayList<Log> listeLog = new ArrayList<Log>();
        String query = "select * from log where tache=?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Tache);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Log log = new Log();
                log.setIdLog(resultat.getInt(1));
                log.setIdUtilisateur(resultat.getInt(2));
                log.setTache(resultat.getString(3));
                log.setDateTache(resultat.getDate(4));
                listeLog.add(log);
            }
            return listeLog;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du log " + ex.getMessage());
            return null;
        }
    }
    
}
