/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.database.ArrayToString;
import com.models.daos.interfaces.IApprenantDAO;
import com.database.DataSource;
import com.models.entities.Apprenant;
import com.models.entities.ScoreUtilisateur;
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
public class ImplAppreantDAO implements IApprenantDAO {

    @Override
    public boolean ajouterApprenant(Apprenant apprenant) throws SQLException {

        Connection connection = DataSource.getInstance().getConnection();

        String requete = "insert into utilisateur (pseudo,mdp,nom,prenom,date_naissance,telephone,adresse,roles,mail,photo,score,username_canonical,email_canonical,enabled,salt,locked,expired,credentials_expired) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, apprenant.getNomUtilisateur());
        ps.setString(2, apprenant.getMotDePass()+"{nosalt}");
        ps.setString(3, apprenant.getNom());
        ps.setString(4, apprenant.getPrenom());
        ps.setDate(5, apprenant.getDateNaissance());
        ps.setInt(6, apprenant.getTel());
        ps.setString(7, apprenant.getAdresse());
        ps.setString(8, ArrayToString.EnumToArray(Role.APR));
        ps.setString(9, apprenant.getMail());
        ps.setString(10, apprenant.getPhoto().substring(apprenant.getPhoto().lastIndexOf("\\")+1));
        ps.setInt(11, apprenant.getScore());

        ps.setString(12, apprenant.getNomUtilisateur());
        ps.setString(13, apprenant.getMail());
        ps.setInt(14, 1);
        ps.setString(15, "nosalt");
        ps.setInt(16, 0);
        ps.setInt(17, 0);
        ps.setInt(18, 0);

        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;

    }

    @Override
    public Apprenant getApprenantByLogin(String login) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from utilisateur where pseudo like ?";
        PreparedStatement ps = connection.prepareCall(requete);
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        Apprenant apprenant = new Apprenant();
        while (rs.next()) {

            apprenant.setIdUtilisateur(rs.getInt(1));

            apprenant.setNomUtilisateur(rs.getString(3));
            apprenant.setMotDePass(rs.getString(4));
            apprenant.setNom(rs.getString(5));
            apprenant.setPrenom(rs.getString(6));
            apprenant.setDateNaissance(rs.getDate(7));
            apprenant.setTel(rs.getInt(8));
            apprenant.setAdresse(rs.getString(9));
            apprenant.setMail(rs.getString(10));
            apprenant.setPhoto(rs.getString(11));
            apprenant.setScore(rs.getInt(13));

        }
        ps.close();
        return apprenant;

    }

    @Override
    public boolean supprimerApprennantByLogin(String login) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "delete from utilisateur where pseudo like ?";
        PreparedStatement ps = connection.prepareCall(requete);
        ps.setString(1, login);
        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;

    }

    @Override
    public List<Apprenant> getAllApprenants() throws SQLException {
        List<Apprenant> list = new ArrayList<>();
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from utilisateur";
        Statement ps = connection.createStatement();

        ResultSet rs = ps.executeQuery(requete);
        Apprenant apprenant = new Apprenant();
        while (rs.next()) {

            apprenant.setIdUtilisateur(rs.getInt("id"));
            apprenant.setNomUtilisateur(rs.getString("pseudo"));
            apprenant.setMotDePass(rs.getString("mdp"));
            apprenant.setNom(rs.getString("nom"));
            apprenant.setPrenom(rs.getString("prenom"));
            apprenant.setDateNaissance(rs.getDate("date_naissance"));
            apprenant.setTel(rs.getInt("telephone"));
            apprenant.setAdresse(rs.getString("adresse"));
            apprenant.setMail(rs.getString("mail"));
            apprenant.setPhoto(rs.getString("photo"));
            apprenant.setScore(rs.getInt("score"));
            list.add(apprenant);
        }
        ps.close();
        return list;
    }

    @Override
    public boolean modifierApprenant(String login, Apprenant newApprenant) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();

        String requete = "update utilisateur set pseudo=?,mdp=?,nom=?,prenom=?,date_naissance=?,telephone=?,adresse=?,mail=?,photo=?,roles=?,score=? where pseudo like ?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, newApprenant.getNomUtilisateur());
        ps.setString(2, newApprenant.getMotDePass());
        ps.setString(3, newApprenant.getNom());
        ps.setString(4, newApprenant.getPrenom());
        ps.setDate(5, newApprenant.getDateNaissance());
        ps.setInt(6, newApprenant.getTel());
        ps.setString(7, newApprenant.getAdresse());
        ps.setString(8, ArrayToString.EnumToArray(Role.APR));
        ps.setString(9, newApprenant.getMail());
        ps.setString(10, newApprenant.getPhoto());
        ps.setInt(11, newApprenant.getScore());
        ps.setString(12, login);
        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;
    }

    @Override
    public Apprenant getApprenantsById(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ScoreUtilisateur> getTopUtilisateur(int id) throws SQLException {
        List<ScoreUtilisateur> list = new ArrayList<>();
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select u.nom, \n"
                + "(select count(*) from session_cours se where u.id=se.id_utilisateur and se.id_cours in (select id from cours where id_utilisateur=?)) as nbr\n"
                + "FROM utilisateur u  group by nbr having (nbr>0) order by nbr desc";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ScoreUtilisateur apprenant = new ScoreUtilisateur();
            apprenant.setNom(rs.getString(1));
            apprenant.setNumbreOfSubs(new Long(new Integer(rs.getInt(2)).toString()));

            list.add(apprenant);
        }
        ps.close();
        return list;
    }

}
