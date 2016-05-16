/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.database.DataSource;
import com.models.daos.interfaces.ICommentaireCoursDAO;
import com.models.entities.CommentaireCours;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author haikal
 */
public class ImplCommentaireCoursDAO implements ICommentaireCoursDAO{
  private Connection cnx;

    public ImplCommentaireCoursDAO() {
        cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public Object afficherCommentaireByCours(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean AjouterCommentaireCours(CommentaireCours n) throws SQLException {
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        String req = "insert into commentaire_cours (id_cours,id_utilisateur,commentaire,date) values (?,?,?,?)";
        
        PreparedStatement ps = cnx.prepareStatement(req);
       ps.setInt(1, n.getIdCours());
       ps.setInt(2, n.getIdUtilisateur());
       ps.setString(3, n.getCommentaire());
       ps.setDate(4, date_sql);
        int ajout = ps.executeUpdate();
        ps.close();
        return (ajout == 1);  
    }
    
}
