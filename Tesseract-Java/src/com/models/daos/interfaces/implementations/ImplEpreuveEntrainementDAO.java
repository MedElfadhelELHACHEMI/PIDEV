/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;
import com.models.daos.interfaces.IEpreuveEntrainementDAO;
import com.database.DataSource;
import com.models.entities.EpreuveEntrainement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bacem
 */
public class ImplEpreuveEntrainementDAO implements IEpreuveEntrainementDAO{

    Connection connection;
    PreparedStatement pst;
    ResultSet rS;
    private static final String TYPE = "Entrainement";

    public ImplEpreuveEntrainementDAO() {
        connection =(DataSource.getInstance()).getConnection();
    }
    
    
    @Override
    public boolean createEpreuveEntrainement(EpreuveEntrainement epreuveEntrainement) {
        try {
            String request="insert into epreuves(difficulte, type) values (?,?)";
            pst = connection.prepareStatement(request);
            pst.setString(1, epreuveEntrainement.getDifficulte());
            pst.setString(2, TYPE);
            
            int result = pst.executeUpdate();
            pst.close();
            
            return (result==1);
            
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveEntrainementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteEpreuveEntrainement(int id) {
        try {
            String request = "delete from epreuves where id="+id;
            int result= pst.executeUpdate(request);
            pst.close();
            return (result==1);
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveEntrainementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateEpreuveEntrainement(EpreuveEntrainement epreuveEntrainement, int id) {
        try {
            String request="update epreuves set difficulte=? where id=?";
            pst = connection.prepareStatement(request);
            pst.setString(1, epreuveEntrainement.getDifficulte());
            pst.setInt(2, id);
            int result = pst.executeUpdate();
            pst.close();
            return result==1;
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveEntrainementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public EpreuveEntrainement searchEpreuveEntrainement(int id) {
        EpreuveEntrainement epreuveEntrainement = new EpreuveEntrainement();
        try {
            String request="select * from epreuves where id=?";
            rS = pst.executeQuery(request);
            rS.next();
            epreuveEntrainement.setId(rS.getInt("id"));
            epreuveEntrainement.setDifficulte(rS.getString("difficulte"));
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveEntrainementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return epreuveEntrainement;
    }

    @Override
    public List<EpreuveEntrainement> displayEpreuveEntrainement() {
        List<EpreuveEntrainement> epreuves=new ArrayList<>();
        try {
            String request="select * from epreuves";
            pst=connection.prepareStatement(request);
            rS=pst.executeQuery();
            while(rS.next()){
                EpreuveEntrainement e=new EpreuveEntrainement(rS.getInt(1),rS.getString("difficulte"));
                epreuves.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveEntrainementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return epreuves;
    }
    
}
