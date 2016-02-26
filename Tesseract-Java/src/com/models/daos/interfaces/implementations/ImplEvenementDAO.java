/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.IChallengeDAO;
import com.models.daos.interfaces.IEvenementDAO;
import com.database.DataSource;
import com.models.entities.Evenement;
import com.models.entities.Organisation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author haikal
 */
public class ImplEvenementDAO implements IEvenementDAO {

    private Connection connection;

    public ImplEvenementDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean addEvenement(Evenement evn) throws SQLException {
        String query = "Insert into evenement(`id`, `id_organisation`, `nom`, `description` , `nbr_max` , `affiche` , `date`) "
                + "values (NULL, ?, ?, ? ,? ,?,?);";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);

            pSt.setInt(1, evn.getIdOrganisation());
            pSt.setString(2, evn.getNom());
            pSt.setString(3, evn.getDescription());
            pSt.setInt(4, evn.getNbrMax());
            pSt.setString(5, evn.getAffiche());
            pSt.setDate(6, evn.getDateEvenement());
            pSt.executeUpdate();
            return true;
        } catch (SQLException ex) {

            return false;
        }
    }

    @Override
    public boolean deleteEvenement(int idEvn) throws SQLException {
        String query = "delete from evenement where id =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idEvn);
            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {

            return false;
        }
    }

    @Override
    public boolean updateEvenement(Evenement evn) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evenement> displayEvenement() throws SQLException{
        ArrayList<Evenement> liste = new ArrayList<Evenement>();

        String query = "select * from evenement";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(query);

            while (resultat.next()) {
                Evenement evn = new Evenement();
                evn.setIdEvenement(resultat.getInt(1));
                evn.setIdOrganisation(resultat.getInt(2));
                evn.setNom(resultat.getString(3));
                evn.setDescription(resultat.getString(4));
                evn.setNbrMax(resultat.getInt(5));
                evn.setAffiche(resultat.getString(6));
                evn.setDateEvenement(resultat.getDate(7));

                liste.add(evn);
            }
            return liste;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Evenement> displayByIdOrganisation(int idOrg) throws SQLException {
        ArrayList<Evenement> liste = new ArrayList<>();

        String query = "select * from evenement where id_organisation = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idOrg);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Evenement evn = new Evenement();
                evn.setIdEvenement(resultat.getInt(1));
                evn.setIdOrganisation(resultat.getInt(2));
                evn.setNom(resultat.getString(3));
                evn.setDescription(resultat.getString(4));
                evn.setNbrMax(resultat.getInt(5));
                evn.setAffiche(resultat.getString(6));
                evn.setDateEvenement(resultat.getDate(7));

                liste.add(evn);
            }
            return liste;
        } catch (SQLException ex) {

            return null;
        }
    }

    @Override
    public Evenement getEvenementByid(int id)throws SQLException {

        String query = "select * from evenement where id = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet resultat = ps.executeQuery();

        Evenement evn = new Evenement();
        evn.setIdEvenement(resultat.getInt(1));
        evn.setIdOrganisation(resultat.getInt(2));
        evn.setNom(resultat.getString(3));
        evn.setDescription(resultat.getString(4));
        evn.setNbrMax(resultat.getInt(5));
        evn.setAffiche(resultat.getString(6));
        evn.setDateEvenement(resultat.getDate(7));

        return evn;
    
   
    
     
}

@Override
        public Evenement getEvenementByNom(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    }
