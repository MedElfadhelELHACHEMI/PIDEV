/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces;

import com.models.entities.Reponse;
import java.util.List;

/**
 *
 * @author Bacem
 */
public interface IReponseDAO {
    boolean createReponse(Reponse reponse);
    boolean deleteReponse(int id);
    boolean updateReponse(Reponse reponse, int id);
    Reponse searchReponse(int id);
    List<Reponse> displayReponse();
    
}
