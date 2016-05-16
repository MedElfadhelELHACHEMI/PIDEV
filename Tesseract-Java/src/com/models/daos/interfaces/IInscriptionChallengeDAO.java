/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces;

import com.models.entities.InscriptionChallenge;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author haikal
 */
public interface IInscriptionChallengeDAO {

    public int nbrUtilisateurInscrit(int idChallenge) throws SQLException;

    public List<InscriptionChallenge> getInscriptionChallengebyUserid(int idUser) throws SQLException;

    public boolean ajouterInscriptionChallenge(InscriptionChallenge challenge)throws SQLException;
    
   
}
