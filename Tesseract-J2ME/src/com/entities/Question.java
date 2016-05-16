/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.entities;

/**
 *
 * @author Sameh
 */
public class Question {
 private int id;
    private String question;
    private int idEpreuve;

    public Question(int id, String question, int idEpreuve) {
        this.id = id;
        this.question = question;
        this.idEpreuve = idEpreuve;
    }

    public int getIdEpreuve() {
        return idEpreuve;
    }

    public void setIdEpreuve(int idEpreuve) {
        this.idEpreuve = idEpreuve;
    }

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

   

   
    public String toString() {
        return "Question{" + "id=" + id + ", question=" + question + '}';
    }
    
}
