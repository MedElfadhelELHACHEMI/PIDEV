package com.models.daos.interfaces;

import com.models.entities.Administrateur;
import com.models.entities.Apprenant;
import com.models.entities.Cours;
import com.models.entities.Formateur;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

public interface IFormateurDAO {

    public boolean ajouterFormateur(Formateur apprenant, Integer idOrganisme) throws SQLException;

    public Formateur getFormateurByLogin(String login) throws SQLException;

    public boolean supprimerFormateurByLogin(String login) throws SQLException;

    public List<Formateur> getAllFormateurs() throws SQLException;

    public boolean modifierFormateur(String login, Formateur newFormateur) throws SQLException;

    public boolean affecterOrganismeFormateur(int i, int i0);

    public Formateur getFormateurById(int i);

    public boolean modifierProfil(String nom, String prenom, String mail, String adresse, int tel, int id);

    public boolean modifierProfilWithPwd(String nom, String prenom, String mail, String adresse, int tel, String motDePass, int id);

    List<Formateur> afficherTopFormateur() throws SQLException;

    public String getNameFormateur(Cours cours) throws SQLException;

    public String getNameOrganisme(Cours cours) throws SQLException;

    public Formateur getFormateur(Cours cours) throws SQLException;

    public Formateur getFormateurByName(String nom) throws SQLException;

    public ObservableList<Formateur> findAllAtt() ;

   

    public void rejeterFormateur(int idUtilisateur)throws SQLException;

    public void accepterFormateur(int idUtilisateur)throws SQLException;

    public List<Formateur> getFormateurAndScore()throws SQLException;

    public List<Formateur> getAllFormateursAtt()throws SQLException;

    public void accRefFormateur(Formateur f, int i);

    public boolean ajouterFormateurWithOrganization(Formateur formateur, Object object)throws SQLException;;

}
