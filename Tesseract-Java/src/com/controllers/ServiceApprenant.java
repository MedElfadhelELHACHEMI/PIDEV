/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.models.entities.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public interface ServiceApprenant {

    public boolean ajouterReclamation(Reclamation reclamation, int idUtilisateur) throws SQLException;

    public List<Evenement> displayEvenementUtilisateur(int id) throws SQLException;

    public List<Challenge> displayChallengeUtilisateur(int id);

    public List<Evenement> displayByIdOrganisation(int idOrg) throws SQLException;

    public List<Evenement> displayEvenement(int id) throws SQLException;

    public List<Challenge> displayChallengeByFormateur(int idChl);

    public List<Challenge> displayChallengeByOrganisation(int idChl);

    public List<Challenge> displayChallenge(int id);

    public Organisation getOrganisationByid(int id);

    public Formateur getFormateurByLogin(String login) throws SQLException;

    public List<Challenge> displayChallengeByDate(Date date, int id);

    public List<Evenement> displayByDate(Date date, int id) throws SQLException;

    public List<Challenge> getChallengeByNomOrganisation(String nom, int id);

    public List<Evenement> getEvenementByNomOrganisation(String nom, int id);

    public boolean ajouterInscriptionEvenement(InscriptionEvenement challenge) throws SQLException;

    public List<InscriptionEvenement> getInscriptionEvenementbyUserid(int idUser);

    public int nbrUtilisateurinscritEvenement(int idEvenement);

    public boolean ajouterInscriptionChallenge(InscriptionChallenge challenge) throws SQLException;

    public List<InscriptionChallenge> getInscriptionChallengebyUserid(int idUser);

    public int nbrUtilisateurinscritChallenge(int idChallenge);

    public Cours chercherCoursByNameCours(String chaine, int idutilisateur) throws SQLException;

    public List<Cours> chercherCoursByNameFormateur(String chaine, int idutilisateur) throws SQLException;

    public List<Cours> chercherCoursByNameOrganisme(String chaine, int idutilisateur) throws SQLException;

    public int afficherNombreVue(Cours cours) throws SQLException;

    public Formateur getFormateur(Cours cours) throws SQLException;

    public Apprenant getApprenantByLogin(String login) throws SQLException;

    public List<Formateur> getAllFormateurs() throws SQLException;

    public List<Organisation> getAllOrganisation() throws SQLException;

    public List<Matiere> getAllMatiere() throws SQLException;

    public Formateur getFormateurByName(String nom) throws SQLException;

    public List<Cours> findAllCours(int idutilisateur) throws SQLException;

    public List<Cours> getListeMesCoursTerminer(int idUtilisateur) throws SQLException;

    public List<Cours> getListeMesCoursEnCours(int idUtilisateur) throws SQLException;

    public int ScoreByCours(Cours cours, int idUtilisateur) throws SQLException;

    public int nbChapitreByCours(Cours cours) throws SQLException;

    public Organisation getOrganisationByName(String nom) throws SQLException;

    public int nbChapitreTerminerByCours(Cours cours, int idUtilisateur) throws SQLException;

    public List<Cours> chercherCoursByLoginApprenant(String chaine) throws SQLException;

    public List<Cours> chercherCoursByNameMatiere(String chaine, int idutilisateur) throws SQLException;

    public String getNameFormateur(Cours cours) throws SQLException;

    public String getNameMatiere(Cours cours) throws SQLException;

    public String getNameOrganisme(Cours cours) throws SQLException;

    public String LastCours(Cours cours, int idUtilisateur) throws SQLException;

    public String getNameBadge(Cours cours, int idUtilisateur) throws SQLException;

    public boolean ajouterSessionCours(SessionCours SessionCours);

    public boolean verifInscrireCours(Cours cours, int idUtilisateur) throws SQLException;

    public ArrayList<Notification> ListNotificationByUserId(int idUtilisateur) throws SQLException;

    public List<Notification> displayNotificationByDate(Date date, int id) throws SQLException;

    public boolean updateNotifcation(int id, String nom) throws SQLException;

    public boolean modifierApprenant(String login, Apprenant apprenant) throws SQLException;

    public boolean AjouterCommentaireCours(CommentaireCours n) throws SQLException;

    public boolean ajouterSessionEpreuve(SessionEpreuve sessionEpreuve) throws SQLException;

    public boolean modifierSessionEpreuve(SessionEpreuve sessionEpreuve) throws SQLException;

    public EpreuveFinal searchEpreuveFinalByCours(int idcours) throws SQLException;

    public List<Reponse> findReponseByIdQuestion(int idQuestion) throws SQLException;

    public List<Question> findQuestionByIdEpreuve(int Epreuve) throws SQLException;

    public boolean modifierBadgeCours(Cours cours, int idUtilisateur) throws SQLException;

    public List<EpreuveEntrainement> searchEpreuveEntrainementByCours(int idcours) throws SQLException;

    public List<EpreuveObjectif> searchEpreuveOBJByObj(int idobj) throws SQLException;

    List<Cours> getCoursesACCCoach(int idCoach);

    public Objectif searchOBJByChapitre(int number, int idChapitre) throws SQLException;

    public List<Chapitre> findChapitreByIdCours(int idCours) throws SQLException;

    public boolean modifierNombreChapCours(Cours cours, int idUtilisateur, int nbr) throws SQLException;

    public List<SessionEpreuve> getSessionEpreuvebyidCoursUtil(int idCours, int idUtilisateur) throws SQLException;

}
