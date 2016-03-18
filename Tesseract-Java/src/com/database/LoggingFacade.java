/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author haikal
 */
public class LoggingFacade {
  

    public static Properties getProperties(int idUtilisateur) {

        Properties properties = new Properties();
        properties.put("log4j.rootLogger", "DEBUG, sql");

        properties.put("log4j.appender.sql", "org.apache.log4j.jdbc.JDBCAppender");
        properties.put("log4j.appender.sql.URL", "jdbc:mysql://localhost:3306/tesseract");

        properties.put("log4j.appender.sql.driver", "com.mysql.jdbc.Driver");

        properties.put("log4j.appender.sql.user", "root");
        properties.put("log4j.appender.sql.password", "");

        properties.put("log4j.appender.sql.sql", "INSERT INTO log (id_utilisateur,tache,date)VALUES (" + idUtilisateur + ",'%m', now())");

        properties.put("log4j.appender.sql.layout", "org.apache.log4j.PatternLayout");
        return properties;
    }
    public static void startLogger(int idUtilisateur){
    PropertyConfigurator.configure(getProperties(idUtilisateur));
    
    }
}
