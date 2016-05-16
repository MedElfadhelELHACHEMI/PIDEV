/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;
import com.entities.Apprenant;
import com.handler.UtilisateurHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tesseract.Midlet;
/**
 *
 * @author Bacem
 */
public class SignIn extends Canvas implements Runnable {

    HttpConnection httpConnection;
    DataInputStream dataInputStream;
    StringBuffer stringBuffer = new StringBuffer("");
    
    int w = getWidth();
    int h = getHeight();
    
    Image usernameTxt[]= new Image[2];
    Image pwdTxt[] = new Image[2];
    Image signInTxt[] = new Image[2];
    Image signUpTxt[] = new Image[2];
    Image position[] = new Image[2];
    Image errorCred;
    
    String username,pwd, pwdDisp;
    String role;
    
    int select = 0;
    int i=1;
    int j=0;
    int k=0;
    int l=0;
    int m=0;
    
    boolean verif=true;
    
    public SignIn(){
        try {
            username="";
            pwd=pwdDisp="";
            
            usernameTxt[0] = Image.createImage("/com/gui/images/textFields/username.png");
            usernameTxt[1] = Image.createImage("/com/gui/images/textFields/selectedUsername.png");
            
            pwdTxt[0] = Image.createImage("/com/gui/images/textFields/password.png");
            pwdTxt[1] = Image.createImage("/com/gui/images/textFields/selectedPwd.png");
            
            signInTxt[0] = Image.createImage("/com/gui/images/buttons/signin.png");
            signInTxt[1] = Image.createImage("/com/gui/images/buttons/selectedIn.png");
            
            signUpTxt[0] = Image.createImage("/com/gui/images/buttons/signup.png");
            signUpTxt[1] = Image.createImage("/com/gui/images/buttons/selectedUp.png");
            
            position[0] = Image.createImage("/com/gui/images/buttons/position.png");
            position[1] = Image.createImage("/com/gui/images/buttons/selectedPosition.png");
        
            errorCred = Image.createImage("/com/gui/images/icons/errorLogin.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    protected void paint(Graphics g) {
        g.setColor(57,58,60);
        g.fillRect(0, 0, w, h);
        g.drawImage(usernameTxt[i], w/2, h/3, g.VCENTER|g.HCENTER);
        g.drawImage(pwdTxt[j], w/2, h/2, g.VCENTER|g.HCENTER);
        g.drawImage(signInTxt[k], w/2, 2*h/3, g.VCENTER|g.HCENTER);
        g.drawImage(signUpTxt[l], w/2, (2*h/3)+ 40, g.VCENTER|g.HCENTER);
        g.drawImage(position[m], w/2, 2*h/3+80, g.VCENTER|g.HCENTER);
        g.setColor(36,199,207);
        g.drawString(username, 15, h/3+10, g.BASELINE|g.LEFT);
        g.drawString(pwdDisp, 15, h/2+10, g.BASELINE|g.LEFT);
        
        if(!verif){
            g.drawImage(errorCred, w/2, h/2, g.VCENTER|g.HCENTER);
        }
    }
    protected void keyPressed(int keyCode){
        int gameAction = getGameAction(keyCode);
        if (gameAction==DOWN){
            select++;
            if(select==5){
                select=0;
            }
            changeSelection();
            repaint();
        }
        else if (gameAction==UP){
            select--;
            if(select==-1){
                select=4;
            }
            changeSelection();
            repaint();
        }
        else if (gameAction==FIRE){
            if(verif==false){
                verif = true;
                repaint();
            }
            else if(select==2){
                Thread th = new Thread(this, "login");
                th.start();
                
            }
            else if (select==3){
                Midlet.INSTANCE.disp.setCurrent(new SignUp());
            }
            else if (select==4){
                Midlet.INSTANCE.disp.setCurrent(new GoogleMapsZoomCanvas(this));
            }
        }
        else if (keyCode!=0 && gameAction!=FIRE && gameAction!=LEFT && gameAction!=RIGHT){
            if(select==0){
                if(keyCode!=-8){
                    username+=getKeyName(keyCode);
                }
                else if(keyCode==-8 && username.length()>0){
                    username=username.substring(0, username.length()-1);
                }
            }
            if(select==1){
                if(keyCode!=-8){
                    pwd+=getKeyName(keyCode);
                    pwdDisp +="*";
                }
                else if(keyCode==-8 && pwd.length()>0){
                    pwd=pwd.substring(0, pwd.length()-1);
                    pwdDisp = pwdDisp.substring(0, pwdDisp.length()-1);
                }
            }
            repaint();
        }
    }
    protected void keyRepeated(int keyCode){
        if(keyCode==-8){
            if(select==0 && username.length()>0){
                username=username.substring(0, username.length()-1);
                repaint();
            }
            if(select==1 && pwd.length()>0){
                pwd=pwd.substring(0, pwd.length()-1);
                repaint();
            }
        }
    }
    protected void changeSelection(){
        switch (select){
            case 0 :
                i=1;
                j=k=l=m=0;
                break;
            case 1:
                i=k=l=m=0;
                j=1;
                break;
            case 2:
                i=j=l=m=0;
                k=1;
                break;
            case 3:
                i=j=k=m=0;
                l=1;
                break;
            case 4:
                m=1;
                i=j=k=l=0;
                break;
        }
    }

    public void run() {
        if(Thread.currentThread().getName().equals("login")){
            String str = "http://localhost/tesseractj2me/tesseract_php/login.php?username=" + username.trim() + "&password=" + pwd.trim();
            verif=false;
            try {
                httpConnection = (HttpConnection) Connector.open(str);
                dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                UtilisateurHandler utilisateurHandler = new UtilisateurHandler();
                parser.parse(dataInputStream, utilisateurHandler);
                if(utilisateurHandler.getUtilisateur()[0]!=null){
                    Apprenant currentUtilisateur = new Apprenant((Apprenant) utilisateurHandler.getUtilisateur()[0]);
                    verif=true;
                    Midlet.INSTANCE.ApprenantCurrent=currentUtilisateur;
                    
                }
                Thread.currentThread().interrupt();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            } catch (SAXException ex) {
                //ex.printStackTrace();
            }
        }
        if(verif== false) {
            repaint();
        } else {
            Midlet.INSTANCE.disp.setCurrent(new MenuApp());
        }
    }
    
}
