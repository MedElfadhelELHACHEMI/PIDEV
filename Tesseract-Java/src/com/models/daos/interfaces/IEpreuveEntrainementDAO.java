/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces;

import com.models.entities.EpreuveEntrainement;
import java.util.List;

/**
 *
 * @author Bacem
 */
public interface IEpreuveEntrainementDAO {
    boolean createEpreuveEntrainement(EpreuveEntrainement epreuveEntrainement);
    boolean deleteEpreuveEntrainement(int id);
    boolean updateEpreuveEntrainement(EpreuveEntrainement epreuveEntrainement, int id);
    EpreuveEntrainement searchEpreuveEntrainement(int id);
    List<EpreuveEntrainement> displayEpreuveEntrainement();
    
}
