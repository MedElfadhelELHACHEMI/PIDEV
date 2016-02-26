/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces;


import com.models.entities.Objectif;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Noor
 */
public interface IObjectifDAO {

    public boolean addObjectif(Objectif o);

    public boolean deleteObjectif(int id);

    public boolean updateObjectif(Objectif o);

    public ObservableList<Objectif> displayObjectifs(int idChapitre);

    public Objectif searchObjectif(String nom, int idChapitre);
    
    public boolean isNumberThere(int number, int idChapitre);
    
    

}
