/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.database.ArrayToString;
import com.models.daos.interfaces.IFormateurDAO;
import com.database.DataSource;
import com.models.entities.Apprenant;
import com.models.entities.Cours;
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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author haikal
 */
public class ImplFormateurDAO implements IFormateurDAO {

    private Connection cnx;

    public ImplFormateurDAO() {
        cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean ajouterFormateur(Formateur formateur, Integer idOrganisme) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();

        String requete = "insert into utilisateur (pseudo,mdp,nom,prenom,date_naissance,telephone,adresse,mail,photo,roles,cv,etat,username_canonical,email_canonical,enabled,salt,locked,expired,credentials_expired) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(requete);

        ps.setString(1, formateur.getNomUtilisateur());
        ps.setString(2, formateur.getMotDePass() + "{nosalt}");
        ps.setString(3, formateur.getNom());
        ps.setString(4, formateur.getPrenom());
        ps.setDate(5, formateur.getDateNaissance());
        ps.setInt(6, formateur.getTel());
        ps.setString(7, formateur.getAdresse());

        ps.setString(8, formateur.getMail());
        ps.setString(9, formateur.getPhoto().substring(formateur.getPhoto().lastIndexOf("\\") + 1));
        ps.setString(10, String.valueOf(ArrayToString.EnumToArray(Role.FOR)));

        ps.setString(11, formateur.getCv());
        ps.setString(12, String.valueOf(Etat.ATT));
        ps.setString(13, formateur.getNomUtilisateur());
        ps.setString(14, formateur.getMail());
        ps.setInt(15, 1);
        ps.setString(16, "nosalt");
        ps.setInt(17, 0);
        ps.setInt(18, 0);
        ps.setInt(19, 0);

        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;
    }

    @Override
    public Formateur getFormateurByLogin(String login) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from utilisateur where nom like ?";
        PreparedStatement ps = connection.prepareCall(requete);
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        Formateur formateur = new Formateur();
        while (rs.next()) {
            formateur.setIdUtilisateur(rs.getInt("id"));
            formateur.setNomUtilisateur(rs.getString("pseudo"));
            formateur.setMotDePass(rs.getString("mdp"));
            formateur.setNom(rs.getString("nom"));
            formateur.setPrenom(rs.getString("prenom"));
            formateur.setDateNaissance(rs.getDate("date_naissance"));
            formateur.setTel(rs.getInt("telephone"));
            formateur.setAdresse(rs.getString("adresse"));
            formateur.setMail(rs.getString("mail"));
            formateur.setPhoto(rs.getString("photo"));
            formateur.setCv(rs.getString("cv"));
            formateur.setScore(rs.getInt("score"));
            formateur.setEtat(Etat.valueOf(rs.getString("etat")));

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
        String requete = "select * from utilisateur where roles = 'a:1:{i:0;s:8:\"ROLE_FOR\";}'";
        Statement ps = connection.createStatement();

        ResultSet rs = ps.executeQuery(requete);

        while (rs.next()) {
            Formateur formateur = new Formateur();
            formateur.setIdUtilisateur(rs.getInt("id"));
            formateur.setNomUtilisateur(rs.getString("pseudo"));
            formateur.setMotDePass(rs.getString("mdp"));
            formateur.setNom(rs.getString("nom"));
            formateur.setPrenom(rs.getString("prenom"));
            formateur.setDateNaissance(rs.getDate("date_naissance"));
            formateur.setTel(rs.getInt("telephone"));
            formateur.setAdresse(rs.getString("adresse"));
            formateur.setMail(rs.getString("mail"));
            formateur.setPhoto(rs.getString("photo"));
            formateur.setCv(rs.getString("cv"));
            formateur.setScore(rs.getInt("score"));
            formateur.setEtat(Etat.valueOf(rs.getString("etat")));
            list.add(formateur);
        }
        ps.close();
        return list;
    }

    @Override
    public boolean modifierFormateur(String login, Formateur newFormateur) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();

        String requete = "update utilisateur set pseudo=?,mdp=?,nom=?,prenom=?,date_naissance=?,telephone=?,adresse=?,mail=?,photo=?,roles=?,cv=?,etat=? where pseudo like ?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, newFormateur.getNomUtilisateur());
        ps.setString(2, newFormateur.getMotDePass());
        ps.setString(3, newFormateur.getNom());
        ps.setString(4, newFormateur.getPrenom());
        ps.setDate(5, newFormateur.getDateNaissance());
        ps.setInt(6, newFormateur.getTel());
        ps.setString(7, newFormateur.getAdresse());
        ps.setString(8, String.valueOf(ArrayToString.EnumToArray(Role.FOR)));
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
    public String getNameFormateur(Cours cours) throws SQLException {
        String name = null;
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select nom from utilisateur where id=?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1, cours.getIdFormateur());
        ResultSet resultat = ps.executeQuery();
        while (resultat.next()) {
            name = resultat.getString(1);
        }
        if (Objects.nonNull(name)) {
            return name;
        }

        throw new UnsupportedOperationException();

    }

    @Override
    public String getNameOrganisme(Cours cours) throws SQLException {
        String name = "";
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select organisation.nom from organisation ,utilisateur where utilisateur.id_organisation=organisation.id and utilisateur.id=?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1, cours.getIdFormateur());
        ResultSet resultat = ps.executeQuery();
        while (resultat.next()) {
            name = resultat.getString(1);
        }
        if (Objects.nonNull(name)) {
            return name;
        }
        return name;

    }

    @Override
    public boolean affecterOrganismeFormateur(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Formateur getFormateur(Cours cours) throws SQLException {
        Formateur formateur = new Formateur();
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from utilisateur where id=?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1, cours.getIdFormateur());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            formateur.setIdUtilisateur(rs.getInt("id"));
            formateur.setNomUtilisateur(rs.getString("pseudo"));
            formateur.setMotDePass(rs.getString("mdp"));
            formateur.setNom(rs.getString("nom"));
            formateur.setPrenom(rs.getString("prenom"));
            formateur.setDateNaissance(rs.getDate("date_naissance"));
            formateur.setTel(rs.getInt("telephone"));
            formateur.setAdresse(rs.getString("adresse"));
            formateur.setMail(rs.getString("mail"));
            formateur.setPhoto(rs.getString("photo"));
            formateur.setCv(rs.getString("cv"));
            formateur.setScore(rs.getInt("score"));
            formateur.setEtat(Etat.valueOf(rs.getString("etat")));

        }

        return formateur;

    }

    @Override
    public Formateur getFormateurByName(String nom) throws SQLException {
        Formateur formateur = new Formateur();
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from utilisateur where nom=?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, nom);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            formateur.setIdUtilisateur(rs.getInt("id"));
            formateur.setNomUtilisateur(rs.getString("pseudo"));
            formateur.setMotDePass(rs.getString("mdp"));
            formateur.setNom(rs.getString("nom"));
            formateur.setPrenom(rs.getString("prenom"));
            formateur.setDateNaissance(rs.getDate("date_naissance"));
            formateur.setTel(rs.getInt("telephone"));
            formateur.setAdresse(rs.getString("adresse"));
            formateur.setMail(rs.getString("mail"));
            formateur.setPhoto(rs.getString("photo"));
            formateur.setCv(rs.getString("cv"));
            formateur.setScore(rs.getInt("score"));
            formateur.setEtat(Etat.valueOf(rs.getString("etat")));

        }

        return formateur;

    }

    @Override
    public Formateur getFormateurById(int i) {
        try {
            Connection connection = DataSource.getInstance().getConnection();
            String requete = "select * from utilisateur where id = ?";
            PreparedStatement ps = connection.prepareCall(requete);
            ps.setInt(1, i);
            ResultSet result = ps.executeQuery();
            Formateur f = null;
            while (result.next()) {
                f = new Formateur();
                f.setIdUtilisateur(result.getInt(1));
                f.setIdOrganisationn(result.getInt(2));
                f.setNomUtilisateur(result.getString(3));
                f.setMotDePass(result.getString(9));
                f.setNom(result.getString(19));
                f.setPrenom(result.getString(20));
                f.setDateNaissance(result.getDate(21));
                f.setTel(result.getInt(22));
                f.setAdresse(result.getString(23));
                f.setMail(result.getString(5));
                f.setPhoto(result.getString(24));
                f.setRole(Role.FOR);
                f.setScore(result.getInt(25));
                f.setCv(result.getString(26));
                f.setEtat(Etat.valueOf(result.getString(27)));
                f.setSkype(result.getString(29));

            }
            ps.close();
            return f;

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
        String requete = "SELECT c.id,(select count(*) from cours s where s.id_utilisateur = c.id) as nbr from utilisateur c where c.roles ='a:1:{i:0;s:8:\"ROLE_FOR\";}' order by nbr desc ";
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

    @Override
    public ObservableList<Formateur> findAllAtt() {
        ObservableList<Formateur> listeFormateurs = FXCollections.observableArrayList();

        try {
            String request = "SELECT * FROM Utilisateur WHERE roles = 'a:1:{i:0;s:8:\"ROLE_FOR\";}' and etat = 'ATT' and score>0";
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(request);

            while (result.next()) {
                Formateur f = new Formateur();
                f.setIdUtilisateur(result.getInt(1));
                f.setIdOrganisationn(result.getInt(2));
                f.setNomUtilisateur(result.getString(3));
                f.setMotDePass(result.getString(9));
                f.setNom(result.getString(19));
                f.setPrenom(result.getString(20));
                f.setDateNaissance(result.getDate(21));
                f.setTel(result.getInt(22));
                f.setAdresse(result.getString(23));
                f.setMail(result.getString(5));
                f.setPhoto(result.getString(24));
                f.setRole(Role.FOR);
                f.setScore(result.getInt(25));
                f.setCv(result.getString(26));
                f.setEtat(Etat.valueOf(result.getString(27)));
                f.setSkype(result.getString(29));
                listeFormateurs.add(f);
            }
            return listeFormateurs;
        } catch (SQLException ex) {
            Logger.getLogger(ImplFormateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeFormateurs;
    }

    @Override
    public void rejeterFormateur(int idUtilisateur) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();

        String requete = "update utilisateur set etat=? where id = ?";
        Formateur newFormateur = new Formateur();
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, "REF");
        ps.setInt(2, idUtilisateur);
        int resultat = ps.executeUpdate();
        ps.close();
    }

    @Override
    public void accepterFormateur(int idUtilisateur) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();

        String requete = "update utilisateur set etat=? where id = ?";
        Formateur newFormateur = new Formateur();
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, "ACC");
        ps.setInt(2, idUtilisateur);
        int resultat = ps.executeUpdate();
        ps.close();
    }

    @Override
    public List<Formateur> getFormateurAndScore() throws SQLException {

        List<Formateur> list = new ArrayList<>();
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "SELECT pseudo, score FROM utilisateur WHERE roles='a:1:{i:0;s:8:\"ROLE_FOR\";}'";
        Statement ps = connection.createStatement();

        ResultSet rs = ps.executeQuery(requete);

        while (rs.next()) {
            Formateur formateur = new Formateur();
            formateur.setNomUtilisateur(rs.getString(1));
            formateur.setScore(rs.getInt(2));

            list.add(formateur);
        }
        ps.close();
        return list; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Formateur> getAllFormateursAtt() throws SQLException {

        List<Formateur> list = new ArrayList<>();
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from utilisateur where roles = 'a:1:{i:0;s:8:\"ROLE_FOR\";}' and Etat= 'Att' and score = 0";
        Statement ps = connection.createStatement();

        ResultSet rs = ps.executeQuery(requete);
        Formateur formateur = new Formateur();
        while (rs.next()) {

            formateur.setIdUtilisateur(rs.getInt(1));

            formateur.setNomUtilisateur(rs.getString(3));
            formateur.setMotDePass(rs.getString(9));
            formateur.setNom(rs.getString(19));
            formateur.setPrenom(rs.getString(20));
            formateur.setDateNaissance(rs.getDate(21));
            formateur.setTel(rs.getInt(22));
            formateur.setAdresse(rs.getString(23));
            formateur.setMail(rs.getString(5));
            formateur.setPhoto(rs.getString(24));
            formateur.setRole(Role.FOR);
            formateur.setEtat(Etat.ATT);

            list.add(formateur);//To change body of generated methods, choose Tools | Templates.
        }
        ps.close();
        return list;
    }

    @Override
    public void accRefFormateur(Formateur f, int i) {
        try {
            Connection connection = DataSource.getInstance().getConnection();
            String requete = "UPDATE utilisateur SET etat = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(2, f.getIdUtilisateur());
            if (i == 0) {
                ps.setString(1, "REF");
            } else if (i == 1) {
                ps.setString(1, "ACC");
            }
            int resultat = ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImplFormateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
