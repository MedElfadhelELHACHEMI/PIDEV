
package com.models.daos.interfaces;

import com.models.entities.Apprenant;
import com.models.entities.ScoreUtilisateur;
import com.models.entities.SessionCours;
import java.sql.SQLException;
import java.util.List;


public interface IApprenantDAO {
    
    
   public boolean ajouterApprenant(Apprenant apprenant) throws SQLException ;
   
   public Apprenant getApprenantByLogin(String login)throws SQLException;
   
   public boolean supprimerApprennantByLogin(String login)throws SQLException;
   
   public List<Apprenant> getAllApprenants()throws SQLException;
   
   public boolean modifierApprenant (String login , Apprenant newApprenant)throws SQLException;

    public Apprenant getApprenantsById(int i);

    public List<ScoreUtilisateur> getTopUtilisateur(int id) throws SQLException;

   
   
   
    
}
