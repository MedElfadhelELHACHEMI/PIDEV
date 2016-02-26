package com.models.daos.interfaces;
import com.models.entities.Challenge;
import java.util.List;


public interface IChallengeDAO {
    
    public boolean addChallenge(Challenge chl);
    
    public boolean deleteChallenge(int idChl);
    
    boolean updateChallenge(Challenge chl);
        
    public List<Challenge> displayChallenge();
    
    public List<Challenge> displayChallengeByOrganisation(int idChl);
    
    public Challenge getChallengeByid(int id);
    
    public Challenge getChallengeByNom(String nom);
}
