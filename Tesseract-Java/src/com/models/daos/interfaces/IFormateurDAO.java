
package com.models.daos.interfaces;

import com.models.entities.Administrateur;
import com.models.entities.Apprenant;
import com.models.entities.Formateur;
import java.sql.SQLException;
import java.util.List;


public interface IFormateurDAO {
    
     public boolean ajouterFormateur(Formateur apprenant,Integer idOrganisme) throws SQLException ;
   
   public Formateur getFormateurByLogin(String login)throws SQLException;
   
   public boolean supprimerFormateurByLogin(String login)throws SQLException;
   
   public List<Formateur> getAllFormateurs()throws SQLException;
   
   public boolean modifierFormateur (String login , Formateur newFormateur)throws SQLException;

    public boolean affecterOrganismeFormateur(int i, int i0);

    public Object getFormateurById(int i);
    
}
