/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IApprenantDAO;
import com.models.daos.interfaces.ICoursDAO;
import com.models.daos.interfaces.IFormateurDAO;
import com.models.daos.interfaces.ILogDAO;
import com.models.daos.interfaces.IOrganisationDAO;
import com.models.daos.interfaces.ISessionCoursDAO;
import com.models.entities.Apprenant;
import com.models.entities.Cours;
import com.models.entities.Formateur;
import com.models.entities.Log;
import com.models.entities.Organisation;
import com.models.entities.ScoreUtilisateur;
import com.models.entities.SessionCours;
import com.models.entities.Utilisateur;
import com.models.enums.Etat;
import com.models.enums.Role;
import com.views.controllers.CurrentUser;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        boolean resut = formateurDAO.modifierProfilWithPwd(formateur.getNom(), formateur.getPrenom(), formateur.getMail(), formateur.getAdresse(), formateur.getTel(), formateur.getMotDePass(), CurrentUser.getId());
        return resut;
    }

    @Override
    public List<SessionCours> getListCoursConsulted(int id) {
        ISessionCoursDAO session = DAOFactory.getSessionCoursDAO();
        return session.listSessionCosulterParCoach(id);
    }

    @Override
    public List<Log> getLoginUtilisateur(int id) {
        ILogDAO dAO = DAOFactory.getLogDAO();
        return dAO.getLogbyuser(id);

    }

    @Override
    public int afficherNombreFormateurs() {
        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        try {
            return (int) formateurDAO.getAllFormateurs().stream().count();
        } catch (SQLException ex) {
            Logger.getLogger(IServiceFormateursImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int getRankFormateur(int id) {
        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        List<Formateur> fs = null;
        try {
            fs = formateurDAO.afficherTopFormateur();
            System.out.println(fs);
        } catch (SQLException ex) {
            Logger.getLogger(IServiceFormateursImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0;
        for (Formateur f : fs) {
            i++;
            System.out.println("-------formateur----" + f.getIdUtilisateur());
            if (f.getIdUtilisateur() == id) {
                return i;
            }
        }
        return i;
    }

    @Override
    public Map<String, Long> getValuesByMonthViews() {
        ISessionCoursDAO coursDAO = DAOFactory.getSessionCoursDAO();
        List<SessionCours> sc = coursDAO.listSessionCosulterParCoach(CurrentUser.getId());
        Map<String, Long> map = new HashMap<>();
      
        
       map.put(Month.JANUARY.toString(), sc.stream().filter(scours->scours.getDate_session().toLocalDate().getYear()==LocalDate.now().getYear() && scours.getDate_session().toLocalDate().getMonth().toString().equals(Month.JANUARY.toString())).count());
    map.put(Month.FEBRUARY.toString(), sc.stream().filter(scours->scours.getDate_session().toLocalDate().getYear()==LocalDate.now().getYear() && scours.getDate_session().toLocalDate().getMonth().toString().equals(Month.FEBRUARY.toString())).count());
    map.put(Month.MARCH.toString(), sc.stream().filter(scours->scours.getDate_session().toLocalDate().getYear()==LocalDate.now().getYear() && scours.getDate_session().toLocalDate().getMonth().toString().equals(Month.MARCH.toString())).count());
    map.put(Month.APRIL.toString(), sc.stream().filter(scours->scours.getDate_session().toLocalDate().getYear()==LocalDate.now().getYear() && scours.getDate_session().toLocalDate().getMonth().toString().equals(Month.APRIL.toString())).count());
    map.put(Month.MAY.toString(), sc.stream().filter(scours->scours.getDate_session().toLocalDate().getYear()==LocalDate.now().getYear() && scours.getDate_session().toLocalDate().getMonth().toString().equals(Month.MAY.toString())).count());
    map.put(Month.JUNE.toString(), sc.stream().filter(scours->scours.getDate_session().toLocalDate().getYear()==LocalDate.now().getYear() && scours.getDate_session().toLocalDate().getMonth().toString().equals(Month.JUNE.toString())).count());
    map.put(Month.JULY.toString(), sc.stream().filter(scours->scours.getDate_session().toLocalDate().getYear()==LocalDate.now().getYear() && scours.getDate_session().toLocalDate().getMonth().toString().equals(Month.JULY.toString())).count());
    map.put(Month.AUGUST.toString(), sc.stream().filter(scours->scours.getDate_session().toLocalDate().getYear()==LocalDate.now().getYear() && scours.getDate_session().toLocalDate().getMonth().toString().equals(Month.AUGUST.toString())).count());
    map.put(Month.SEPTEMBER.toString(), sc.stream().filter(scours->scours.getDate_session().toLocalDate().getYear()==LocalDate.now().getYear() && scours.getDate_session().toLocalDate().getMonth().toString().equals(Month.SEPTEMBER.toString())).count());
    map.put(Month.OCTOBER.toString(), sc.stream().filter(scours->scours.getDate_session().toLocalDate().getYear()==LocalDate.now().getYear() && scours.getDate_session().toLocalDate().getMonth().toString().equals(Month.OCTOBER.toString())).count());
    map.put(Month.NOVEMBER.toString(), sc.stream().filter(scours->scours.getDate_session().toLocalDate().getYear()==LocalDate.now().getYear() && scours.getDate_session().toLocalDate().getMonth().toString().equals(Month.NOVEMBER.toString())).count());
    map.put(Month.DECEMBER.toString(), sc.stream().filter(scours->scours.getDate_session().toLocalDate().getYear()==LocalDate.now().getYear() && scours.getDate_session().toLocalDate().getMonth().toString().equals(Month.DECEMBER.toString())).count());
        System.out.println(map);
               return map;
               }

    @Override
    public List<ScoreUtilisateur> getTop5Utilisateur() {
          IApprenantDAO apprenantDAO = DAOFactory.getApprenantDAO();
            List<ScoreUtilisateur> sc = null ;
        try {
           sc = apprenantDAO.getTopUtilisateur(CurrentUser.getId());
        } catch (SQLException ex) {
            Logger.getLogger(IServiceFormateursImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map<String, Long> map = new HashMap<>();
    sc.sort(Comparator.comparing(x->x.getNumbreOfSubs()));
     
        
        
      return sc ;
    }

}
