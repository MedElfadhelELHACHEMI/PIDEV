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
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

import tesseract.Midlet;

/**
 *
 * @author Bacem
 */
public class SignUp extends Canvas implements Runnable {

    Image usernameTxt[] = new Image[2];
    Image pwdTxt[] = new Image[2];
    Image emailTxt[] = new Image[2];
    Image emailTxt2[] = new Image[2];

    Image nomTxt[] = new Image[2];
    Image prenomTxt[] = new Image[2];
    Image dateTxt[] = new Image[2];
    Image adresseTxt[] = new Image[2];
    Image telephoneTxt[] = new Image[2];

    Image right[] = new Image[2];
    Image left[] = new Image[2];
    Image errorCred;

    int w = getWidth();
    int h = getHeight();

    int h_i;

    int select, i, j, k, l, m, n, o;

    String username, pwd, email, email2, nom, prenom, adresse, date, tel, pwdDisp;
    
    private String role="APR";

    int screen;
    boolean verif = true;
    HttpConnection httpConnection;
    DataInputStream dataInputStream;
    StringBuffer stringBuffer = new StringBuffer("");

    public SignUp() {
        try {
            username = pwd = email = email2 = nom = prenom = adresse = date = tel = pwdDisp = "";

            screen = 0;

            usernameTxt[0] = Image.createImage("/com/gui/images/textFields/username.png");
            usernameTxt[1] = Image.createImage("/com/gui/images/textFields/selectedUsername.png");
            h_i = usernameTxt[0].getHeight();
            pwdTxt[0] = Image.createImage("/com/gui/images/textFields/password.png");
            pwdTxt[1] = Image.createImage("/com/gui/images/textFields/selectedPwd.png");

            emailTxt[0] = Image.createImage("/com/gui/images/textFields/email.png");
            emailTxt[1] = Image.createImage("/com/gui/images/textFields/selectedEmail.png");

            emailTxt2[0] = Image.createImage("/com/gui/images/textFields/email2.png");
            emailTxt2[1] = Image.createImage("/com/gui/images/textFields/selectedEmail2.png");

            nomTxt[0] = Image.createImage("/com/gui/images/textFields/nom.png");
            nomTxt[1] = Image.createImage("/com/gui/images/textFields/selectedNom.png");

            prenomTxt[0] = Image.createImage("/com/gui/images/textFields/prenom.png");
            prenomTxt[1] = Image.createImage("/com/gui/images/textFields/selectedPrenom.png");

            dateTxt[0] = Image.createImage("/com/gui/images/textFields/date.png");
            dateTxt[1] = Image.createImage("/com/gui/images/textFields/selectedDate.png");

            adresseTxt[0] = Image.createImage("/com/gui/images/textFields/adresse.png");
            adresseTxt[1] = Image.createImage("/com/gui/images/textFields/selectedAdresse.png");

            telephoneTxt[0] = Image.createImage("/com/gui/images/textFields/telephone.png");
            telephoneTxt[1] = Image.createImage("/com/gui/images/textFields/selectedTelephone.png");

            right[0] = Image.createImage("/com/gui/images/icons/right.png");
            right[1] = Image.createImage("/com/gui/images/icons/selectedRight.png");

            left[0] = Image.createImage("/com/gui/images/icons/left.png");
            left[1] = Image.createImage("/com/gui/images/icons/selectedLeft.png");

            errorCred = Image.createImage("/com/gui/images/icons/errorLogin.png");

            select = j = k = l = m = o = 0;
            i = 1;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    protected void paint(Graphics g) {
        g.setColor(57, 58, 60);
        g.fillRect(0, 0, w, h);
        if (screen == 0) {
            g.setColor(255, 255, 255);
            g.drawString("Primary Informations :", 10, h / 7, g.LEFT | g.BASELINE);
            g.drawImage(usernameTxt[i], w / 2, h / 4, g.VCENTER | g.HCENTER);
            g.drawImage(pwdTxt[j], w / 2, h / 4 + h_i + 5, g.VCENTER | g.HCENTER);
            g.drawImage(emailTxt[k], w / 2, h / 4 + 2 * h_i + 10, g.VCENTER | g.HCENTER);
            g.drawImage(emailTxt2[l], w / 2, h / 4 + 3 * h_i + 15, g.VCENTER | g.HCENTER);
            g.drawImage(right[m], w, h, g.BOTTOM | g.RIGHT);
//            g.drawImage(left[n], 0, h, g.BOTTOM|g.LEFT);
            g.setColor(36, 199, 207);
            g.drawString(username, 15, h / 4 + 10, g.BASELINE | g.LEFT);
            g.drawString(pwdDisp, 15, h / 4 + h_i + 15, g.BASELINE | g.LEFT);
            g.drawString(email, 15, h / 4 + 2 * h_i + 20, g.BASELINE | g.LEFT);
            g.drawString(email2, 15, h / 4 + 3 * h_i + 25, g.BASELINE | g.LEFT);
        } else if (screen == 1) {
            g.setColor(255, 255, 255);
            g.drawString("General Informations :", 10, h / 7, g.LEFT | g.BASELINE);
            g.drawImage(prenomTxt[i], w / 2, h / 4, g.VCENTER | g.HCENTER);
            g.drawImage(nomTxt[j], w / 2, h / 4 + h_i + 5, g.VCENTER | g.HCENTER);
            g.drawImage(dateTxt[k], w / 2, h / 4 + 2 * h_i + 10, g.VCENTER | g.HCENTER);
            g.drawImage(adresseTxt[l], w / 2, h / 4 + 3 * h_i + 15, g.VCENTER | g.HCENTER);
            g.drawImage(telephoneTxt[o], w / 2, h / 4 + 4 * h_i + 20, g.VCENTER | g.HCENTER);

            g.setColor(36, 199, 207);
            g.drawString(prenom, 15, h / 4 + 10, g.BASELINE | g.LEFT);
            g.drawString(nom, 15, h / 4 + h_i + 15, g.BASELINE | g.LEFT);
            g.drawString(date, 15, h / 4 + 2 * h_i + 20, g.BASELINE | g.LEFT);
            g.drawString(adresse, 15, h / 4 + 3 * h_i + 25, g.BASELINE | g.LEFT);
            g.drawString(tel, 15, h / 4 + 4 * h_i + 30, g.BASELINE | g.LEFT);

            g.setColor(218, 218, 218);
            g.drawString("(dd-mm-yyyy)", w - 80, h / 4 + 2 * h_i + 4, g.RIGHT | g.BASELINE);

            g.drawImage(right[m], w, h, g.BOTTOM | g.RIGHT);
            g.drawImage(left[n], 0, h, g.BOTTOM | g.LEFT);
            if (!verif) {
                g.drawImage(errorCred, w / 2, h / 2, g.VCENTER | g.HCENTER);
            }

        }

    }

    protected void keyPressed(int keyCode) {
        int gameAction = getGameAction(keyCode);
        if (gameAction == DOWN) {
            select++;
            if (select == 5 && screen == 0) {
                select = 0;
            }
            if (select == 6 && screen == 1) {
                select = 0;
            }
            changeSelection();
            repaint();
        } else if (gameAction == UP) {
            select--;
            if (select == -1 && screen == 0) {
                select = 4;
            }
            if (select == -1 && screen == 1) {
                select = 5;
            }
            changeSelection();
            repaint();
        } else if (gameAction == LEFT) {
            if (screen != 0) {
                if (select == 5) {
                    select = 6;
                }
                changeSelection();
                repaint();
            }
        } else if (gameAction == RIGHT) {
            if (screen != 0) {
                if (select == 6) {
                    select = 5;
                }
                changeSelection();
                repaint();
            }
        } else if (gameAction == FIRE) {
            if (select == 4 && screen == 0) {
                screen = 1;
                select = 0;
                changeSelection();
                repaint();
            }
            if (select == 6 && screen == 1) {
                screen = 0;
                select = 0;
                changeSelection();
                repaint();
            }
            if (verif == false) {
                verif = true;
                repaint();
            } else if (select == 5 && screen == 1) {
                if (username == "" || pwd == "" || email == "" || email2 == "" || nom == "" || prenom == "" || adresse == "" || tel == "" || date == "") {
                    verif = false;
                    repaint();
                } else {
                    Thread th = new Thread(this, "signup");
                    th.start();
                }

            }
        } else if (keyCode != 0 && gameAction != FIRE && gameAction != LEFT && gameAction != RIGHT) {
            if (screen == 0) {
                if (select == 0) {
                    if (keyCode != -8) {
                        username += getKeyName(keyCode);
                    } else if (keyCode == -8 && username.length() > 0) {
                        username = username.substring(0, username.length() - 1);
                    }
                } else if (select == 1) {
                    if (keyCode != -8) {
                        pwd += getKeyName(keyCode);
                        pwdDisp += "*";
                    } else if (keyCode == -8 && pwd.length() > 0) {
                        pwd = pwd.substring(0, pwd.length() - 1);
                        pwdDisp = pwdDisp.substring(0, pwdDisp.length() - 1);
                    }
                } else if (select == 2) {
                    if (keyCode != -8) {
                        if(keyCode==249){
                            email +="@";
                        }
                        else{
                            email += getKeyName(keyCode);
                        }
               
                    } else if (keyCode == -8 && email.length() > 0) {
                        email = email.substring(0, email.length() - 1);
                    }
                } else if (select == 3) {
                    if (keyCode != -8) {
                        if(keyCode==249){
                            email2 +="@";
                        }
                        else{
                        email2 += getKeyName(keyCode);
                        }
                    } else if (keyCode == -8 && email2.length() > 0) {
                        email2 = email2.substring(0, email2.length() - 1);
                    }
                }
            } else if (screen == 1) {
                if (select == 0) {
                    if (keyCode != -8) {
                        prenom += getKeyName(keyCode);
                    } else if (keyCode == -8 && prenom.length() > 0) {
                        prenom = prenom.substring(0, prenom.length() - 1);
                    }
                } else if (select == 1) {
                    if (keyCode != -8) {
                        nom += getKeyName(keyCode);
                    } else if (keyCode == -8 && nom.length() > 0) {
                        nom = nom.substring(0, nom.length() - 1);
                    }
                } else if (select == 2) {
                    if ((keyCode > 47 && keyCode < 58)) {
                        date += getKeyName(keyCode);
                    } else if (keyCode == -8 && date.length() > 0) {
                        date = date.substring(0, date.length() - 1);
                    }else if(keyCode==35){
                        date+=(char)45;
                    }
                } else if (select == 3) {
                    if (keyCode != -8) {
                        adresse += getKeyName(keyCode);
                    } else if (keyCode == -8 && adresse.length() > 0) {
                        adresse = adresse.substring(0, adresse.length() - 1);
                    }
                } else if (select == 4) {
                    if (keyCode > 47 && keyCode < 58) {
                        tel += getKeyName(keyCode);
                    } else if (keyCode == -8 && tel.length() > 0) {
                        tel = tel.substring(0, tel.length() - 1);
                    }
                }
            }
            repaint();
        }
    }

    protected void changeSelection() {
        switch (select) {
            case 0:
                i = 1;
                j = k = l = m = n = o = 0;
                break;
            case 1:
                i = k = l = m = n = o = 0;
                j = 1;
                break;
            case 2:
                i = j = l = m = n = o = 0;
                k = 1;
                break;
            case 3:
                i = j = k = m = n = o = 0;
                l = 1;
                break;
            case 4:
                if (screen == 0) {
                    m = 1;
                    i = j = k = l = n = o = 0;
                } else if (screen == 1) {
                    o = 1;
                    i = j = k = l = m = n = 0;
                }
                break;
            case 5:
                if (screen == 0) {
                    n = 1;
                    i = j = k = l = m = 0;
                } else if (screen == 1) {
                    m = 1;
                    i = j = k = l = n = o = 0;
                }
                break;
            case 6:
                if (screen == 1) {
                    n = 1;
                    i = j = k = l = m = o = 0;
                }
        }
    }

    public void run() {
        if (Thread.currentThread().getName().equals("signup")) {
            String str = "http://localhost/tesseractj2me/tesseract_php/insertAppr.php?username=" + username.trim() 
                    + "&password=" + pwd.trim() 
                    + "&nom=" + nom.trim() 
                    + "&prenom=" + prenom.trim() 
                    + "&role=" + role.trim() 
                    + "&email=" + email.trim() 
                    + "&adresse=" + adresse.trim() 
                    + "&telephone=" + tel.trim()
                    + "&date=" +date.trim();
            System.out.println(str);
            verif = false;
            try {
                httpConnection = (HttpConnection) Connector.open(str);
                dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
                StringBuffer sb = new StringBuffer();
                int ch;

                while ((ch = dataInputStream.read()) != -1) {
                    sb.append((char) ch);
                }

                if ("successfully added".equals(sb.toString().trim())) {
                    verif=true;
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (verif == false) {
            repaint();
        } else {
            Midlet.INSTANCE.disp.setCurrent(new SignIn());
        }
    }

}
