package com.models.entities;

import com.database.ArrayToString;
import com.models.enums.Role;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public  class Utilisateur {

    protected int idUtilisateur;

    protected String nomUtilisateur;
    protected String motDePass;
    protected String nom;
    protected String prenom;
    protected Date dateNaissance;
    protected int tel;
    protected String adresse;
    protected String mail;
    protected String photo;
    protected Role role;
    protected String username_canonical ;
    protected String email_canonical ;
    protected int enabled ;
    protected String slat ;
    protected int locked ; 
    protected int expires_at ;
    protected int credentials_expired  ;
    
    
   
    protected List<SujetForum> sujetForumList;

    protected List<Log> logList;

    protected List<Invitation> invitationList;

    protected List<InscriptionChallenge> inscriptionChallengeList;

    protected List<BanForum> banForumList;

    protected List<SessionCours> sessionCoursList;

    protected List<InvitationEvenement> inscriptionEvenementList;

    protected List<Notification> notificationList;

    protected Organisation idOrganisation;

    protected List<CommentaireForum> commentraireForumList;

    protected List<Reclamation> reclamationList;

    protected List<SessionEpreuve> sessionEpreuveList;

    public Utilisateur(int idUtilisateur, String nomUtilisateur, String motDePass, String nom, String prenom, Date dateNaissance, int tel, String adresse, String mail, String photo) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePass = motDePass;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.tel = tel;
        this.adresse = adresse;
        this.mail = mail;
        this.photo = photo;
        
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Utilisateur() {
    }

    public Utilisateur(String nomUtilisateur, String motDePass, String nom, String prenom, Date dateNaissance, int tel, String adresse, String mail, String photo) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePass = motDePass;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.tel = tel;
        this.adresse = adresse;
        this.mail = mail;
        this.photo = photo;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePass() {
        return motDePass;
    }

    public void setMotDePass(String motDePass) {
        this.motDePass = motDePass;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoto() {
        return "C:\\wamp\\www\\symf\\Tesseract-Symfony\\Tesseract-Symfony\\web\\uploads\\pictures\\"+photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idUtilisateur;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        if (this.idUtilisateur != other.idUtilisateur) {
            return false;
        }
        return true;
    }

    public List<SujetForum> getSujetForumList() {
        return sujetForumList;
    }

    public void setSujetForumList(List<SujetForum> sujetForumList) {
        this.sujetForumList = sujetForumList;
    }

    public List<Log> getLogList() {
        return logList;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    public List<Invitation> getInvitationList() {
        return invitationList;
    }

    public void setInvitationList(List<Invitation> invitationList) {
        this.invitationList = invitationList;
    }

    public List<InscriptionChallenge> getInscriptionChallengeList() {
        return inscriptionChallengeList;
    }

    public void setInscriptionChallengeList(List<InscriptionChallenge> inscriptionChallengeList) {
        this.inscriptionChallengeList = inscriptionChallengeList;
    }

    public List<BanForum> getBanForumList() {
        return banForumList;
    }

    public void setBanForumList(List<BanForum> banForumList) {
        this.banForumList = banForumList;
    }

    public List<SessionCours> getSessionCoursList() {
        return sessionCoursList;
    }

    public void setSessionCoursList(List<SessionCours> sessionCoursList) {
        this.sessionCoursList = sessionCoursList;
    }

    public List<InvitationEvenement> getInscriptionEvenementList() {
        return inscriptionEvenementList;
    }

    public void setInscriptionEvenementList(List<InvitationEvenement> inscriptionEvenementList) {
        this.inscriptionEvenementList = inscriptionEvenementList;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public Organisation getIdOrganisation() {
        return idOrganisation;
    }

    public void setIdOrganisation(Organisation idOrganisation) {
        this.idOrganisation = idOrganisation;
    }

    public List<CommentaireForum> getCommentraireForumList() {
        return commentraireForumList;
    }

    public void setCommentraireForumList(List<CommentaireForum> commentraireForumList) {
        this.commentraireForumList = commentraireForumList;
    }

    public List<Reclamation> getReclamationList() {
        return reclamationList;
    }

    public void setReclamationList(List<Reclamation> reclamationList) {
        this.reclamationList = reclamationList;
    }

    public List<SessionEpreuve> getSessionEpreuveList() {
        return sessionEpreuveList;
    }

    public void setSessionEpreuveList(List<SessionEpreuve> sessionEpreuveList) {
        this.sessionEpreuveList = sessionEpreuveList;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getSlat() {
        return slat;
    }

    public void setSlat(String slat) {
        this.slat = slat;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public int getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(int expires_at) {
        this.expires_at = expires_at;
    }

    public int getCredentials_expired() {
        return credentials_expired;
    }

    public void setCredentials_expired(int credentials_expired) {
        this.credentials_expired = credentials_expired;
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(prenom.substring(0, 1).toUpperCase() + prenom.substring(1)+ ' '+ nom.substring(0, 1).toUpperCase() + nom.substring(1));
    }
    
    @Override
    public String toString() {
        return "Utilisateur{" + "idUtilisateur=" + idUtilisateur + ", nomUtilisateur=" + nomUtilisateur + ", motDePass=" + motDePass + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", tel=" + tel + ", adresse=" + adresse + ", mail=" + mail + ", photo=" + photo + '}';
    }

}
