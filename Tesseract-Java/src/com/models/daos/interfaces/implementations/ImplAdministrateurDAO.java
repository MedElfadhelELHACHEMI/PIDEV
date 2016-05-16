/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.database.ArrayToString;
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
        String requete = "insert into utilisateur (pseudo,mdp,nom,prenom,date_naissance,telephone,adresse,mail,photo,roles,mail_sercours,username_canonical,email_canonical,enabled,salt,locked,expired,credentials_expired) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(requete);
       
        ps.setString(1, administrateur.getNomUtilisateur());
        ps.setString(2, administrateur.getMotDePass());
        ps.setString(3, administrateur.getNom());
        ps.setString(4, administrateur.getPrenom());
        ps.setDate(5, administrateur.getDateNaissance());
        ps.setInt(6, administrateur.getTel());
        ps.setString(7, administrateur.getAdresse());
        ps.setString(8, ArrayToString.EnumToArray(administrateur.getRole()));
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

          administrateur.setIdUtilisateur(rs.getInt("id"));
            administrateur.setNomUtilisateur(rs.getString("pseudo"));
            administrateur.setMotDePass(rs.getString("mdp"));
            administrateur.setNom(rs.getString("nom"));
            administrateur.setPrenom(rs.getString("prenom"));
            administrateur.setDateNaissance(rs.getDate("date_naissance"));
            administrateur.setTel(rs.getInt("telephone"));
            administrateur.setAdresse(rs.getString("adresse"));
            administrateur.setMail(rs.getString("mail"));
            administrateur.setPhoto(rs.getString("photo"));
            administrateur.setMailSecours(rs.getString("mail_sercours"));

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
            administrateur.setIdUtilisateur(rs.getInt("id"));
            administrateur.setNomUtilisateur(rs.getString("pseudo"));
            administrateur.setMotDePass(rs.getString("mdp"));
            administrateur.setNom(rs.getString("nom"));
            administrateur.setPrenom(rs.getString("prenom"));
            administrateur.setDateNaissance(rs.getDate("date_naissance"));
            administrateur.setTel(rs.getInt("telephone"));
            administrateur.setAdresse(rs.getString("adresse"));
            administrateur.setMail(rs.getString("mail"));
            administrateur.setPhoto(rs.getString("photo"));
            administrateur.setMailSecours(rs.getString("mail_sercours"));
            list.add(administrateur);
        }
        ps.close();
        return list;
    }

    @Override
    public boolean modifierAdministrateur(String login, Administrateur newAdministrateur) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();

        String requete = "update utilisateur set pseudo=?,mdp=?,nom=?,prenom=?,date_naissance=?,telephone=?,adresse=?,mail=?,photo=?,roles=?,mail_secours=? where pseudo like ?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, newAdministrateur.getNomUtilisateur());
        ps.setString(2, newAdministrateur.getMotDePass());
        ps.setString(3, newAdministrateur.getNom());
        ps.setString(4, newAdministrateur.getPrenom());
        ps.setDate(5, newAdministrateur.getDateNaissance());
        ps.setInt(6, newAdministrateur.getTel());
        ps.setString(7, newAdministrateur.getAdresse());
        ps.setString(8, ArrayToString.EnumToArray(Role.ADM));
        ps.setString(9, newAdministrateur.getMail());
        ps.setString(10, newAdministrateur.getPhoto());
        ps.setString(11, newAdministrateur.getMailSecours());
        ps.setString(12, login);
        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;
    }

}
