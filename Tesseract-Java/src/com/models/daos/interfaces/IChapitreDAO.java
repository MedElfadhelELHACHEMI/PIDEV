package com.models.daos.interfaces;

import com.models.entities.Chapitre;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;


public interface IChapitreDAO {
    
    public boolean addChapitre(Chapitre c);
    
    public boolean deleteChapitre(int id);
    
    public boolean updateChapitre(Chapitre c);
    
    public ObservableList<Chapitre> displayChapitre(int idCours);
    
    public Chapitre searchChapitre(String nom, int idCours);
    
    public boolean isNumberThere(int number, int idCours);

    public List<Chapitre> findChapitreByIdCours(int idCours)throws SQLException;
}
