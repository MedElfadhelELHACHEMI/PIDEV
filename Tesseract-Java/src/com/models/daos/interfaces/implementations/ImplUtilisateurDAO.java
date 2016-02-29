/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
                case "APR":
                    return new Apprenant(resultat.getInt(13), Etat.valueOf(resultat.getString(15)), resultat.getInt(1), resultat.getString(3), resultat.getString(4), resultat.getString(5), resultat.getString(6), resultat.getDate(7), resultat.getInt(8), resultat.getString(9), resultat.getString(10), resultat.getString(11));
                case "frm":
                    return new Formateur(resultat.getString(14), Etat.valueOf(resultat.getString(15)), resultat.getInt(0), resultat.getString(3), resultat.getString(4), resultat.getString(5), resultat.getString(6), resultat.getDate(7), resultat.getInt(8), resultat.getString(9), resultat.getString(10), resultat.getString(11));
                case "adm":
                    return new Administrateur();
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
                    Apprenant a = new Apprenant();
                    a.setIdUtilisateur(resultat.getInt(1));
                    return a;
                case "FOR":
                    Formateur f = new Formateur();
                    f.setIdUtilisateur(resultat.getInt(1));
                    return f;
                case "ADM":
                    Administrateur d = new Administrateur();
                    d.setIdUtilisateur(resultat.getInt(1));
                    return d;
                case "MCP":
                    MembreCP m = new MembreCP();
                    m.setIdUtilisateur(resultat.getInt(1));
                    return m;

            }
        } catch (SQLException ex) {
            System.out.println("---------------------ERROR!");
            System.out.println(ex.getMessage());
        }
        return null;

    }

    @Override
    public boolean verifyPassword(Utilisateur user) throws SQLException {
        String typedPassword , correctPassword,aux=new String();
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
        typedPassword=user.getMotDePass();
        try {       
           
            aux=cmooc.encrypt(typedPassword);
        } catch (Exception ex) {
            Logger.getLogger(ImplUtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        boolean v=correctPassword.equals(aux);
       
          
        ps.close();
        return v;

    }

}
