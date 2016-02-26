/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.ICommentaireForumDAO;
import com.database.DataSource;
import com.models.entities.CommentaireForum;
import com.models.entities.Matiere;
import com.models.entities.SujetForum;
import com.models.entities.Utilisateur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class ImplCommentaireForumDAO implements ICommentaireForumDAO{
    private Connection connection;

    public ImplCommentaireForumDAO() {
        connection = DataSource.getInstance().getConnection();
    }
    
    @Override
    public boolean addCommentaireForum(CommentaireForum cmntfrm) throws SQLException {
       String query = "Insert into commentaire_forum(`id_utilisateur`,`id_sujet`, `contenu`,`date`) "
                + "values (?, ?, ?, ?);";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            pSt.setInt(1,cmntfrm.getUtilisateur().getIdUtilisateur());
            pSt.setInt(2,cmntfrm.getSujetforum().getId());
            pSt.setString(3,cmntfrm.getContenu());
            pSt.setDate(4, (Date) cmntfrm.getDate());
            pSt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error to add sujet forum !");
            return false;
        }
    }

    @Override
    public boolean deleteCommentaireForum(int idCmnt) throws SQLException {
        String requete = "delete from commentaire_forum where id=?";
        PreparedStatement ps = connection.prepareCall(requete);
        ps.setInt(1, idCmnt);
        int reslt = ps.executeUpdate();
        return reslt == 1;
    }

    @Override
    public boolean updateCommentaireForum(CommentaireForum cmntfrm) throws SQLException {
        String requete = "update commentaire_forum set id_utilisateur=?,id_sujet=?,contenu=?,date=? where id=?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1,cmntfrm.getUtilisateur().getIdUtilisateur());
        ps.setInt(2, cmntfrm.getSujetforum().getId());
        ps.setString(3,cmntfrm.getContenu());
        ps.setDate(4, cmntfrm.getDate());
        ps.setInt(5,cmntfrm.getId());
        int resultat = ps.executeUpdate();
        return resultat == 1;
    }

    @Override
    public List<CommentaireForum> displayCommentaireForum() throws SQLException {
             ArrayList<CommentaireForum> liste = new ArrayList<CommentaireForum>();
        String query = "select * from commentaire_forum";   
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(query);
            while (resultat.next()) {
                CommentaireForum sj=new CommentaireForum();
                ImplUtilisateurDAO ud=new ImplUtilisateurDAO();               
                Utilisateur usr = (Utilisateur) ud.getUtilisateurByID(resultat.getInt(2));
                SujetForum s=new SujetForum();
                ImplSujetForumDAO cs=new ImplSujetForumDAO();
                s=cs.getSujetForum(resultat.getInt(3));
                sj.setId(resultat.getInt(1));
                sj.setUtilisateur(usr);
                sj.setSujetforum(s);
                sj.setContenu(resultat.getString(4));
                sj.setDate(resultat.getDate(5));
                liste.add(sj);
            }
            return liste;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("______________________________________ERROR______________________________________");
            System.out.println("erreur lors du chargement des Logs " + ex.getMessage());
            System.out.println("______________________________________ERROR______________________________________");
            return null;
        }
    }

    @Override
    public List<CommentaireForum> getCommentaireForumBySujet(SujetForum sujetf) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CommentaireForum> getCommentaireForumByUtilisateur(Utilisateur user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
