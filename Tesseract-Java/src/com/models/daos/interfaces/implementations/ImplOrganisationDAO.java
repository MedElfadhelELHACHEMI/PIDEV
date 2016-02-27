/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.IOrganisationDAO;
import com.database.DataSource;
import com.models.entities.Challenge;
import com.models.entities.Organisation;
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
 * @author BoB
 */
public class ImplOrganisationDAO implements IOrganisationDAO {

    private Connection connection;

    public ImplOrganisationDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean addOrganisation(Organisation org) {
        String query = "Insert into organisation(`id`, `nom`, `adresse`,`email`,`matricule` , `photo` ) "
                + "values (NULL, ?, ?, ?, ? ,? );";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
           
            pSt.setString(2, org.getNom());
            pSt.setString(3, org.getAdresse());
            pSt.setString(4, org.geteMail());
            pSt.setString(5, org.getMatricule());
            pSt.setString(6, org.getPhoto());
            pSt.executeUpdate();
            return true;
        } catch (SQLException ex) {
           ex.getStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteOrganisation(int idOrg) {
        String query = "delete from organisation where id =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idOrg);
            ps.executeUpdate();
            System.out.println("organisation supprimé");
            return true;
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateOrganisation(Organisation org) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organisation> displayOrganisation() {
        ArrayList<Organisation> liste = new ArrayList<Organisation>();

        String query = "select * from Organisation";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(query);

            while (resultat.next()) {
                Organisation og = new Organisation();
                og.setIdOrganisation(resultat.getInt(1));
                og.setNom(resultat.getString(2));
                og.setAdresse(resultat.getString(3));
                og.setMatricule(resultat.getString(4));
                og.setPhoto(resultat.getString(5));

                liste.add(og);
            }
            return liste;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Organisation getOrganisationByid(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organisation getOrganisationByNom(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getOrganisationByMatriculeNom(String oxia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
