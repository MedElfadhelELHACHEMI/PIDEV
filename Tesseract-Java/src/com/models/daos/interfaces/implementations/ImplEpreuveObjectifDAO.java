/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.IEpreuveObjectifDAO;
import com.database.DataSource;
import com.models.entities.EpreuveObjectif;
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
public class ImplEpreuveObjectifDAO implements IEpreuveObjectifDAO {

    Connection connection;
    PreparedStatement pst;
    ResultSet rS;
    private static final String TYPE = "Objectif";

    public ImplEpreuveObjectifDAO() {
        connection =(DataSource.getInstance()).getConnection();
    }
    

    @Override
    public boolean createEpreuveObjectif(EpreuveObjectif epreuveObjectif) {
        try {
            String request="insert into epreuves(difficulte,type) values (?,?)";
            pst = connection.prepareStatement(request);
            pst.setString(1, epreuveObjectif.getDifficulte());
            pst.setString(2, TYPE);
            
            int result = pst.executeUpdate();
            pst.close();
            
            return (result==1);
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveObjectifDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;   
    }

    @Override
    public boolean deleteEpreuveObjectif(int id) {
        try {
            String request = "dele from epreuves where id="+id;
            int result= pst.executeUpdate(request);
            pst.close();
            return (result==1);
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveObjectifDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateEpreuveObjectif(EpreuveObjectif epreuveObjectif, int id) {
        try {
            String request="update epreuves set difficulte=? where id=?";
            pst = connection.prepareStatement(request);
            pst.setString(1, epreuveObjectif.getDifficulte());
            pst.setInt(2, id);
            int result = pst.executeUpdate();
            pst.close();
            return result==1;
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveObjectifDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public EpreuveObjectif searchEpreuveObjectif(int id) {
        EpreuveObjectif epreuveObjectif = new EpreuveObjectif();
        try {
            String request="select * from epreuves where id=?";
            rS = pst.executeQuery(request);
            rS.next();
            epreuveObjectif.setId(rS.getInt("id"));
            epreuveObjectif.setDifficulte(rS.getString("difficulte"));
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveObjectifDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return epreuveObjectif;
    }

    @Override
    public List<EpreuveObjectif> displayEpreuveObjectif() {
        List<EpreuveObjectif> epreuves=new ArrayList<>();
        try {
            String request="select * from epreuves";
            pst=connection.prepareStatement(request);
            rS=pst.executeQuery();
            while(rS.next()){
                EpreuveObjectif e=new EpreuveObjectif(rS.getInt(1),rS.getString("difficulte"));
                epreuves.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveObjectifDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return epreuves;
    }

    @Override
    public List<EpreuveObjectif> searchEpreuveOBJByObj(int idobj) throws SQLException{
      
List<EpreuveObjectif> epreuves=new ArrayList<>();
            String request="select * from epreuve where id_objectif=? and type like '%OBJ%'";
           PreparedStatement ps = connection.prepareStatement(request);
               ps.setInt(1, idobj);
        ResultSet resultat = ps.executeQuery();
        while (resultat.next()) {
             EpreuveObjectif epreuveFinal = new EpreuveObjectif();
        epreuveFinal.setId(resultat.getInt(1));
        epreuveFinal.setDifficulte(resultat.getString(5));
         epreuves.add(epreuveFinal);
        }
        if (Objects.nonNull(epreuves)) {
            return epreuves;
        }

        throw new UnsupportedOperationException(); 
    }
    
}
