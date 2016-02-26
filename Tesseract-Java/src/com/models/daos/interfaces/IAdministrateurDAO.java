
package com.models.daos.interfaces;

import com.models.entities.Administrateur;
import java.sql.SQLException;
import java.util.List;


public interface IAdministrateurDAO {
       
   public boolean ajouterAdministrateur(Administrateur apprenant) throws SQLException ;
   
   public Administrateur getAdministrateurByLogin(String login)throws SQLException;
   
   public boolean supprimerAdministrateurByLogin(String login)throws SQLException;
   
   public List<Administrateur> getAllAdministrateurs()throws SQLException;
   
   public boolean modifierAdministrateur (String login , Administrateur newAdministrateur)throws SQLException;
   
}
