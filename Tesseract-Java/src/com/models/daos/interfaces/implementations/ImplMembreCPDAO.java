/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.IMembreCPDAO;
import com.database.DataSource;

import com.models.entities.MembreCP;
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

        String requete = "insert into utilisateur (pseudo,mdp,nom,prenom,date_naissance,telephone,adresse,mail,photo,role) values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, MembreCP.getNomUtilisateur());
        ps.setString(2, MembreCP.getMotDePass());
        ps.setString(3, MembreCP.getNom());
        ps.setString(4, MembreCP.getPrenom());
        ps.setDate(5, MembreCP.getDateNaissance());
        ps.setInt(6, MembreCP.getTel());
        ps.setString(7, MembreCP.getAdresse());

        ps.setString(8, MembreCP.getMail());
        ps.setString(9, MembreCP.getPhoto());
        ps.setString(10, String.valueOf(Role.MCP));
        System.out.println(MembreCP.getNomUtilisateur());
        System.out.println(MembreCP.getMotDePass());
        System.out.println(MembreCP.getNom());
        System.out.println(MembreCP.getPrenom());
        System.out.println(MembreCP.getDateNaissance());
        System.out.println(MembreCP.getTel());
        System.out.println(MembreCP.getAdresse());

        System.out.println(MembreCP.getMail());
        System.out.println(MembreCP.getPhoto());
        System.out.println(String.valueOf(Role.MCP));
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

        String requete = "update utilisateur set pseudo=?,mdp=?,nom=?,prenom=?,date_naissance=?,telephone=?,adresse=?,mail=?,photo=?,role=? where pseudo like ?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, newMembreCP.getNomUtilisateur());
        ps.setString(2, newMembreCP.getMotDePass());
        ps.setString(3, newMembreCP.getNom());
        ps.setString(4, newMembreCP.getPrenom());
        ps.setDate(5, newMembreCP.getDateNaissance());
        ps.setInt(6, newMembreCP.getTel());
        ps.setString(7, newMembreCP.getAdresse());
        ps.setString(8, String.valueOf(Role.MCP));
        ps.setString(9, newMembreCP.getMail());
        ps.setString(10, newMembreCP.getPhoto());

        ps.setString(11, login);
        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;
    }

}
