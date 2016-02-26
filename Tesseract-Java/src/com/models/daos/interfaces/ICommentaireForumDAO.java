package com.models.daos.interfaces;

import com.models.entities.CommentaireForum;
import com.models.entities.SujetForum;
import com.models.entities.Utilisateur;
import java.sql.SQLException;
import java.util.List;


public interface ICommentaireForumDAO {
    public boolean addCommentaireForum(CommentaireForum cmntfrm) throws SQLException;
    
    public boolean deleteCommentaireForum(int idCmnt) throws SQLException;
    
    boolean updateCommentaireForum(CommentaireForum cmntfrm)throws SQLException;
        
    public List<CommentaireForum> displayCommentaireForum()throws SQLException;
    
    public List<CommentaireForum> getCommentaireForumBySujet(SujetForum sujetf)throws SQLException;
    
    public List<CommentaireForum> getCommentaireForumByUtilisateur(Utilisateur user)throws SQLException;
}
