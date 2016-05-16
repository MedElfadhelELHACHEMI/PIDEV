/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.entities;

/**
 *
 * @author Sameh
 */
public class HistoriqueQuestion {
   private int id_his_question;
    private int correct;
    private int id_his_rep;
    

    public HistoriqueQuestion() {
    }

    public int getId_his_question() {
        return id_his_question;
    }

    public int getCorrect() {
        return correct;
    }

    public int getId_his_rep() {
        return id_his_rep;
    }

    public void setId_his_question(int id_his_question) {
        this.id_his_question = id_his_question;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public void setId_his_rep(int id_his_rep) {
        this.id_his_rep = id_his_rep;
    }

    @Override
    public String toString() {
        return "HistoriqueQuestion{" + "id_his_question=" + id_his_question + ", correct=" + correct + ", id_his_rep=" + id_his_rep + '}';
    }
    
    
}
