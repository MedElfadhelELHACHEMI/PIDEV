/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.models.entities.Cours;
import com.models.entities.Formateur;
import com.models.entities.Organisation;
import com.models.entities.Utilisateur;
import java.util.List;

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
}
