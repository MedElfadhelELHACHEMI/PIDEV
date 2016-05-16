/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;


import com.models.daos.interfaces.IReponseDAO;
import com.database.DataSource;
import com.models.entities.Reponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bacem
 */
public class ImplReponseDAO implements IReponseDAO{

    Connection connection;
    PreparedStatement pst;
    ResultSet rS;

    public ImplReponseDAO() {
        connection =(DataSource.getInstance()).getConnection();
    }
    
    @Override
    public boolean createReponse(Reponse reponse) {
        try {
            String request="insert into reponses(reponse, justification, etat, id_question) values(?,?,?,?)";
            pst = connection.prepareStatement(request);
            pst.setString(1, reponse.getReponse());
            pst.setString(2, reponse.getJustification());
            pst.setString(3, reponse.getEtat());
            pst.setInt(4, reponse.getIdQuestion());
            
            int result = pst.executeUpdate();
            pst.close();
            
            return (result==1);
        } catch (SQLException ex) {
            Logger.getLogger(ImplReponseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteReponse(int id) {
        try {
            String request = "delete from reponses where id="+id;
            int result= pst.executeUpdate(request);
            pst.close();
            return (result==1);
        } catch (SQLException ex) {
            Logger.getLogger(ImplReponseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateReponse(Reponse reponse, int id) {
        try {
            String request = "update reponses set reponse=?, justification=?, etat=?, id_question=? where id=?";
            pst = connection.prepareStatement(request);
            pst.setString(1, reponse.getReponse());
            pst.setString(2, reponse.getJustification());
            pst.setString(3, reponse.getEtat());
            pst.setInt(4, reponse.getIdQuestion());
            
            int result = pst.executeUpdate();
            pst.close();
            return result==1;
        } catch (SQLException ex) {
            Logger.getLogger(ImplReponseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Reponse searchReponse(int id) {
        Reponse reponse = new Reponse();
        try {
            String request="select * from reponses where id =?";
            rS = pst.executeQuery(request);
            rS.next();
            reponse.setId(rS.getInt("id"));
            reponse.setReponse(rS.getString("reponse"));
            reponse.setJustification(rS.getString("justification"));
            reponse.setEtat(rS.getString("etat"));
            reponse.setIdQuestion(rS.getInt("id_question"));
        } catch (SQLException ex) {
            Logger.getLogger(ImplReponseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reponse;
    }

    @Override
    public List<Reponse> displayReponse() {
        List<Reponse> epreuves=new ArrayList<>();
        try {
            String request="select * from reponses";
            pst=connection.prepareStatement(request);
            rS=pst.executeQuery();
            while(rS.next()){
                Reponse r=new Reponse(rS.getInt(1),rS.getString("reponse"),rS.getString("justification"),rS.getString("etat"), rS.getInt(2));
                epreuves.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplReponseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return epreuves;
    }

    @Override
    public List<Reponse> findReponseByIdQuestion(int idQuestion)  throws SQLException {
      List<Reponse> listereponse = new ArrayList<>();
        String requete = "select * from reponse where id_question=?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1, idQuestion);
        ResultSet resultat = ps.executeQuery();
     
        while (resultat.next()) {
          Reponse reponse = new Reponse();
             reponse.setId(resultat.getInt(1));
             reponse.setIdQuestion(resultat.getInt(2));
             reponse.setReponse(resultat.getString(3));
             reponse.setJustification(resultat.getString(4));
             reponse.setEtat(resultat.getString(5));
             listereponse.add(reponse);
            
        }
        if (Objects.nonNull(listereponse)) {
            return listereponse;
        }

        throw new UnsupportedOperationException();  }
    
}
