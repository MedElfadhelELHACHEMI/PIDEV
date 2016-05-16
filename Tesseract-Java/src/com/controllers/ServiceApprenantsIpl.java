/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.database.CryptographieMOOC;
import com.models.daos.interfaces.ICoursDAO;
import com.models.daos.interfaces.ISessionCoursDAO;
import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IApprenantDAO;
import com.models.daos.interfaces.IChallengeDAO;
import com.models.daos.interfaces.IChapitreDAO;
import com.models.daos.interfaces.ICommentaireCoursDAO;
import com.models.daos.interfaces.IEpreuveEntrainementDAO;
import com.models.daos.interfaces.IEpreuveFinalDAO;
import com.models.daos.interfaces.IEpreuveObjectifDAO;
import com.models.daos.interfaces.IEvenementDAO;
import com.models.daos.interfaces.IFormateurDAO;
import com.models.daos.interfaces.IInscriptionChallengeDAO;
import com.models.daos.interfaces.IInscriptionEvenementDAO;
import com.models.daos.interfaces.IMatiereDAO;
import com.models.daos.interfaces.INotificationDAO;
import com.models.daos.interfaces.IObjectifDAO;
import com.models.daos.interfaces.IOrganisationDAO;
import com.models.daos.interfaces.IQuestionDAO;
import com.models.daos.interfaces.IReclamationDAO;
import com.models.daos.interfaces.IReponseDAO;
import com.models.daos.interfaces.ISessionEpreuveDAO;
import com.models.daos.interfaces.implementations.ImplSessionEpreuveDAO;
import com.models.entities.Apprenant;
import com.models.entities.Challenge;
import com.models.entities.Chapitre;
import com.models.entities.CommentaireCours;
import com.models.entities.Cours;
import com.models.entities.EpreuveEntrainement;
import com.models.entities.EpreuveFinal;
import com.models.entities.EpreuveObjectif;
import com.models.entities.Evenement;
import com.models.entities.Formateur;
import com.models.entities.InscriptionChallenge;
import com.models.entities.InscriptionEvenement;
import com.models.entities.Matiere;
import com.models.entities.Notification;
import com.models.entities.Objectif;
import com.models.entities.Organisation;
import com.models.entities.Question;
import com.models.entities.Reclamation;
import com.models.entities.Reponse;
import com.models.entities.SessionCours;
import com.models.entities.SessionEpreuve;
import com.models.enums.Etat;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Sameh
 */
public class ServiceApprenantsIpl implements ServiceApprenant {

    @Override
    public Cours chercherCoursByNameCours(String chaine, int idutilisateur) throws SQLException {
        ICoursDAO iCoursDAO = DAOFactory.getCoursDAO();
        Cours cours;
        cours = iCoursDAO.chercherCoursByNameCours(chaine, idutilisateur);
        return cours;
    }

    @Override
    public List<Cours> chercherCoursByNameFormateur(String chaine, int idutilisateur) throws SQLException {
        ICoursDAO iCoursDAO = DAOFactory.getCoursDAO();
        List<Cours> listCours;
        listCours = iCoursDAO.chercherCoursByNameFormateur(chaine, idutilisateur);

        return listCours;
    }

    @Override
    public List<Cours> chercherCoursByNameOrganisme(String chaine, int idutilisateur) throws SQLException {
        ICoursDAO iCoursDAO = DAOFactory.getCoursDAO();
        List<Cours> listCours;
        listCours = iCoursDAO.chercherCoursByNameOrganisme(chaine, idutilisateur);

        return listCours;
    }

    @Override
    public int afficherNombreVue(Cours cours) throws SQLException {
        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        int nb;
        nb = iSessionCoursDAO.afficherNombreVue(cours);
        return nb;
    }

    @Override
    public Apprenant getApprenantByLogin(String login) throws SQLException {
        IApprenantDAO apprenantDAO = DAOFactory.getApprenantDAO();
        Apprenant apprenant = apprenantDAO.getApprenantByLogin(login);
        return apprenant;

    }

    @Override
    public List<Formateur> getAllFormateurs() throws SQLException {
        IFormateurDAO iFormateurDAO = DAOFactory.getFormateurDAO();
        List<Formateur> listFormateur;
        listFormateur = iFormateurDAO.getAllFormateurs();
        return listFormateur;
    }

    @Override
    public List<Organisation> getAllOrganisation() throws SQLException {
        IOrganisationDAO iOrganisationDAO = DAOFactory.getOrganisationDAO();
        List<Organisation> listOrgan;
        listOrgan = iOrganisationDAO.displayOrganisation();
        return listOrgan;

    }

    @Override
    public List<Cours> findAllCours(int idutilisateur) throws SQLException {
        ICoursDAO iCoursDAO = DAOFactory.getCoursDAO();
        List<Cours> listCours;
        listCours = iCoursDAO.findAll(idutilisateur);

        return listCours;
    }

    @Override
    public List<Cours> getListeMesCoursTerminer(int idUtilisateur) throws SQLException {
        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        List<Cours> listCours;
        listCours = iSessionCoursDAO.getListeMesCoursTerminer(idUtilisateur);

        return listCours;
    }

    @Override
    public List<Cours> getListeMesCoursEnCours(int idUtilisateur) throws SQLException {
        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        List<Cours> listCours;
        listCours = iSessionCoursDAO.getListeMesCoursEnCours(idUtilisateur);

        return listCours;
    }

    @Override
    public List<Cours> chercherCoursByNameMatiere(String chaine, int idutilisateur) throws SQLException {
        ICoursDAO iCoursDAO = DAOFactory.getCoursDAO();
        List<Cours> listCours;
        listCours = iCoursDAO.chercherCoursByNameMatiere(chaine, idutilisateur);

        return listCours;
    }

    @Override
    public String getNameFormateur(Cours cours) throws SQLException {
        IFormateurDAO iFormateurDAO = DAOFactory.getFormateurDAO();
        String name;
        name = iFormateurDAO.getNameFormateur(cours);

        return name;

    }

    @Override
    public String getNameMatiere(Cours cours) throws SQLException {
        IMatiereDAO iMatiereDAO = DAOFactory.getMatiereDAO();
        String name;
        name = iMatiereDAO.getNameMatiere(cours);

        return name;
    }

    @Override
    public String getNameOrganisme(Cours cours) throws SQLException {
        IFormateurDAO iFormateurDAO = DAOFactory.getFormateurDAO();
        String name;
        name = iFormateurDAO.getNameOrganisme(cours);

        return name;
    }

    @Override
    public int nbChapitreByCours(Cours cours) throws SQLException {
        ICoursDAO iCoursDAO = DAOFactory.getCoursDAO();
        int nbChapitre;
        nbChapitre = iCoursDAO.nbChapitreByCours(cours);

        return nbChapitre;
    }

    @Override
    public int nbChapitreTerminerByCours(Cours cours, int idUtilisateur) throws SQLException {
        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        int nbChapTerminer = 0;
        nbChapTerminer = iSessionCoursDAO.nbChapitreTerminerByCours(cours, idUtilisateur);
        return nbChapTerminer;
    }

    @Override
    public List<Cours> chercherCoursByLoginApprenant(String chaine) throws SQLException {
        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        List<Cours> listCours;
        listCours = iSessionCoursDAO.chercherCoursByLoginApprenant(chaine);

        return listCours;
    }

    @Override
    public String LastCours(Cours cours, int idUtilisateur) throws SQLException {

        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        String lc = null;
        lc = iSessionCoursDAO.LastCours(cours, idUtilisateur);
        return lc;
    }

    @Override
    public int ScoreByCours(Cours cours, int idUtilisateur) throws SQLException {
        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        int score = 0;
        score = iSessionCoursDAO.ScoreByCours(cours, idUtilisateur);
        return score;
    }

    @Override
    public String getNameBadge(Cours cours, int idUtilisateur) throws SQLException {
        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        String Badge = "";
        Badge = iSessionCoursDAO.getNameBadge(cours, idUtilisateur);
        return Badge;
    }

    @Override
    public boolean verifInscrireCours(Cours cours, int idUtilisateur) throws SQLException {
        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        boolean verid;
        verid = iSessionCoursDAO.verifInscrireCours(cours, idUtilisateur);
        return verid;
    }

    @Override
    public ArrayList<Notification> ListNotificationByUserId(int idUtilisateur) throws SQLException {
        INotificationDAO notificationDAO = DAOFactory.getNotificationDAO();
        ArrayList<Notification> listNotifications;
        listNotifications = notificationDAO.displayNotificationNonVueByUserId(idUtilisateur);
        return listNotifications;
    }

    @Override
    public List<Notification> displayNotificationByDate(Date date, int id) throws SQLException {
        INotificationDAO notificationDAO = DAOFactory.getNotificationDAO();
        List<Notification> listNotifications;
        listNotifications = notificationDAO.displayNotificationByDate(date, id);
        return listNotifications;
    }

    @Override
    public boolean updateNotifcation(int id, String nom) throws SQLException {
        INotificationDAO notificationDAO = DAOFactory.getNotificationDAO();

        boolean test = notificationDAO.updateNotifcation(id, nom);
        return test;
    }

    @Override
    public boolean modifierApprenant(String login, Apprenant apprenant) throws SQLException {
        boolean verif = false;
        try {
            IApprenantDAO aO = DAOFactory.getApprenantDAO();
            String pwd = apprenant.getMotDePass();
            apprenant.setMotDePass(CryptographieMOOC.getCryptage().encrypt(pwd));
            verif = aO.modifierApprenant(apprenant.getNomUtilisateur(), apprenant);
        } catch (Exception ex) {
            Logger.getLogger(ServiceApprenantsIpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return verif;

    }

    @Override
    public boolean AjouterCommentaireCours(CommentaireCours n) throws SQLException {
        ICommentaireCoursDAO commentaireCoursDAO = DAOFactory.getCommentaireCoursDAO();
        boolean verif = false;
        verif = commentaireCoursDAO.AjouterCommentaireCours(n);
        return verif;
    }

    @Override
    public boolean ajouterSessionEpreuve(SessionEpreuve sessionEpreuve) throws SQLException {
        ISessionEpreuveDAO implSessionEpreuveDAO = DAOFactory.getSessionEpreuveDAO();
        boolean verif = false;
        verif = implSessionEpreuveDAO.ajouterSessionEpreuve(sessionEpreuve);
        return verif;
    }

    @Override
    public boolean modifierSessionEpreuve(SessionEpreuve sessionEpreuve) throws SQLException {
        ISessionEpreuveDAO implSessionEpreuveDAO = DAOFactory.getSessionEpreuveDAO();
        boolean verif = false;
        verif = implSessionEpreuveDAO.modifierSessionEpreuve(sessionEpreuve);
        return verif;
    }

    @Override
    public EpreuveFinal searchEpreuveFinalByCours(int idcours) throws SQLException {
        EpreuveFinal ef;
        IEpreuveFinalDAO aO = DAOFactory.getEpreuveFinalDAO();
        ef = aO.searchEpreuveFinalByCours(idcours);
        return ef;
    }

    @Override
    public List<Reponse> findReponseByIdQuestion(int idQuestion) throws SQLException {
        IReponseDAO iReponseDAO = DAOFactory.getReponseDAO();
        List<Reponse> listReponse;
        listReponse = iReponseDAO.findReponseByIdQuestion(idQuestion);
        return listReponse;
    }

    @Override
    public List<Question> findQuestionByIdEpreuve(int Epreuve) throws SQLException {
        IQuestionDAO iQuestionDAO = DAOFactory.getQuestionDAO();
        List<Question> listQuestion;
        listQuestion = iQuestionDAO.findQuestionByIdEpreuve(Epreuve);
        return listQuestion;
    }

    @Override
    public boolean modifierBadgeCours(Cours cours, int idUtilisateur) throws SQLException {
        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        boolean verid;
        verid = iSessionCoursDAO.modifierBadgeCours(cours, idUtilisateur);
        return verid;
    }

    @Override
    public List<EpreuveEntrainement> searchEpreuveEntrainementByCours(int idcours) throws SQLException {
        List<EpreuveEntrainement> ef;
        IEpreuveEntrainementDAO aO = DAOFactory.getEpreuveEntrainementDAO();
        ef = aO.searchEpreuveEntrainementByCours(idcours);
        return ef;
    }

    @Override
    public List<EpreuveObjectif> searchEpreuveOBJByObj(int idobj) throws SQLException {
        List<EpreuveObjectif> ef;
        IEpreuveObjectifDAO aO = DAOFactory.getEpreuveObjectifDAO();
        ef = aO.searchEpreuveOBJByObj(idobj);
        return ef;
    }

    @Override
    public Objectif searchOBJByChapitre(int number, int idChapitre) throws SQLException {
        IObjectifDAO iObjectifDAO = DAOFactory.getObjectifDAO();
        Objectif objectif = iObjectifDAO.searchOBJByChapitre(number, idChapitre);
        return objectif;
    }

    @Override
    public List<Chapitre> findChapitreByIdCours(int idCours) throws SQLException {
        List<Chapitre> listeChapitre;
        IChapitreDAO chapitreDAO = DAOFactory.getChapitreDAO();
        listeChapitre = chapitreDAO.findChapitreByIdCours(idCours);
        return listeChapitre;
    }

    @Override
    public boolean modifierNombreChapCours(Cours cours, int idUtilisateur, int nbr) throws SQLException {
        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        boolean verid;
        verid = iSessionCoursDAO.modifierNombreChapCours(cours, idUtilisateur, nbr);
        return verid;

    }

    @Override
    public List<SessionEpreuve> getSessionEpreuvebyidCoursUtil(int idCours, int idUtilisateur) throws SQLException {
        List<SessionEpreuve> list;
        ISessionEpreuveDAO implSessionEpreuve = DAOFactory.getSessionEpreuveDAO();
        list = implSessionEpreuve.getSessionEpreuvebyidCoursUtil(idCours, idUtilisateur);
        return list;
    }

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
    public List<Matiere> getAllMatiere() throws SQLException {
        IMatiereDAO iMatiere = DAOFactory.getMatiereDAO();
        List<Matiere> list = iMatiere.findAll();
        return list;

    }

    @Override
    public boolean ajouterSessionCours(SessionCours SessionCours) {
        ISessionCoursDAO iSessionCoursDAO = DAOFactory.getSessionCoursDAO();
        boolean verid;
        verid = iSessionCoursDAO.ajouterSessionCours(SessionCours);
        return verid;
    }

    @Override
    public Formateur getFormateur(Cours cours) throws SQLException {
        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        Formateur formateur = formateurDAO.getFormateur(cours);
        return formateur;
    }

    @Override
    public Formateur getFormateurByName(String nom) throws SQLException {
        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        Formateur formateur = formateurDAO.getFormateurByName(nom);
        return formateur;
    }

    @Override
    public Organisation getOrganisationByName(String nom) throws SQLException {
        IOrganisationDAO iOrganisationDAO = DAOFactory.getOrganisationDAO();
        Organisation Organ;
        Organ = iOrganisationDAO.getOrganisationByName(nom);
        return Organ;
    }

    @Override
    public List<Evenement> displayByIdOrganisation(int idOrg) throws SQLException {
        IEvenementDAO evenementDAO = DAOFactory.getEvenementDAO();
        List<Evenement> evenements = evenementDAO.displayByIdOrganisation(idOrg);
        return evenements;
    }

    @Override
    public List<Evenement> displayEvenement(int id) throws SQLException {
        IEvenementDAO evenementDAO = DAOFactory.getEvenementDAO();
        List<Evenement> evenements = evenementDAO.displayEvenement(id);
        return evenements;
    }

    @Override
    public List<Challenge> displayChallengeByFormateur(int idChl) {
        IChallengeDAO challengeDAO = DAOFactory.getChallengeDAO();
        List<Challenge> challenges = challengeDAO.displayChallengeByFormateur(idChl);
        return challenges;
    }

    @Override
    public List<Challenge> displayChallengeByOrganisation(int idChl) {
        IChallengeDAO challengeDAO = DAOFactory.getChallengeDAO();
        List<Challenge> challenges = challengeDAO.displayChallengeByOrganisation(idChl);
        return challenges;
    }

    @Override
    public List<Challenge> displayChallenge(int id) {
        IChallengeDAO challengeDAO = DAOFactory.getChallengeDAO();
        List<Challenge> challenges = challengeDAO.displayChallenge(id);
        return challenges;
    }

    @Override
    public boolean ajouterInscriptionEvenement(InscriptionEvenement challenge) throws SQLException {
        IInscriptionEvenementDAO evenementDAO = DAOFactory.getInscriptionEvenementDAO();
        boolean verif = evenementDAO.ajouterInscriptionEvenement(challenge);
        return verif;
    }

    @Override
    public List<InscriptionEvenement> getInscriptionEvenementbyUserid(int idUser) {
        IInscriptionEvenementDAO evenementDAO = DAOFactory.getInscriptionEvenementDAO();
          List<InscriptionEvenement> evenements = new ArrayList<>();
        try {
            evenements = evenementDAO.getInscriptionEvenementbyUserid(idUser);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceApprenantsIpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evenements;
    }

    @Override
    public int nbrUtilisateurinscritEvenement(int idEvenement) {
        IInscriptionEvenementDAO evenementDAO = DAOFactory.getInscriptionEvenementDAO();
        int nb = 0 ;
        try {
            nb = evenementDAO.nbrUtilisateurInscrit(idEvenement);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceApprenantsIpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nb;
    }

    @Override
    public boolean ajouterInscriptionChallenge(InscriptionChallenge challenge) throws SQLException {
        IInscriptionChallengeDAO challengeDAO = DAOFactory.getInscriptionChallengeDAO();
        boolean verif = challengeDAO.ajouterInscriptionChallenge(challenge);
        return verif;
    }

    @Override
    public List<InscriptionChallenge> getInscriptionChallengebyUserid(int idUser) {
        IInscriptionChallengeDAO challengeDAO = DAOFactory.getInscriptionChallengeDAO();
            List<InscriptionChallenge> challenges = new ArrayList<>();
        try {
            challenges = challengeDAO.getInscriptionChallengebyUserid(idUser);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceApprenantsIpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return challenges;
    }

    @Override
    public int nbrUtilisateurinscritChallenge(int idChallenge) {
        IInscriptionChallengeDAO challengeDAO = DAOFactory.getInscriptionChallengeDAO();
        int nb= 0 ;
        try {
            nb = challengeDAO.nbrUtilisateurInscrit(idChallenge);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceApprenantsIpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nb;
    }

    @Override
    public Organisation getOrganisationByid(int id) {
        IOrganisationDAO iOrganisationDAO = DAOFactory.getOrganisationDAO();
        Organisation Organ;
        Organ = iOrganisationDAO.getOrganisationByid(id);
        return Organ;
    }

    @Override
    public List<Evenement> getEvenementByNomOrganisation(String nom, int id) {
        
        List<Evenement> evenements  = new ArrayList<>();
        try {
            IEvenementDAO evenementDAO = DAOFactory.getEvenementDAO();
           evenements = evenementDAO.getEvenementByNomOrganisation(nom, id);
            return evenements;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceApprenantsIpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evenements ;
    }

    @Override
    public List<Challenge> getChallengeByNomOrganisation(String nom, int id) {
        IChallengeDAO challengeDAO = DAOFactory.getChallengeDAO();
        List<Challenge> challenges = challengeDAO.getChallengeByNomOrganisation(nom, id);
        return challenges;
    }

    @Override
    public List<Evenement> displayEvenementUtilisateur(int id) throws SQLException {
        IEvenementDAO evenementDAO = DAOFactory.getEvenementDAO();
        List<Evenement> evenements = evenementDAO.displayEvenementUtilisateur(id);
        return evenements;
    }

    @Override
    public List<Challenge> displayChallengeUtilisateur(int id) {
        IChallengeDAO challengeDAO = DAOFactory.getChallengeDAO();
        List<Challenge> challenges = challengeDAO.displayChallengeUtilisateur(id);
        return challenges;
    }

    @Override
    public List<Evenement> displayByDate(Date date, int id) throws SQLException {
        IEvenementDAO evenementDAO = DAOFactory.getEvenementDAO();
        List<Evenement> evenements = evenementDAO.displayByDate(date, id);
        return evenements;
    }

    @Override
    public List<Challenge> displayChallengeByDate(Date date, int id) {
        IChallengeDAO challengeDAO = DAOFactory.getChallengeDAO();
        List<Challenge> challenges = challengeDAO.displayChallengeByDate(date, id);
        return challenges;
    }

    @Override
    public boolean ajouterReclamation(Reclamation reclamation, int idUtilisateur) throws SQLException {
        IReclamationDAO iReclamationDAO = DAOFactory.getReclamationDAO();
        boolean verif = iReclamationDAO.ajouterReclamation(reclamation, idUtilisateur);
        return verif;
    }

    @Override
    public Formateur getFormateurByLogin(String login) throws SQLException {
        IFormateurDAO iFormateurDAO = DAOFactory.getFormateurDAO();
        Formateur listFormateur;
        listFormateur = iFormateurDAO.getFormateurByLogin(login);
        return listFormateur;
    }

}
