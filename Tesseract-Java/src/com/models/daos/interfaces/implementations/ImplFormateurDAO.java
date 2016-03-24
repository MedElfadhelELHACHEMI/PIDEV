/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.IFormateurDAO;
import com.database.DataSource;
import com.models.entities.Apprenant;
import com.models.entities.Formateur;
import com.models.enums.Etat;
import com.models.enums.Role;
import java.sql.Connection;
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
public class ImplFormateurDAO implements IFormateurDAO {

    @Override
    public boolean ajouterFormateur(Formateur formateur, Integer idOrganisme) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();

        String requete = "insert into utilisateur (pseudo,mdp,nom,prenom,date_naissance,telephone,adresse,mail,photo,role,cv,etat) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(requete);

        ps.setString(1, formateur.getNomUtilisateur());
        ps.setString(2, formateur.getMotDePass());
        ps.setString(3, formateur.getNom());
        ps.setString(4, formateur.getPrenom());
        ps.setDate(5, formateur.getDateNaissance());
        ps.setInt(6, formateur.getTel());
        ps.setString(7, formateur.getAdresse());

        ps.setString(8, formateur.getMail());
        ps.setString(9, formateur.getPhoto());
        ps.setString(10, String.valueOf(Role.FOR));

        ps.setString(11, formateur.getCv());
        ps.setString(12, String.valueOf(Etat.ATT));
        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;
    }

    @Override
    public Formateur getFormateurByLogin(String login) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from utilisateur where pseudo like ?";
        PreparedStatement ps = connection.prepareCall(requete);
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        Formateur formateur = new Formateur();
        while (rs.next()) {

            formateur.setIdUtilisateur(rs.getInt(1));
            formateur.setNomUtilisateur(rs.getString(3));
            formateur.setMotDePass(rs.getString(4));
            formateur.setNom(rs.getString(5));
            formateur.setPrenom(rs.getString(6));
            formateur.setDateNaissance(rs.getDate(7));
            formateur.setTel(rs.getInt(8));
            formateur.setAdresse(rs.getString(9));
            formateur.setMail(rs.getString(10));
            formateur.setPhoto(rs.getString(11));
            formateur.setCv(rs.getString(14));
            formateur.setEtat(Etat.valueOf(rs.getString(15)));

        }
        ps.close();
        return formateur;
    }

    @Override
    public boolean supprimerFormateurByLogin(String login) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "delete from utilisateur where pseudo like ?";
        PreparedStatement ps = connection.prepareCall(requete);
        ps.setString(1, login);
        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;
    }

    @Override
    public List<Formateur> getAllFormateurs() throws SQLException {

        List<Formateur> list = new ArrayList<>();
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from utilisateur where role = 'FOR'";
        Statement ps = connection.createStatement();

        ResultSet rs = ps.executeQuery(requete);
        Formateur formateur = new Formateur();
        while (rs.next()) {

            formateur.setIdUtilisateur(rs.getInt(1));
            formateur.setNomUtilisateur(rs.getString(3));
            formateur.setMotDePass(rs.getString(4));
            formateur.setNom(rs.getString(5));
            formateur.setPrenom(rs.getString(6));
            formateur.setDateNaissance(rs.getDate(7));
            formateur.setTel(rs.getInt(8));
            formateur.setAdresse(rs.getString(9));
            formateur.setMail(rs.getString(10));
            formateur.setPhoto(rs.getString(11));
            formateur.setRole(Role.FOR);
            formateur.setCv(rs.getString(14));

            list.add(formateur);
        }
        ps.close();
        return list;
    }

    @Override
    public boolean modifierFormateur(String login, Formateur newFormateur) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();

        String requete = "update utilisateur set pseudo=?,mdp=?,nom=?,prenom=?,date_naissance=?,telephone=?,adresse=?,mail=?,photo=?,role=?,cv=?,etat=? where pseudo like ?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, newFormateur.getNomUtilisateur());
        ps.setString(2, newFormateur.getMotDePass());
        ps.setString(3, newFormateur.getNom());
        ps.setString(4, newFormateur.getPrenom());
        ps.setDate(5, newFormateur.getDateNaissance());
        ps.setInt(6, newFormateur.getTel());
        ps.setString(7, newFormateur.getAdresse());
        ps.setString(8, String.valueOf(Role.FOR));
        ps.setString(9, newFormateur.getMail());
        ps.setString(10, newFormateur.getPhoto());
        ps.setString(11, newFormateur.getCv());
        ps.setString(12, newFormateur.getEtat().name());
        ps.setString(13, login);
        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;
    }

    @Override
    public boolean affecterOrganismeFormateur(int i, int i0) {

        try {
            Connection connection = DataSource.getInstance().getConnection();
            String req = "update utilisateur set id_organisation =" + i0 + " where id =" + i + "";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            int res = preparedStatement.executeUpdate();
            preparedStatement.close();
            return res == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ImplInvitationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


@Override
        public Formateur getFormateurById(int i) {
        try {
            Connection connection = DataSource.getInstance().getConnection();
            String requete = "select * from utilisateur where id = ?";
            PreparedStatement ps = connection.prepareCall(requete);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            Formateur formateur = null;
            while (rs.next()) {
                formateur = new Formateur();
                formateur.setIdUtilisateur(rs.getInt(1));
                formateur.setIdOrganisationn(rs.getInt(2));
                formateur.setNomUtilisateur(rs.getString(3));
                formateur.setMotDePass(rs.getString(4));
                formateur.setNom(rs.getString(5));
                formateur.setPrenom(rs.getString(6));
                formateur.setDateNaissance(rs.getDate(7));
                formateur.setTel(rs.getInt(8));
                formateur.setAdresse(rs.getString(9));
                formateur.setMail(rs.getString(10));
                formateur.setPhoto(rs.getString(11));
                formateur.setCv(rs.getString(14));
                formateur.setRole(Role.valueOf(rs.getString(12)));

            }
            ps.close();
            return formateur;
        

} catch (SQLException ex) {
            Logger.getLogger(ImplFormateurDAO.class  

.getName()).log(Level.SEVERE, null, ex);
        }
        return new Formateur();
    }

    @Override
        public boolean modifierProfil(String nom, String prenom, String mail, String adresse, int tel, int id) {
        try {
            Connection connection = DataSource.getInstance().getConnection();

            String requete = "update utilisateur set nom=?,prenom=?,telephone=?,adresse=?,mail=? where id = ?";
            Formateur newFormateur = new Formateur();
            PreparedStatement ps = connection.prepareStatement(requete);

            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setInt(3, tel);
            ps.setString(4, adresse);
            ps.setString(5, mail);
            ps.setInt(6, id);
            int resultat = ps.executeUpdate();
            ps.close();
            return resultat == 1;
        

} catch (SQLException ex) {
            Logger.getLogger(ImplFormateurDAO.class  

.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
        public boolean modifierProfilWithPwd(String nom, String prenom, String mail, String adresse, int tel, String motDePass, int id) {
        try {
            Connection connection = DataSource.getInstance().getConnection();

            String requete = "update utilisateur set mdp=?,nom=?,prenom=?,telephone=?,adresse=?,mail=? where id = ?";
            Formateur newFormateur = new Formateur();
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, motDePass);
            ps.setString(2, nom);
            ps.setString(3, prenom);
            ps.setInt(4, tel);
            ps.setString(5, adresse);
            ps.setString(6, mail);
            ps.setInt(7, id);
            int resultat = ps.executeUpdate();
            ps.close();
            return resultat == 1;
        

} catch (SQLException ex) {
            Logger.getLogger(ImplFormateurDAO.class  

.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
        public List<Formateur> afficherTopFormateur() throws SQLException {

        List<Formateur> list = new ArrayList<>();
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "SELECT c.id,(select count(*) from cours s where s.id_utilisateur = c.id) as nbr from utilisateur c where c.role ='FOR' order by nbr desc ";
        Statement ps = connection.createStatement();

        ResultSet rs = ps.executeQuery(requete);

        while (rs.next()) {
            Formateur formateur = new Formateur();
            formateur.setIdUtilisateur(rs.getInt(1));

            formateur.setTel(rs.getInt(2));

            list.add(formateur);
        }
        ps.close();
        return list;
    }

}
