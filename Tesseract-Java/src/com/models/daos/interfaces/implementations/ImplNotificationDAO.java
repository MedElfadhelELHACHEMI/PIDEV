package com.models.daos.interfaces.implementations;

import com.models.daos.interfaces.INotificationDAO;
import com.database.DataSource;
import com.models.entities.Notification;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImplNotificationDAO implements INotificationDAO {

    @Override
    public boolean createNotification(Notification notification) throws SQLException {

        Connection connection = DataSource.getInstance().getConnection();
        String req = "insert into notification(id, id_utilisateur, notification , date) values (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setInt(1, notification.getIdNotification());
        preparedStatement.setInt(2, notification.getIdUtilisateur());
        preparedStatement.setString(3, notification.getNotification());
        preparedStatement.setDate(4, notification.getDateNotification());

        int res = preparedStatement.executeUpdate();
        preparedStatement.close();
        return (res == 1);

    }

    @Override
    public List<Notification> displayNotificationByUserId(int idUtilisateur) throws SQLException {
        List<Notification> listN = new ArrayList<Notification>();
        Connection connection = DataSource.getInstance().getConnection();
        String req = "select * from notification where id =" + idUtilisateur;

        Statement st = connection.createStatement();
        ResultSet resultat = st.executeQuery(req);

        while (resultat.next()) {
            Notification notification = new Notification();
            notification.setIdNotification(resultat.getInt(1));
            notification.setIdUtilisateur(resultat.getInt(2));
            notification.setNotification(resultat.getString(3));
            notification.setDateNotification(resultat.getDate(4));

            listN.add(notification);
        }

        return listN;
    }

    @Override
    public List<Notification> displayNotificationByDate(Date date) throws SQLException {
        List<Notification> listN = new ArrayList<Notification>();
        Connection connection = DataSource.getInstance().getConnection();
        String req = "select * from notification where date ='" +date+" 00:00:00'" ;

        Statement st = connection.createStatement();
        ResultSet resultat = st.executeQuery(req);

        while (resultat.next()) {
            Notification notification = new Notification();
            notification.setIdNotification(resultat.getInt(1));
            notification.setIdUtilisateur(resultat.getInt(2));
            notification.setNotification(resultat.getString(3));
            notification.setDateNotification(resultat.getDate(4));

            listN.add(notification);
        }

        return listN;

    }

}
