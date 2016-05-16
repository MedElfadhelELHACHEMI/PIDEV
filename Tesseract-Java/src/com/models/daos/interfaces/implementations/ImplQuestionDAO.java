/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.IQuestionDAO;
import com.database.DataSource;
import com.models.entities.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bacem
 */
public class ImplQuestionDAO implements IQuestionDAO{

    Connection connection;
    PreparedStatement pst;
    ResultSet rS;

    public ImplQuestionDAO(){
    connection =(DataSource.getInstance()).getConnection();
}
    @Override
    public boolean createQuestion(Question question) {
        try {
            String request="insert into questions(question, id_epreuve) values (?,?)";
            pst = connection.prepareStatement(request);
            pst.setString(1, question.getQuestion());
            pst.setInt(2, question.getIdEpreuve());
            
            int result = pst.executeUpdate();
            pst.close();
            
            return (result==1);
        } catch (SQLException ex) {
            Logger.getLogger(ImplQuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteQuestion(int id) {
        try {
            String request = "delete from questions where id="+id;
            int result= pst.executeUpdate(request);
            pst.close();
            return (result==1);
        } catch (SQLException ex) {
            Logger.getLogger(ImplQuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateQuestion(Question question, int id) {
        try {
            String request="update questions set question=?, id_epreuve=? where id=?";
            pst = connection.prepareStatement(request);
            pst.setString(1, question.getQuestion());
            pst.setInt(2, question.getIdEpreuve());
            pst.setInt(3, id);
            
            int result = pst.executeUpdate();
            pst.close();
            return result==1;
        } catch (SQLException ex) {
            Logger.getLogger(ImplQuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Question searchQuestion(int id) {
        Question question = new Question();
        try {
            String request ="select * from questions where id=?";
            rS = pst.executeQuery(request);
            rS.next();
            question.setId(rS.getInt("id"));
            question.setIdEpreuve(rS.getInt("id_epreuve"));
            question.setQuestion(rS.getString("question"));
        } catch (SQLException ex) {
            Logger.getLogger(ImplQuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }

    @Override
    public List<Question> displayQuestion() {
        List<Question> questions = new ArrayList<>();
        try {
            String request="select * from epreuves";
            pst = connection.prepareStatement(request);
            rS=pst.executeQuery();
            while(rS.next()){
                Question q= new Question(rS.getInt(1), rS.getString("question"), rS.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplQuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    @Override
    public List<Question> findQuestionByIdEpreuve(int Epreuve) throws SQLException {
     List<Question> listeQuestion = new ArrayList<>();
        String requete = "select * from question where id_epreuve=?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1, Epreuve);
        ResultSet resultat = ps.executeQuery();
     
        while (resultat.next()) {
           Question  question = new Question();
           
              question.setId(resultat.getInt(1));
              question.setQuestion(resultat.getString(3));
              question.setIdEpreuve(Epreuve);
            listeQuestion.add(question);
            
        }
        if (Objects.nonNull(listeQuestion)) {
            return listeQuestion;
        }

        throw new UnsupportedOperationException();   
    }
    
}
