package com.models.daos.interfaces;

import com.models.entities.CommentaireCours;
import java.sql.SQLException;


public interface ICommentaireCoursDAO {

    public Object afficherCommentaireByCours(int i);

    public boolean AjouterCommentaireCours(CommentaireCours n)throws SQLException;


}
