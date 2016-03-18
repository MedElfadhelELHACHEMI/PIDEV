package com.models.daos.interfaces.implementations;

import com.database.CryptographieMOOC;
import com.models.daos.interfaces.IUtilisateurDAO;
import com.database.DataSource;
import com.models.entities.Administrateur;
import com.models.entities.Apprenant;
import com.models.entities.Formateur;
import com.models.entities.MembreCP;
import com.models.entities.Utilisateur;
import com.models.enums.Etat;
import com.models.enums.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author haikal
 */
public class ImplUtilisateurDAO implements IUtilisateurDAO {

    private Connection connection;

    public ImplUtilisateurDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean addUtilisateur(Utilisateur user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteUtilisateur(int idUser) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateUtilisateur(Utilisateur User) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> displayUtilisateur() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Utilisateur getUtilisateurByID(int id) throws SQLException {
        String query = "select * from utilisateur where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            resultat.next();

            switch (resultat.getString(12)) {
                case "APR": {
                    Apprenant apprenant = new Apprenant();
                    apprenant.setIdUtilisateur(resultat.getInt(1));
                    apprenant.setNomUtilisateur(resultat.getString(3));
                    apprenant.setMotDePass(resultat.getString(4));
                    apprenant.setNom(resultat.getString(5));
                    apprenant.setPrenom(resultat.getString(6));
                    apprenant.setDateNaissance(resultat.getDate(7));
                    apprenant.setTel(resultat.getInt(8));
                    apprenant.setAdresse(resultat.getString(9));
                    apprenant.setMail(resultat.getString(10));
                    apprenant.setPhoto(resultat.getString(11));
                    apprenant.setRole(Role.valueOf(resultat.getString(12)));
                    apprenant.setScore(resultat.getInt(13));
                    return apprenant;
                }
                case "FOR": {
                    Formateur formateur = new Formateur();

                    formateur.setIdUtilisateur(resultat.getInt(1));
                    formateur.setIdOrganisationn(resultat.getInt(2));
                    formateur.setNomUtilisateur(resultat.getString(3));
                    formateur.setMotDePass(resultat.getString(4));
                    formateur.setNom(resultat.getString(5));
                    formateur.setPrenom(resultat.getString(6));
                    formateur.setDateNaissance(resultat.getDate(7));
                    formateur.setTel(resultat.getInt(8));
                    formateur.setAdresse(resultat.getString(9));
                    formateur.setMail(resultat.getString(10));
                    formateur.setPhoto(resultat.getString(11));
                    formateur.setRole(Role.valueOf(resultat.getString(12)));
                    formateur.setScore(resultat.getInt(13));

                    return formateur;

                }
                case "ADM":
                    Administrateur administrateur = new Administrateur();

                    administrateur.setIdUtilisateur(resultat.getInt(1));

                    administrateur.setNomUtilisateur(resultat.getString(3));
                    administrateur.setMotDePass(resultat.getString(4));
                    administrateur.setNom(resultat.getString(5));
                    administrateur.setPrenom(resultat.getString(6));
                    administrateur.setDateNaissance(resultat.getDate(7));
                    administrateur.setTel(resultat.getInt(8));
                    administrateur.setAdresse(resultat.getString(9));
                    administrateur.setMail(resultat.getString(10));
                    administrateur.setPhoto(resultat.getString(11));
                    administrateur.setRole(Role.valueOf(resultat.getString(12)));

                    return administrateur;
                default:
                    return new Utilisateur();
            }
        } catch (SQLException ex) {
            System.out.println("---------------------ERROR!");
            System.out.println(ex.getMessage());
        }
        return null;

    }

    @Override
    public Utilisateur getUtilisateurByMail(String mail) throws SQLException {
        String query = "select * from utilisateur where mail =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, mail);
            ResultSet resultat = ps.executeQuery();
            resultat.next();

            switch (resultat.getString(12)) {
                case "APR":
                   Apprenant apprenant = new Apprenant();
                    apprenant.setIdUtilisateur(resultat.getInt(1));
                    apprenant.setNomUtilisateur(resultat.getString(3));
                    apprenant.setMotDePass(resultat.getString(4));
                    apprenant.setNom(resultat.getString(5));
                    apprenant.setPrenom(resultat.getString(6));
                    apprenant.setDateNaissance(resultat.getDate(7));
                    apprenant.setTel(resultat.getInt(8));
                    apprenant.setAdresse(resultat.getString(9));
                    apprenant.setMail(resultat.getString(10));
                    apprenant.setPhoto(resultat.getString(11));
                    apprenant.setRole(Role.valueOf(resultat.getString(12)));
                    apprenant.setScore(resultat.getInt(13));
                    return apprenant;
                    
                  
                case "FOR":
                {Formateur formateur = new Formateur();

                    formateur.setIdUtilisateur(resultat.getInt(1));
                    formateur.setIdOrganisationn(resultat.getInt(2));
                    formateur.setNomUtilisateur(resultat.getString(3));
                    formateur.setMotDePass(resultat.getString(4));
                    formateur.setNom(resultat.getString(5));
                    formateur.setPrenom(resultat.getString(6));
                    formateur.setDateNaissance(resultat.getDate(7));
                    formateur.setTel(resultat.getInt(8));
                    formateur.setAdresse(resultat.getString(9));
                    formateur.setMail(resultat.getString(10));
                    formateur.setPhoto(resultat.getString(11));
                    formateur.setRole(Role.valueOf(resultat.getString(12)));
                    formateur.setScore(resultat.getInt(13));

                    return formateur;}
                case "ADM":
                {  Administrateur administrateur = new Administrateur();

                    administrateur.setIdUtilisateur(resultat.getInt(1));

                    administrateur.setNomUtilisateur(resultat.getString(3));
                    administrateur.setMotDePass(resultat.getString(4));
                    administrateur.setNom(resultat.getString(5));
                    administrateur.setPrenom(resultat.getString(6));
                    administrateur.setDateNaissance(resultat.getDate(7));
                    administrateur.setTel(resultat.getInt(8));
                    administrateur.setAdresse(resultat.getString(9));
                    administrateur.setMail(resultat.getString(10));
                    administrateur.setPhoto(resultat.getString(11));
                    administrateur.setRole(Role.valueOf(resultat.getString(12)));

                    return administrateur;}
                case "MCP":
                { MembreCP mcp = new MembreCP();
                      mcp.setIdUtilisateur(resultat.getInt(1));

                    mcp.setNomUtilisateur(resultat.getString(3));
                    mcp.setMotDePass(resultat.getString(4));
                    mcp.setNom(resultat.getString(5));
                    mcp.setPrenom(resultat.getString(6));
                    mcp.setDateNaissance(resultat.getDate(7));
                    mcp.setTel(resultat.getInt(8));
                    mcp.setAdresse(resultat.getString(9));
                    mcp.setMail(resultat.getString(10));
                    mcp.setPhoto(resultat.getString(11));
                    mcp.setRole(Role.valueOf(resultat.getString(12)));}
                case "ORG":{
                 {Formateur formateur = new Formateur();

                    formateur.setIdUtilisateur(resultat.getInt(1));
                    formateur.setIdOrganisationn(resultat.getInt(2));
                    formateur.setNomUtilisateur(resultat.getString(3));
                    formateur.setMotDePass(resultat.getString(4));
                    formateur.setNom(resultat.getString(5));
                    formateur.setPrenom(resultat.getString(6));
                    formateur.setDateNaissance(resultat.getDate(7));
                    formateur.setTel(resultat.getInt(8));
                    formateur.setAdresse(resultat.getString(9));
                    formateur.setMail(resultat.getString(10));
                    formateur.setPhoto(resultat.getString(11));
                    formateur.setRole(Role.valueOf(resultat.getString(12)));
                    formateur.setScore(resultat.getInt(13));

                    return formateur;}
                
                }

            }
        } catch (SQLException ex) {
            System.out.println("---------------------ERROR!");
            System.out.println(ex.getMessage());
        }
        return null;

    }

    @Override
    public boolean verifyPassword(Utilisateur user) throws SQLException {
        String typedPassword, correctPassword, aux = new String();
        String query = "select * from utilisateur where id=?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, user.getIdUtilisateur());
        ResultSet resultat = ps.executeQuery();
        resultat.next();
        correctPassword = resultat.getString(4);

//        try {
//          //  System.out.println(CryptographieMOOC.getCryptage().decrypt(correctPassword));
//        } catch (Exception ex) {
//            Logger.getLogger(ImplUtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        CryptographieMOOC cmooc = CryptographieMOOC.getCryptage();
        typedPassword = user.getMotDePass();
        try {

            aux = cmooc.encrypt(typedPassword);
        } catch (Exception ex) {
            Logger.getLogger(ImplUtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean v = correctPassword.equals(aux);

        ps.close();
        return v;

    }

}
