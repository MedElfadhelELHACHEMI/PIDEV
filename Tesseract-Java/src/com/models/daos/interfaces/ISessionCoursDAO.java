package com.models.daos.interfaces;

import com.models.entities.Cours;
import com.models.entities.SessionCours;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


public interface ISessionCoursDAO {
    
    public boolean ajouterSessionCours(SessionCours SessionCours);
    
    public List<SessionCours> getSessionCoursbyUserid(int idUser);
    
    public List<SessionCours> getSessionCoursbyCoursid(int idCours);
    
    public List<SessionCours> getSessionCoursbyDate(Date date);

    public List<SessionCours> listSessionCosulterParCoach(int id);

    public int afficherNombreVue(Cours cours)throws SQLException;

    public List<Cours> getListeMesCoursTerminer(int idUtilisateur)throws SQLException;

    public List<Cours> getListeMesCoursEnCours(int idUtilisateur)throws SQLException;

    public int nbChapitreTerminerByCours(Cours cours, int idUtilisateur)throws SQLException;

    public List<Cours> chercherCoursByLoginApprenant(String chaine)throws SQLException;

    public String LastCours(Cours cours, int idUtilisateur)throws SQLException;

    public int ScoreByCours(Cours cours, int idUtilisateur)throws SQLException;

    public String getNameBadge(Cours cours, int idUtilisateur)throws SQLException;

    public boolean verifInscrireCours(Cours cours, int idUtilisateur)throws SQLException;

    public boolean modifierBadgeCours(Cours cours, int idUtilisateur)throws SQLException;

    public boolean modifierNombreChapCours(Cours cours, int idUtilisateur, int nbr)throws SQLException;
}
