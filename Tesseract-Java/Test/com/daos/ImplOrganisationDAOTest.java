package com.daos;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.INotificationDAO;
import com.models.daos.interfaces.IOrganisationDAO;
import com.models.entities.Notification;
import com.models.entities.Organisation;
import java.sql.Date;
import java.sql.SQLException;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
import org.junit.Test;

public class ImplOrganisationDAOTest {

    @Ignore
    @Test
    public void TEST_GET_ORGANISME_SHOULD_RETURN_NO_Null() throws SQLException {

        IOrganisationDAO iOrganisationDAO = DAOFactory.getOrganisationDAO();

        Assert.assertNotNull(iOrganisationDAO.getOrganisationByid(1));
    }
      @Ignore
    @Test
    public void TEST_GET_ORGANISME_SHOULD_RETURN_LIST() throws SQLException {

        IOrganisationDAO iOrganisationDAO = DAOFactory.getOrganisationDAO();

        Assert.assertNotNull(iOrganisationDAO.displayOrganisation());
    }
     @Ignore 
  @Test
    public void getOrganisationByMatriculeNom_TEST() throws SQLException {

        IOrganisationDAO iOrganisationDAO = DAOFactory.getOrganisationDAO();

        Assert.assertNotNull(iOrganisationDAO.getOrganisationByMatriculeNom("Oxia"));
    }
    @Ignore
    @Test
    public void addOrganisme(){
    IOrganisationDAO iOrganisationDAO = DAOFactory.getOrganisationDAO();
    iOrganisationDAO.addOrganisation(new Organisation());
    }
}
