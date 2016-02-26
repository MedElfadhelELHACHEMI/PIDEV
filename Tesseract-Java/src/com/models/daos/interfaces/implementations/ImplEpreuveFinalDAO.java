/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.IEpreuveFinalDAO;
import com.database.DataSource;
import com.models.entities.EpreuveFinal;
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
public class ImplEpreuveFinalDAO implements IEpreuveFinalDAO {
    
    Connection connection;
    PreparedStatement pst;
    ResultSet rS;
    private static final String TYPE = "Final";

    public ImplEpreuveFinalDAO() {
        connection =(DataSource.getInstance()).getConnection();
    }

    @Override
    public boolean createEpreuveFinal(EpreuveFinal epreuveFinal) {
        try {
            String request="insert into epreuves(difficulte, duree,type) values (?,?,?)";
            pst = connection.prepareStatement(request);
            pst.setString(1, epreuveFinal.getDifficulte());
            pst.setInt(2, epreuveFinal.getDureeExamen());
            pst.setString(3, TYPE);
            
            int result = pst.executeUpdate();
            pst.close();
            
            return (result==1);
            
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveFinalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteEpreuveFinal(int id) {
        try {
            String request = "dele from epreuves where id="+id;
            int result= pst.executeUpdate(request);
            pst.close();
            return (result==1);
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveFinalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateEpreuveFinal(EpreuveFinal epreuveFinal, int id) {
        try {
            String request="update epreuves set difficulte=?, duree=? where id=?";
            pst = connection.prepareStatement(request);
            pst.setString(1, epreuveFinal.getDifficulte());
            pst.setInt(2, epreuveFinal.getDureeExamen());
            pst.setInt(3, id);
            int result = pst.executeUpdate();
            pst.close();
            return result==1;
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveFinalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public EpreuveFinal searchEpreuveFinal(int id) {
        EpreuveFinal epreuveFinal = new EpreuveFinal();
        try {
            String request="select * fromepreuves where id=?";
            rS = pst.executeQuery(request);
            rS.next();
            epreuveFinal.setId(rS.getInt("id"));
            epreuveFinal.setDifficulte(rS.getString("difficulte"));
            epreuveFinal.setDureeExamen(rS.getInt("duree"));
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveFinalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return epreuveFinal;
    }

    @Override
    public List<EpreuveFinal> displayEpreuveFinal() {
        List<EpreuveFinal> epreuves=new ArrayList<>();
        try {
            String request="select * from epreuves";
            pst=connection.prepareStatement(request);
            rS=pst.executeQuery();
            while(rS.next()){
                EpreuveFinal e=new EpreuveFinal(rS.getInt(5),rS.getInt(1),rS.getString("difficulte"));
                epreuves.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveFinalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return epreuves;
    }
    
}
