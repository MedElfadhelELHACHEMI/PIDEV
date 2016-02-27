package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.INotificationDAO;
import com.models.entities.Notification;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class ImplNotificationDAOTest {

    public ImplNotificationDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Ignore
    @Test
    public void TEST_CREATE_NOTIFICATION_SHOULD_RETURN_TRUE() throws SQLException {

        INotificationDAO notificationDAO = DAOFactory.getNotificationDAO();
        Notification notification = new Notification();
        notification.setIdNotification(1);
        notification.setIdUtilisateur(1);
        notification.setNotification("blaaa ya youuuu!!");
        notification.setDateNotification(new Date(20));

        boolean test = notificationDAO.createNotification(notification);
        assertTrue(test);
    }

    @Ignore
    @Test
    public void TEST_DISPLAY_NOTIFICATION_BY_USER__ID_SHOULD_RETRUN_LIST() throws SQLException {

        INotificationDAO notificationDAO = DAOFactory.getNotificationDAO();
        List result = notificationDAO.displayNotificationByUserId(1);
        assertEquals(result.size(), 2);

    }

     @Ignore
    @Test
    public void TEST_DISPLAY_NOTIFICATION_BY_DATE_SHOULD_RETRUN_LIST() throws SQLException {

      LocalDate localDate = LocalDate.of(1970, Month.JANUARY, 1);
        INotificationDAO notificationDAO = DAOFactory.getNotificationDAO();
        List result = notificationDAO.displayNotificationByDate(Date.valueOf(localDate));
        assertEquals(result.size(), 1);

    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
