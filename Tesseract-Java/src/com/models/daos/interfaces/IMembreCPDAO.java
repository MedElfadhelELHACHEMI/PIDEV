

package com.models.daos.interfaces;

import com.models.entities.Formateur;
import com.models.entities.MembreCP;
import java.sql.SQLException;
import java.util.List;


public interface IMembreCPDAO {
     public boolean ajouterMembreCP(MembreCP MembreCP) throws SQLException ;
   
   public MembreCP getMembreCPByLogin(String login)throws SQLException;
   
   public boolean supprimerMembreCPByLogin(String login)throws SQLException;
   
   public List<MembreCP> getAllMembreCPs()throws SQLException;
   
   public boolean modifierMembreCP (String login , MembreCP newMembreCP)throws SQLException;
}
