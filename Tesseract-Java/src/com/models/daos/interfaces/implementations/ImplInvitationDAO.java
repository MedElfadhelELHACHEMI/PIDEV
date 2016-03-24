package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.IInvitationDAO;
import com.database.DataSource;
import com.models.entities.Invitation;
import com.models.entities.Organisation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImplInvitationDAO implements IInvitationDAO {

    @Override
    public boolean createInvitation(Invitation i) throws SQLException {

        Connection connection = DataSource.getInstance().getConnection();
        String req = "insert into invitations ( id_organisme,id_utilisateur,  sens,etat,date) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setInt(2, i.getIdUtilisateur());
        preparedStatement.setInt(1, i.getIdOrganisation());
        preparedStatement.setString(3, i.getSens());
        preparedStatement.setString(4, i.getEtat());
        preparedStatement.setDate(5, i.getDateInvitation());

        int res = preparedStatement.executeUpdate();
        preparedStatement.close();
        return (res == 1);
    }

    @Override
    public List<Invitation> displayInvitationByUserId(int idUtilisateur) throws SQLException {
        List<Invitation> listInvitations = new ArrayList<Invitation>();
        Connection connection = DataSource.getInstance().getConnection();
        String req = "select * from invitation where id_utilisateur =" + String.valueOf(idUtilisateur);

        Statement st = connection.createStatement();
        ResultSet resultat = st.executeQuery(req);

        while (resultat.next()) {
            Invitation invitation = new Invitation();
            invitation.setIdUtilisateur(resultat.getInt(1));
            invitation.setIdOrganisation(resultat.getInt(2));
            invitation.setSens(resultat.getString(3));
            invitation.setEtat(resultat.getString(4));
            invitation.setDateInvitation(resultat.getDate(5));
            listInvitations.add(invitation);
        }
        return listInvitations;

    }

    @Override
    public List<Invitation> displayInvitationByOrganisationId(int idOrganisation) throws SQLException {
        List<Invitation> listInvitations = new ArrayList<Invitation>();
        Connection connection = DataSource.getInstance().getConnection();
        String req = "select * from invitation where id_organisation =" + String.valueOf(idOrganisation);

        Statement st = connection.createStatement();
        ResultSet resultat = st.executeQuery(req);

        while (resultat.next()) {
            Invitation invitation = new Invitation();
            invitation.setIdUtilisateur(resultat.getInt(1));
            invitation.setIdOrganisation(resultat.getInt(2));
            invitation.setSens(resultat.getString(3));
            invitation.setEtat(resultat.getString(4));
            invitation.setDateInvitation(resultat.getDate(5));
            listInvitations.add(invitation);
        }
        return listInvitations;

    }

    @Override
    public List<Invitation> getAllInvitations() throws SQLException {
        List<Invitation> listInvitations = new ArrayList<Invitation>();
        Connection connection = DataSource.getInstance().getConnection();
        String req = "select * from invitation ";

        Statement st = connection.createStatement();
        ResultSet resultat = st.executeQuery(req);

        while (resultat.next()) {
            Invitation invitation = new Invitation();
            invitation.setIdUtilisateur(resultat.getInt(1));
            invitation.setIdOrganisation(resultat.getInt(2));
            invitation.setSens(resultat.getString(3));
            invitation.setEtat(resultat.getString(4));
            invitation.setDateInvitation(resultat.getDate(5));
            listInvitations.add(invitation);
        }
        return listInvitations;

    }

    @Override
    public List displayInvitationByUserIdEmetteur(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifExistInvitaion(int idOrganisation, int id) {
        try {
            List<Invitation> listInvitations = new ArrayList<Invitation>();
            Connection connection = DataSource.getInstance().getConnection();
            String req = "SELECT * FROM invitations where id_utilisateur=" + id + " and id_organisme=" + idOrganisation + " and etat='ATT'  and sens = 'E'";

            PreparedStatement st = connection.prepareStatement(req);
//            st.setInt(1, idOrganisation);
//          st.setInt(2, id);
            ResultSet resultat = st.executeQuery(req);

            while (resultat.next()) {
                Invitation invitation = new Invitation();
                invitation.setIdUtilisateur(resultat.getInt(3));
                invitation.setIdOrganisation(resultat.getInt(2));
                invitation.setSens(resultat.getString(4));
                invitation.setEtat(resultat.getString(5));
                invitation.setDateInvitation(resultat.getDate(6));
                listInvitations.add(invitation);
            }
            System.out.println("list invi" + listInvitations);
            if (listInvitations.isEmpty()) {

                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ImplInvitationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Invitation> getInvitationEnAttenteById(int id) {
        List<Invitation> listInvitations = null;
        try {
            listInvitations = new ArrayList<Invitation>();
            Connection connection = DataSource.getInstance().getConnection();
            String req = "select * FROM invitations where id_utilisateur=" + id + " and etat='ATT' and sens = 'R'";
            PreparedStatement st = connection.prepareStatement(req);
//            st.setInt(1, idOrganisation);
//          st.setInt(2, id);
            ResultSet resultat = st.executeQuery();

            while (resultat.next()) {
                Invitation invitation = new Invitation();
                invitation.setIdUtilisateur(resultat.getInt(3));
                invitation.setIdOrganisation(resultat.getInt(2));
                invitation.setSens(resultat.getString(4));
                invitation.setEtat(resultat.getString(5));
                invitation.setDateInvitation(resultat.getDate(6));
                listInvitations.add(invitation);
            }
            System.out.println("list invi" + listInvitations);

            return listInvitations;

        } catch (SQLException ex) {
            Logger.getLogger(ImplInvitationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listInvitations;

    }

    @Override
    public void modifierInvitation(Invitation invitation) {
        try {
            Connection connection = DataSource.getInstance().getConnection();
            String req = "update invitations set etat = 'ACC' where id_organisme ="+invitation.getIdOrganisation()+" and id_utilisateur= "+invitation.getIdUtilisateur()+" and etat ='ATT' and sens ='R' ";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            int res = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImplInvitationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean modifierInvitationRefuser(Invitation invitation) {
        try {
            Connection connection = DataSource.getInstance().getConnection();
            String req = "update invitations set etat = 'REF' where id_organisme ="+invitation.getIdOrganisation()+" and id_utilisateur= "+invitation.getIdUtilisateur()+" and etat ='ATT' and sens ='R' ";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            int res = preparedStatement.executeUpdate();
            preparedStatement.close();
            return res==1;
        } catch (SQLException ex) {
            Logger.getLogger(ImplInvitationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;
    }

}
