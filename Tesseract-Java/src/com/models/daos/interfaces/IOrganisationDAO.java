/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces;
import com.models.entities.Organisation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author BoB
 */
public interface IOrganisationDAO {
    
    public boolean addOrganisation(Organisation org);
    
    public boolean deleteOrganisation(int idOrg);
    
    boolean updateOrganisation(Organisation org);
        
    public List<Organisation> displayOrganisation();
    
    public Organisation getOrganisationByid(int id);
    
    public Organisation getOrganisationByNom(String nom);

    public Object getOrganisationByMatriculeNom(String oxia);

    public int getCountCoachOrganis(int idOrganisation);

    public Organisation getOrganisationByName(String nom) throws SQLException ;
 
    
    
}
