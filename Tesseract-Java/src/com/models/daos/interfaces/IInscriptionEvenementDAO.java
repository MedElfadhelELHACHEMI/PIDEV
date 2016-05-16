/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces;

import com.models.entities.InscriptionEvenement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author haikal
 */
public interface IInscriptionEvenementDAO {

    public int nbrUtilisateurInscrit(int idEvenement) throws SQLException;

    public List<InscriptionEvenement> getInscriptionEvenementbyUserid(int idUser)throws SQLException;

    public boolean ajouterInscriptionEvenement(InscriptionEvenement challenge) throws SQLException;
    
}
