package com.models.daos.interfaces;
import com.models.entities.Challenge;
import java.sql.Date;
import java.util.List;


public interface IChallengeDAO {
    
    public boolean addChallenge(Challenge chl);
    
    public boolean deleteChallenge(int idChl);
    
    boolean updateChallenge(Challenge chl);
        
    public List<Challenge> displayChallenge();
    
    public List<Challenge> displayChallengeByOrganisation(int idChl);
    
    public Challenge getChallengeByid(int id);
    
    public Challenge getChallengeByNom(String nom);

    public List<Challenge> displayChallengeByFormateur(int idChl);

    public List<Challenge> displayChallenge(int id);

    public List<Challenge> displayChallengeUtilisateur(int id);

    public List<Challenge> getChallengeByNomOrganisation(String nom, int id);

    public List<Challenge> displayChallengeByDate(Date date, int id);
}
