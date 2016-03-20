/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.ICoursDAO;
import com.models.daos.interfaces.IFormateurDAO;
import com.models.daos.interfaces.IOrganisationDAO;
import com.models.entities.Apprenant;
import com.models.entities.Cours;
import com.models.entities.Formateur;
import com.models.entities.Organisation;
import com.models.entities.Utilisateur;
import com.models.enums.Etat;
import com.views.controllers.CurrentUser;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author haikal
 */
public class IServiceFormateursImpl implements IServiceFormateurs {

    @Override
    public List<Cours> getCoursesACCCoach(int idCoach) {
        ICoursDAO coursDAO = DAOFactory.getCoursDAO();
        try {
            return coursDAO.findCoursByIdFromateur(idCoach).stream().filter(c -> c.getValidation2() == Etat.ACC).collect(Collectors.toList());
        } catch (SQLException ex) {
            Logger.getLogger(IServiceFormateursImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Organisation getOrganisationCoach(Utilisateur utilisateur) {
        IOrganisationDAO dAO = DAOFactory.getOrganisationDAO();
        return dAO.getOrganisationByid(utilisateur.getIdUtilisateur());
    }

    @Override
    public boolean updateFormateurInformations(Formateur formateur) {
        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        boolean resut = formateurDAO.modifierProfil(formateur.getNom(), formateur.getPrenom(), formateur.getMail(), formateur.getAdresse(), formateur.getTel(), CurrentUser.getId());
        return resut;
    }

    @Override
    public Utilisateur getUtilisateurById(int id) {
        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        Formateur resut = formateurDAO.getFormateurById(id);
        return resut;
    }

    @Override
    public boolean updateFormateurInformationsWithPass(Formateur formateur) {
       IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        boolean resut = formateurDAO.modifierProfilWithPwd(formateur.getNom(), formateur.getPrenom(), formateur.getMail(), formateur.getAdresse(), formateur.getTel(),formateur.getMotDePass(), CurrentUser.getId());
        return resut;
    }

}
