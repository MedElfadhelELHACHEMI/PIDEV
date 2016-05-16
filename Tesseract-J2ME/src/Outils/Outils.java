/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Outils;

/**
 *
 * @author Iskander
 */
public class Outils {
    
    //remplace les espaces par +
    public static String adaptStringToURL(String chaine){
       String chaine2= new String();
            for (int i= 0; i <chaine.length();i++){
               if (chaine.charAt(i) ==' ')
                chaine2+='+';
               else
                chaine2+=chaine.charAt(i);
            }
        return chaine2;
    }
    
    
    
     public static String adaptDescripToURL(String chaine){
       String chaine2= new String();
            for (int i= 0; i <chaine.length();i++){
               if (chaine.charAt(i) ==' ')
                chaine2+="%20";
               else
                chaine2+=chaine.charAt(i);
            }
        return chaine2;
    }
    
     
}
