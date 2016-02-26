/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.IUserDAO;
import com.database.DataSource;
import com.models.enums.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author BoB
 */
public class ImplUserDAO implements IUserDAO {
     private Connection connection;
     private String role;
     private int id;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     

    public ImplUserDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void authenticateUser(String userName, String Pwd) {
        String query = "select role,id from utilisateur where pseudo='"+ userName +"' and mdp='"+ Pwd +"'";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                setRole(resultat.getString(1));
                setId(resultat.getInt(2));
        }
        } 
        catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du user " + ex.getMessage());
            
        }
    }
    
}
