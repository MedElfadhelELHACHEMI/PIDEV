package com.models.daos.interfaces.implementations;

import com.database.ArrayToString;
import com.database.CryptographieMOOC;
import com.models.daos.interfaces.IUtilisateurDAO;
import com.database.DataSource;
import com.models.entities.Administrateur;
import com.models.entities.Apprenant;
import com.models.entities.Formateur;
import com.models.entities.MembreCP;
import com.models.entities.Notification;
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
            switch (ArrayToString.arrayToEnum(resultat.getString("roles")).toString()) {
                case "APR": {
                    Apprenant apprenant = new Apprenant();
                    apprenant.setIdUtilisateur(resultat.getInt("id"));
                    apprenant.setNomUtilisateur(resultat.getString("nom"));
                    apprenant.setMotDePass(resultat.getString("mdp"));
                    apprenant.setNom(resultat.getString("nom"));
                    apprenant.setPrenom(resultat.getString("prenom"));
                    apprenant.setDateNaissance(resultat.getDate("date_naissance"));
                    apprenant.setTel(resultat.getInt("telephone"));
                    apprenant.setAdresse(resultat.getString("adresse"));
                    apprenant.setMail(resultat.getString("mail"));
                    apprenant.setPhoto(resultat.getString("photo"));
                    apprenant.setRole(ArrayToString.arrayToEnum(resultat.getString("roles")));
                    apprenant.setScore(resultat.getInt("score"));
                    return apprenant;
                }
                case "FOR": {
                    Formateur formateur = new Formateur();

                    formateur.setIdUtilisateur(resultat.getInt("id"));
                    formateur.setIdOrganisationn(resultat.getInt("id_organisation"));
                    formateur.setNomUtilisateur(resultat.getString("nom"));
                    formateur.setMotDePass(resultat.getString("mdp"));
                    formateur.setNom(resultat.getString("nom"));
                    formateur.setPrenom(resultat.getString("prenom"));
                    formateur.setDateNaissance(resultat.getDate("date_naissance"));
                    formateur.setTel(resultat.getInt("telephone"));
                    formateur.setAdresse(resultat.getString("adresse"));
                    formateur.setMail(resultat.getString("mail"));
                    formateur.setPhoto(resultat.getString("photo"));
                    formateur.setRole(ArrayToString.arrayToEnum(resultat.getString("roles")));
                    formateur.setScore(resultat.getInt("score"));

                    return formateur;

                }
                case "ADM":
                    Administrateur administrateur = new Administrateur();
                    administrateur.setIdUtilisateur(resultat.getInt("id"));
                    administrateur.setNomUtilisateur(resultat.getString("nom"));
                    administrateur.setMotDePass(resultat.getString("mdp"));
                    administrateur.setNom(resultat.getString("nom"));
                    administrateur.setPrenom(resultat.getString("prenom"));
                    administrateur.setDateNaissance(resultat.getDate("date_naissance"));
                    administrateur.setTel(resultat.getInt("telephone"));
                    administrateur.setAdresse(resultat.getString("adresse"));
                    administrateur.setMail(resultat.getString("mail"));
                    administrateur.setPhoto(resultat.getString("photo"));
                    administrateur.setRole(ArrayToString.arrayToEnum(resultat.getString("roles")));

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
            System.out.println(ArrayToString.arrayToEnum(resultat.getString("roles")).toString());
            switch (ArrayToString.arrayToEnum(resultat.getString("roles")).toString()) {
                case "APR":
                    Apprenant apprenant = new Apprenant();
                    apprenant.setIdUtilisateur(resultat.getInt("id"));
                    apprenant.setNomUtilisateur(resultat.getString("nom"));
                    apprenant.setMotDePass(resultat.getString("mdp"));
                    apprenant.setNom(resultat.getString("nom"));
                    apprenant.setPrenom(resultat.getString("prenom"));
                    apprenant.setDateNaissance(resultat.getDate("date_naissance"));
                    apprenant.setTel(resultat.getInt("telephone"));
                    apprenant.setAdresse(resultat.getString("adresse"));
                    apprenant.setMail(resultat.getString("mail"));
                    apprenant.setPhoto(resultat.getString("photo"));
                    apprenant.setRole(ArrayToString.arrayToEnum(resultat.getString("roles")));
                    apprenant.setScore(resultat.getInt("score"));
                    return apprenant;

                case "FOR": {
                   Formateur formateur = new Formateur();

                    formateur.setIdUtilisateur(resultat.getInt("id"));
                    formateur.setIdOrganisationn(resultat.getInt("id_organisation"));
                    formateur.setNomUtilisateur(resultat.getString("nom"));
                    formateur.setMotDePass(resultat.getString("mdp"));
                    formateur.setNom(resultat.getString("nom"));
                    formateur.setPrenom(resultat.getString("prenom"));
                    formateur.setDateNaissance(resultat.getDate("date_naissance"));
                    formateur.setTel(resultat.getInt("telephone"));
                    formateur.setAdresse(resultat.getString("adresse"));
                    formateur.setMail(resultat.getString("mail"));
                    formateur.setPhoto(resultat.getString("photo"));
                    formateur.setRole(ArrayToString.arrayToEnum(resultat.getString("roles")));
                    formateur.setScore(resultat.getInt("score"));

                    return formateur;
                }
                case "ADM": {
                 Administrateur administrateur = new Administrateur();
                    administrateur.setIdUtilisateur(resultat.getInt("id"));
                    administrateur.setNomUtilisateur(resultat.getString("nom"));
                    administrateur.setMotDePass(resultat.getString("mdp"));
                    administrateur.setNom(resultat.getString("nom"));
                    administrateur.setPrenom(resultat.getString("prenom"));
                    administrateur.setDateNaissance(resultat.getDate("date_naissance"));
                    administrateur.setTel(resultat.getInt("telephone"));
                    administrateur.setAdresse(resultat.getString("adresse"));
                    administrateur.setMail(resultat.getString("mail"));
                    administrateur.setPhoto(resultat.getString("photo"));
                    administrateur.setRole(ArrayToString.arrayToEnum(resultat.getString("roles")));

                    return administrateur;
                }
                case "MCP": {
                    MembreCP mcp = new MembreCP();
          mcp.setIdUtilisateur(resultat.getInt("id"));
                    mcp.setNomUtilisateur(resultat.getString("nom"));
                    mcp.setMotDePass(resultat.getString("mdp"));
                    mcp.setNom(resultat.getString("nom"));
                    mcp.setPrenom(resultat.getString("prenom"));
                    mcp.setDateNaissance(resultat.getDate("date_naissance"));
                    mcp.setTel(resultat.getInt("telephone"));
                    mcp.setAdresse(resultat.getString("adresse"));
                    mcp.setMail(resultat.getString("mail"));
                    mcp.setPhoto(resultat.getString("photo"));
                    mcp.setRole(ArrayToString.arrayToEnum(resultat.getString("roles")));

                    
                    
                    return mcp ;
                }
                case "ORG": {
                    {
//                        Formateur formateur = new Formateur();
//
//                        formateur.setIdUtilisateur(resultat.getInt(1));
//                        formateur.setIdOrganisationn(resultat.getInt(2));
//                        formateur.setNomUtilisateur(resultat.getString(3));
//                        formateur.setMotDePass(resultat.getString(4));
//                        formateur.setNom(resultat.getString(5));
//                        formateur.setPrenom(resultat.getString(6));
//                        formateur.setDateNaissance(resultat.getDate(7));
//                        formateur.setTel(resultat.getInt(8));
//                        formateur.setAdresse(resultat.getString(9));
//                        formateur.setMail(resultat.getString(10));
//                        formateur.setPhoto(resultat.getString(11));
//                        formateur.setRole(Role.valueOf(resultat.getString(12)));
//                        formateur.setScore(resultat.getInt(13));
//
//                        return formateur;
                    }

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
        correctPassword = ArrayToString.getPasswordFromArray(resultat.getString("mdp"));
                
                System.out.println("Correct PAssword"+correctPassword);

//        try {
//          //  System.out.println(CryptographieMOOC.getCryptage().decrypt(correctPassword));
//        } catch (Exception ex) {
//            Logger.getLogger(ImplUtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        CryptographieMOOC cmooc = CryptographieMOOC.getCryptage();
        typedPassword = user.getMotDePass();
        System.out.println(typedPassword+"----------------");
        try {

            aux = cmooc.encrypt(typedPassword);
        } catch (Exception ex) {
            Logger.getLogger(ImplUtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean v = correctPassword.equals(aux);

        ps.close();
        return v;

    }

    @Override
    public List<Notification> displayAllNotifications(int idUtilisateur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
