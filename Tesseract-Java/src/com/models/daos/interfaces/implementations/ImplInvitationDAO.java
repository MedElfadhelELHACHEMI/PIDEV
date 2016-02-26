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

public class ImplInvitationDAO implements IInvitationDAO {

    @Override
    public boolean createInvitation(Invitation i) throws SQLException {

        Connection connection = DataSource.getInstance().getConnection();
        String req = "insert into invitation ( id_utilisateur, id_organisation, sens,etat,date) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setInt(1, i.getIdUtilisateur());
        preparedStatement.setInt(2, i.getIdOrganisation());
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

    }
