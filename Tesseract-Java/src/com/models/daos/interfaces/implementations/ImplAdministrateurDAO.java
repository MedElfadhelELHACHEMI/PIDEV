/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.IAdministrateurDAO;
import com.database.DataSource;
import com.models.entities.Administrateur;
import com.models.entities.Apprenant;
import com.models.enums.Role;
import java.sql.Connection;
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
public class ImplAdministrateurDAO implements IAdministrateurDAO {

    @Override
    public boolean ajouterAdministrateur(Administrateur administrateur) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();
        System.out.println(administrateur.getNomUtilisateur());
        String requete = "insert into utilisateur (pseudo,mdp,nom,prenom,date_naissance,telephone,adresse,mail,photo,role,mail_sercours) values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, administrateur.getNomUtilisateur());
        ps.setString(2, administrateur.getMotDePass());
        ps.setString(3, administrateur.getNom());
        ps.setString(4, administrateur.getPrenom());
        ps.setDate(5, administrateur.getDateNaissance());
        ps.setInt(6, administrateur.getTel());
        ps.setString(7, administrateur.getAdresse());
        ps.setString(8, String.valueOf(Role.ADM));
        ps.setString(9, administrateur.getMail());
        ps.setString(10, administrateur.getPhoto());
        ps.setString(11, administrateur.getMailSecours());

        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;
    }

    @Override
    public Administrateur getAdministrateurByLogin(String login) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from utilisateur where pseudo like ?";
        PreparedStatement ps = connection.prepareCall(requete);
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        Administrateur administrateur = new Administrateur();
        while (rs.next()) {

            administrateur.setIdUtilisateur(rs.getInt(1));
            administrateur.setNomUtilisateur(rs.getString(3));
            administrateur.setMotDePass(rs.getString(4));
            administrateur.setNom(rs.getString(5));
            administrateur.setPrenom(rs.getString(6));
            administrateur.setDateNaissance(rs.getDate(7));
            administrateur.setTel(rs.getInt(8));
            administrateur.setAdresse(rs.getString(9));
            administrateur.setMail(rs.getString(10));
            administrateur.setPhoto(rs.getString(11));
            administrateur.setMailSecours(rs.getString(16));

        }
        ps.close();
        return administrateur;
    }

    @Override
    public boolean supprimerAdministrateurByLogin(String login) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "delete from utilisateur where pseudo like ?";
        PreparedStatement ps = connection.prepareCall(requete);
        ps.setString(1, login);
        int reslt = ps.executeUpdate();
        ps.close();
        return reslt == 1;
    }

    @Override
    public List<Administrateur> getAllAdministrateurs() throws SQLException {
        List<Administrateur> list = new ArrayList<>();
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from utilisateur";
        Statement ps = connection.createStatement();

        ResultSet rs = ps.executeQuery(requete);
        Administrateur administrateur = new Administrateur();
        while (rs.next()) {

            administrateur.setIdUtilisateur(rs.getInt(1));
            administrateur.setNomUtilisateur(rs.getString(3));
            administrateur.setMotDePass(rs.getString(4));
            administrateur.setNom(rs.getString(5));
            administrateur.setPrenom(rs.getString(6));
            administrateur.setDateNaissance(rs.getDate(7));
            administrateur.setTel(rs.getInt(8));
            administrateur.setAdresse(rs.getString(9));
            administrateur.setMail(rs.getString(10));
            administrateur.setPhoto(rs.getString(11));
            administrateur.setMailSecours(rs.getString(16));
            list.add(administrateur);
        }
        ps.close();
        return list;
    }

    @Override
    public boolean modifierAdministrateur(String login, Administrateur newAdministrateur) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();

        String requete = "update utilisateur set pseudo=?,mdp=?,nom=?,prenom=?,date_naissance=?,telephone=?,adresse=?,mail=?,photo=?,role=?,mail_secours=? where pseudo like ?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, newAdministrateur.getNomUtilisateur());
        ps.setString(2, newAdministrateur.getMotDePass());
        ps.setString(3, newAdministrateur.getNom());
        ps.setString(4, newAdministrateur.getPrenom());
        ps.setDate(5, newAdministrateur.getDateNaissance());
        ps.setInt(6, newAdministrateur.getTel());
        ps.setString(7, newAdministrateur.getAdresse());
        ps.setString(8, String.valueOf(Role.ADM));
        ps.setString(9, newAdministrateur.getMail());
        ps.setString(10, newAdministrateur.getPhoto());
        ps.setString(11, newAdministrateur.getMailSecours());
        ps.setString(12, login);
        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;
    }

}
