package com.views.controllers;

import com.models.entities.Utilisateur;
import com.models.enums.Role;

/**
 *
 * @author Noor
 */
public class CurrentUser {

    private static int id;
    private static Role role;
    private static Utilisateur utilisateur;

    @Override
    public String toString() {
        return "id" + id + "role" + role;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        CurrentUser.id = id;
    }

    public static Role getRole() {
        return role;
    }

    public static void setRole(Role role) {
        CurrentUser.role = role;
    }

    public static Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public static void setUtilisateur(Utilisateur utilisateur) {
        CurrentUser.utilisateur = utilisateur;
    }

}
