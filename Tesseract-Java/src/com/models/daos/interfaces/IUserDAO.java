/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.daos.interfaces;

import com.models.entities.Utilisateur;
import com.models.enums.Role;

/**
 *
 * @author BoB
 */
public interface IUserDAO {
    
    public void authenticateUser(String userName,String Pwd);
}
