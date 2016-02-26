/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces;

import com.models.entities.Log;
import java.util.List;

/**
 *
 * @author Choukou_Tracker
 */
public interface ILogDAO {
    
    public boolean ajouterLog(Log Log);
    
    public boolean supprimerLog(int id);
        
    public List<Log> getallLog();
    
    public Log getLogbyid(int id);
    
    public List<Log> getLogbyuser(int idUser);
    
    public List<Log> getLogbytache(String Tache);
    
}
