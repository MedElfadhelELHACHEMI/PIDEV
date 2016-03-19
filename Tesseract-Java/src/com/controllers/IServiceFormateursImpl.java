/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.ICoursDAO;
import com.models.entities.Cours;
import com.models.enums.Etat;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author haikal
 */
public class IServiceFormateursImpl implements IServiceFormateurs{

    @Override
    public List<Cours> getCoursesACCCoach(int idCoach) {
        ICoursDAO coursDAO = DAOFactory.getCoursDAO();
        try {
            return coursDAO.findCoursByIdFromateur(idCoach).stream().filter(c->c.getValidation2()==Etat.ACC).collect(Collectors.toList());
        } catch (SQLException ex) {
            Logger.getLogger(IServiceFormateursImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null ;    
    }
    
}
