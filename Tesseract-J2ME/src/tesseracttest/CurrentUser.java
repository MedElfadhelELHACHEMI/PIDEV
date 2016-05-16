/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesseracttest;

/**
 *
 * @author haikal
 */
public class CurrentUser {
    private static int id ;
    private static String role ;

    public static int getId() {
        return id=1;
    }

    public static void setId(int id) {
        CurrentUser.id = id;
    }

    public static String getRole() {
        return role="FOR";
    }

    public static void setRole(String role) {
        CurrentUser.role = role;
    }
    
}
