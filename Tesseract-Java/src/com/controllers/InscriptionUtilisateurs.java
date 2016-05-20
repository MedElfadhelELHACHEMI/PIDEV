/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.database.CryptographieMOOC;
import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IApprenantDAO;
import com.models.daos.interfaces.IFormateurDAO;
import com.models.daos.interfaces.IOrganisationDAO;
import com.models.entities.Apprenant;
import com.models.entities.Formateur;
import com.models.entities.Organisation;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author haikal
 */
public class InscriptionUtilisateurs {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public void envoyerEMailUtilisateur(String EmailEmetteur, String pwdEmetteur, String EmailRecepteur, String object, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactoty.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EmailEmetteur, pwdEmetteur);
                    }

                }
        );
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EmailRecepteur));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("haikal.magrahi@esprit.tn"));

            message.setSubject(object);
            message.setText(text);
            Transport.send(message);
        } catch (Exception e) {

        }

    }

    public static boolean validerMail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public boolean inscriptionOrganisation(Organisation organisation) {
        IOrganisationDAO iOrganisationDAO = DAOFactory.getOrganisationDAO();
        return iOrganisationDAO.addOrganisation(organisation);

    }

    public boolean verifierLogin(String text) throws SQLException {
        IApprenantDAO aO = DAOFactory.getApprenantDAO();
        Apprenant apprenant = aO.getApprenantByLogin(text);
        System.out.println(apprenant);
        if (apprenant.getIdUtilisateur() == 0) {

            return true;
        }
        return false;

    }

    public boolean inscriptionApprenant(Apprenant apprenant) throws SQLException, Exception {
        IApprenantDAO aO = DAOFactory.getApprenantDAO();
        String pwd = apprenant.getMotDePass();
        apprenant.setScore(0);
        apprenant.setMotDePass(CryptographieMOOC.getCryptage().encrypt(pwd));
        return aO.ajouterApprenant(apprenant);
    }

    public boolean inscriptionFormateur(Formateur formateur) throws SQLException, Exception {
        IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        String pwd =CryptographieMOOC.getCryptage().encrypt(formateur.getMotDePass());
        formateur.setMotDePass(pwd);
        return formateurDAO.ajouterFormateur(formateur,null);
    }

    public boolean inscriptionFormateurWithOrganization(Formateur formateur) throws Exception {
       IFormateurDAO formateurDAO = DAOFactory.getFormateurDAO();
        String pwd =CryptographieMOOC.getCryptage().encrypt(formateur.getMotDePass());
        formateur.setMotDePass(pwd);
        return formateurDAO.ajouterFormateurWithOrganization(formateur,null);
    }
}
