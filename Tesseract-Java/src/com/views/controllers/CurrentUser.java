
package com.views.controllers;

import com.models.enums.Role;

/**
 *
 * @author Noor
 */
public class CurrentUser {
    public static int id;
    public static Role role;

    @Override
    public String toString() {
        return "id"+id+"role"+role;
    }
    
    
    
}
