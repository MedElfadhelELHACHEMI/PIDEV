
package com.models.daos.interfaces;

import com.models.entities.Notification;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface INotificationDAO {
     public boolean createNotification(Notification notification) throws SQLException;

    public List<Notification> displayNotificationByUserId(int idUtilisateur) throws SQLException;

    public List<Notification> displayNotificationByDate(Date date ) throws SQLException;

    public ArrayList<Notification> displayNotificationNonVueByUserId(int idUtilisateur)throws SQLException;

    public List<Notification> displayNotificationByDate(Date date, int id)throws SQLException;

    public boolean updateNotifcation(int id, String nom)throws SQLException;
   
   
    public List<Notification> displayNotificationByUserId2(int idUtilisateur) throws SQLException;
}
