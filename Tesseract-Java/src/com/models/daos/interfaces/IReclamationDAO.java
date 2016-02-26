package com.models.daos.interfaces;

import com.models.entities.Reclamation;
import java.sql.SQLException;
import java.util.List;

public interface IReclamationDAO {

    public boolean ajouterReclamation(Reclamation reclamation, int idUtilisateur)throws SQLException;

    public List<Reclamation> getReclamationsByIdUser(int idUtilisateur)throws SQLException;

    public boolean modifierReclamation(int idReclamation, Reclamation newReclamation)throws SQLException;

}
