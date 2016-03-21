/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.models.entities.Cours;
import com.models.entities.Formateur;
import com.models.entities.Log;
import com.models.entities.Organisation;
import com.models.entities.ScoreUtilisateur;
import com.models.entities.SessionCours;
import com.models.entities.Utilisateur;
import java.util.List;
import java.util.Map;

/**
 *
 * @author haikal
 */
public interface IServiceFormateurs {
   List<Cours> getCoursesACCCoach(int idCoach);

    public Organisation getOrganisationCoach(Utilisateur utilisateur);

    public boolean updateFormateurInformations(Formateur formateur);

    public Utilisateur getUtilisateurById(int id);

    public boolean updateFormateurInformationsWithPass(Formateur formateur);

    public List<SessionCours> getListCoursConsulted(int id);

    public List<Log> getLoginUtilisateur(int id);

    public int afficherNombreFormateurs();

    public int getRankFormateur(int id);

    public Map<String, Long> getValuesByMonthViews();

    public List<ScoreUtilisateur> getTop5Utilisateur();
}
