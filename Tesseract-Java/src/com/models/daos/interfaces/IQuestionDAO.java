/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces;
import com.models.entities.Question;
import java.util.List;

/**
 *
 * @author Bacem
 */
public interface IQuestionDAO {
    
    boolean createQuestion(Question question);
    boolean deleteQuestion(int id);
    boolean updateQuestion(Question question, int id);
    Question searchQuestion(int id);
    List<Question> displayQuestion();
    
}
