/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outils;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.api.Spaceable;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.models.entities.Challenge;
import com.views.controllers.CurrentUser;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sameh
 */
public class PdfBadge {
private String nomFichier;
String nameCours;
   public PdfBadge(String nomFichier,String nameCours) throws DocumentException, FileNotFoundException {
    try {
        this.nomFichier=nomFichier;
        this.nameCours=nameCours;
        String ext = getFileExtension(nomFichier);
        if(ext.isEmpty())
        {
            nomFichier=nomFichier+".pdf";
        }
  
       Document pdf = new Document();
     PdfWriter.getInstance(pdf, new FileOutputStream(nomFichier));
     Font fontTitle=new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.WHITE);  
   Chunk title=new Chunk("Certificat de Réussite", fontTitle);  
 title.setBackground(new BaseColor(255,102,0), 1f, 1f, 1f, 3f);  
   title.setLineHeight(30f);   
   
   title.setUnderline(BaseColor.BLACK,5f,0.5f,2f,0.5f,PdfContentByte.LINE_CAP_ROUND);
   String imagepath = "badge.jpg";  
 Image img = Image.getInstance(imagepath);  
    String imagepath2 = "sig.png";  
 Image img2 = Image.getInstance(imagepath2);   
 img.scaleAbsolute(120f, 60f); 
pdf.open(); 
  pdf.add(img);  
 pdf.add(title);  
 pdf.add(Chunk.NEWLINE); 
 pdf.add(Chunk.NEWLINE); 
  pdf.add(new LineSeparator());
  pdf.add(Chunk.NEWLINE); 
  pdf.add(new Paragraph(CurrentUser.getUtilisateur().getNom()+""+CurrentUser.getUtilisateur().getPrenom()));

  pdf.add(new Paragraph("né()le"+CurrentUser.getUtilisateur().getDateNaissance()));

  Paragraph paragraph = new Paragraph("A reussi avec succés le cours:");
 paragraph.setAlignment(Element.ALIGN_CENTER);
 
    Paragraph paragraph2 = new Paragraph(""+nameCours);
 
  paragraph2.setSpacingAfter(50);
   paragraph2.setAlignment(Element.ALIGN_CENTER);

   pdf.add(paragraph);
   pdf.add(paragraph2);

 
  pdf.add(new Paragraph("Tessaract"));
  pdf.add(new Paragraph("co-fondateur de Tessaract Coding"));
   pdf.add(img2);  

  pdf.addCreationDate();
        pdf.close();
    } catch (BadElementException ex) {
        Logger.getLogger(PdfListChallenge.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(PdfListChallenge.class.getName()).log(Level.SEVERE, null, ex);
    }
     }
     
     private static String getFileExtension(String file) {
        String fileName = file;
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }          
}
