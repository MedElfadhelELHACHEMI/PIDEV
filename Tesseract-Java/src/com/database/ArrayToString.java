/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import com.models.enums.Role;

/**
 *
 * @author haikal
 */
public class ArrayToString {

    public static Role arrayToEnum(String s) {

        if (s.contains("ROLE_SUPER_ADMIN") == true) {
            return Role.valueOf("ADM");
        }
        if (s.contains("ROLE_FOR") == true) {
            return Role.valueOf("FOR");
        }
        if (s.contains("ROLE_APR") == true) {
            return Role.valueOf("APR");
        }
        if (s.contains("ROLE_AOR") == true) {
            return Role.valueOf("ORG");
        }
        if (s.contains("ROLE_MCP") == true) {
            return Role.valueOf("MCP");
        }
        throw new UnsupportedOperationException();
    }

    public static String EnumToArray(Role s) {
        System.out.println(s.toString());
        if (s.equals(Role.ADM)) {
            String ch = "a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}";
            return ch;

        }
        if (s.equals(Role.FOR)) {
            String ch = "a:1:{i:0;s:8:\"ROLE_FOR\";}";
            return ch;
        }
        if (s.equals(Role.APR)) {
            String ch = "a:1:{i:0;s:8:\"ROLE_APR\";}";
            return ch;
        }
        if (s.equals(Role.ORG)) {
            String ch = "a:1:{i:0;s:8:\"ROLE_AOR\";}";
            return ch;
        }
        if (s.equals(Role.MCP)) {
            String ch = "a:1:{i:0;s:8:\"ROLE_MCP\";}";
            return ch;
        }

        throw new UnsupportedOperationException();

    }

    public static String getPasswordFromArray(String s) {
        System.out.println("---------"+s+"----------");
        int i = s.indexOf("{");
       String ch=s.substring(0, s.indexOf("{")); 
      return ch ;

    }
}
