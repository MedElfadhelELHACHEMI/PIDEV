package com.models.daos.interfaces;
import com.models.entities.Evenement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


public interface IEvenementDAO {
    
    public boolean addEvenement(Evenement evn) throws SQLException;
    
    public boolean deleteEvenement(int idEvn) throws SQLException;
    
    boolean updateEvenement(Evenement evn)throws SQLException;
        
    public List<Evenement> displayEvenement()throws SQLException;
    
    public List<Evenement> displayByIdOrganisation(int idOrg)throws SQLException;
    
    public Evenement getEvenementByid(int id)throws SQLException;
    
    public Evenement getEvenementByNom(String nom)throws SQLException;

    public List<Evenement> displayEvenement(int id)throws SQLException ;

    public List<Evenement> displayByDate(Date date, int id)throws SQLException ;

    public List<Evenement> displayEvenementUtilisateur(int id)throws SQLException ;

    public List<Evenement> getEvenementByNomOrganisation(String nom, int id)throws SQLException ;
}
