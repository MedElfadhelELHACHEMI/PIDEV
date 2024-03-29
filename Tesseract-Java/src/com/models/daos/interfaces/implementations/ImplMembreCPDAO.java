/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.database.ArrayToString;
import com.models.daos.interfaces.IMembreCPDAO;
import com.database.DataSource;

import com.models.entities.MembreCP;
import com.models.enums.Etat;
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
public class ImplMembreCPDAO implements IMembreCPDAO {

    @Override
    public boolean ajouterMembreCP(MembreCP MembreCP) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();

        String requete = "insert into utilisateur (pseudo,mdp,nom,prenom,date_naissance,telephone,adresse,mail,photo,roles,username_canonical,email_canonical,enabled,salt,locked,expired,credentials_expired) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, MembreCP.getNomUtilisateur());
        ps.setString(2, MembreCP.getMotDePass()+"{nosalt}");
        ps.setString(3, MembreCP.getNom());
        ps.setString(4, MembreCP.getPrenom());
        ps.setDate(5, MembreCP.getDateNaissance());
        ps.setInt(6, MembreCP.getTel());
        ps.setString(7, MembreCP.getAdresse());

        ps.setString(8, MembreCP.getMail());
        ps.setString(9, MembreCP.getPhoto());
        ps.setString(10, ArrayToString.EnumToArray(Role.MCP));

        ps.setString(11, MembreCP.getNomUtilisateur());
        ps.setString(12, MembreCP.getMail());
        ps.setInt(13, 1);
        ps.setString(14, "nosalt");
        ps.setInt(15, 0);
        ps.setInt(16, 0);
        ps.setInt(17, 0);

        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;
    }

    @Override
    public MembreCP getMembreCPByLogin(String login) throws SQLException {

        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from utilisateur where pseudo like ?";
        PreparedStatement ps = connection.prepareCall(requete);
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        MembreCP membreCP = new MembreCP();
        while (rs.next()) {

            membreCP.setIdUtilisateur(rs.getInt("id"));
            membreCP.setNomUtilisateur(rs.getString("pseudo"));
            membreCP.setMotDePass(rs.getString("mdp"));
            membreCP.setNom(rs.getString("nom"));
            membreCP.setPrenom(rs.getString("prenom"));
            membreCP.setDateNaissance(rs.getDate("date_naissance"));
            membreCP.setTel(rs.getInt("telephone"));
            membreCP.setAdresse(rs.getString("adresse"));
            membreCP.setMail(rs.getString("mail"));
            membreCP.setPhoto(rs.getString("photo"));

        }
        ps.close();
        return membreCP;
    }

    @Override
    public boolean supprimerMembreCPByLogin(String login) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "delete from utilisateur where pseudo like ?";
        PreparedStatement ps = connection.prepareCall(requete);
        ps.setString(1, login);
        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;
    }

    @Override
    public List<MembreCP> getAllMembreCPs() throws SQLException {

        List<MembreCP> list = new ArrayList<>();
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from utilisateur";
        Statement ps = connection.createStatement();

        ResultSet rs = ps.executeQuery(requete);
        MembreCP membreCP = new MembreCP();
        while (rs.next()) {

            membreCP.setIdUtilisateur(rs.getInt(1));
            membreCP.setNomUtilisateur(rs.getString(3));
            membreCP.setMotDePass(rs.getString(4));
            membreCP.setNom(rs.getString(5));
            membreCP.setPrenom(rs.getString(6));
            membreCP.setDateNaissance(rs.getDate(7));
            membreCP.setTel(rs.getInt(8));
            membreCP.setAdresse(rs.getString(9));
            membreCP.setMail(rs.getString(10));
            membreCP.setPhoto(rs.getString(11));

            list.add(membreCP);
        }
        ps.close();
        return list;
    }

    @Override
    public boolean modifierMembreCP(String login, MembreCP newMembreCP) throws SQLException {

        Connection connection = DataSource.getInstance().getConnection();

        String requete = "update utilisateur set pseudo=?,mdp=?,nom=?,prenom=?,date_naissance=?,telephone=?,adresse=?,mail=?,photo=?,roles=? where pseudo like ?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, newMembreCP.getNomUtilisateur());
        ps.setString(2, newMembreCP.getMotDePass());
        ps.setString(3, newMembreCP.getNom());
        ps.setString(4, newMembreCP.getPrenom());
        ps.setDate(5, newMembreCP.getDateNaissance());
        ps.setInt(6, newMembreCP.getTel());
        ps.setString(7, newMembreCP.getAdresse());
        ps.setString(8, ArrayToString.EnumToArray(Role.MCP));
        ps.setString(9, newMembreCP.getMail());
        ps.setString(10, newMembreCP.getPhoto());

        ps.setString(11, login);
        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;
    }

}
