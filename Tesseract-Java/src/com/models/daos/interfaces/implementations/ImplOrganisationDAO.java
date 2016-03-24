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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String query = "insert into organisation( nom, adress,email,matricule , photo ) values ( ?, ?, ?, ? ,? )";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            System.out.println(org);
            pSt.setString(1, org.getNom());
            pSt.setString(2, org.getAdresse());
            pSt.setString(3, org.geteMail());
            pSt.setString(4, org.getMatricule());
            pSt.setString(5, org.getPhoto());
            pSt.execute();
            return true;
        } catch (SQLException ex) {

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
                og.seteMail(resultat.getString(4));
                og.setMatricule(resultat.getString(5));
                og.setPhoto(resultat.getString(6));

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
      
        String query = "select * from organisation where id =?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultat = statement.executeQuery();
            while (resultat.next()) {
                Organisation og = new Organisation();
             og.setIdOrganisation(resultat.getInt(1));
                og.setNom(resultat.getString(2));
                og.setAdresse(resultat.getString(3));
                og.seteMail(resultat.getString(4));
                og.setMatricule(resultat.getString(5));
                og.setPhoto(resultat.getString(6));
             
                return og;
            }

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            return new Organisation();
        }
        return new Organisation();
    }

    @Override
    public Organisation getOrganisationByNom(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getOrganisationByMatriculeNom(String oxia
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCountCoachOrganis(int idOrganisation) {

        try {
            String query = "select count(*) from utilisateur where id_organisation = ?";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idOrganisation);
            ResultSet resultat = statement.executeQuery();
            while (resultat.next()) {                
                return resultat.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ImplOrganisationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 0 ;
    }

}
