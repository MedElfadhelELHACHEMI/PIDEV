/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outils;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.models.entities.Cours;
import com.models.entities.Notification;
import com.views.controllers.CurrentUser;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sameh
 */
public class PdfListNotification {
  private String nomFichier;
     public PdfListNotification(String nomFichier) throws DocumentException, FileNotFoundException {
      try {
          this.nomFichier=nomFichier;
          String ext = getFileExtension(nomFichier);
          if(ext.isEmpty())
          {
              nomFichier=nomFichier+".pdf";
          }
          ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
         ArrayList<Notification> list = new ArrayList<Notification>();
           list=serviceApprenant.ListNotificationByUserId(CurrentUser.getId());
         Document pdf = new Document();
          PdfWriter.getInstance(pdf, new FileOutputStream(nomFichier));
        pdf.open();
         PdfPTable table = new PdfPTable(2);
        PdfPCell cellule;
        
        cellule=new PdfPCell(new Phrase("Notification"));
        table.addCell(cellule);
        cellule=new PdfPCell(new Phrase("Date Notification"));
        table.addCell(cellule);
        for(int i=0;i<list.size();i++){
         String nom =list.get(i).getNotification();
            cellule=new PdfPCell(new Phrase(nom));
            table.addCell(cellule);
            
            String Date = String.valueOf(list.get(i).getDateNotification());
            cellule=new PdfPCell(new Phrase(Date));
            table.addCell(cellule);   
        }
          pdf.add(table);                       
        pdf.close();
      } catch (SQLException ex) {
          Logger.getLogger(PdfListNotification.class.getName()).log(Level.SEVERE, null, ex);
      }
     }
     
     private static String getFileExtension(String file) {
        String fileName = file;
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }  
}
