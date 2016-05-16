package tessearctntest;

import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Dialog;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Image;
import com.sun.lwuit.Tabs;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.plaf.Style;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import tesseracttest.Midlet;

/**
 *
 * @author Noor
 */
class SubmitCourseForm extends Form {

    HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb;
    String url = "http://localhost/Business/addCourse.php?";
    Command command = new Command("Back");
    Dialog confirm;
    Button ok = new Button("OK");
    Image bgImage, titleIcon, descIcon, vidIcon, durationIcon;
    Form f;
    Tabs tabs;
    TextField title, duration, vid;
    TextArea desc;
    Command Reset,Back;
    Button Submit = new Button("Submit");

    Font myFont = Font.createSystemFont(Font.FACE_SYSTEM,
            Font.STYLE_BOLD | Font.STYLE_ITALIC,
            Font.SIZE_MEDIUM);

    SubmitCourseForm(String submit_Course) throws IOException {
        addCommandListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
             if(ae.getCommand()==Back){
             Midlet.INSTANCE.disp.setCurrent(new CoursesCanvas());
             } 
            }
        });
        addCommand(command);
        //Reset.getStyle().setBgTransparency(2);
        Style buttonUp = new Style();
        buttonUp.setBgColor(0x0B2632);
        buttonUp.setFont(myFont);
        buttonUp.setFgColor(0xB4CEDA);
        // buttonUp.setAlignment(10);

        Style buttonDown = new Style();
        buttonDown.setBgColor(0xB4CEDA);
        buttonDown.setFont(myFont);
        buttonDown.setFgColor(0x0B2632);
//        buttonDown.setAlignment(10);

        Submit.setUnselectedStyle(buttonUp);
        Submit.setSelectedStyle(buttonUp);
        Submit.setPressedStyle(buttonDown);
//        
//        Reset.setUnselectedStyle(buttonUp);       
//        Reset.setSelectedStyle(buttonDown);
//        
        Submit.setPreferredW(200);
//        Reset.setPreferredW(80);

        confirm = new Dialog();
        confirm.addComponent(ok);
        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                if (title.getText().equals("") || desc.getText().equals("") || duration.getText().equals("") || vid.getText().equals("")) {
                    confirm.setDialogType(Dialog.TYPE_ERROR);
                    confirm.setNextFocusUp(tabs);
                    confirm.setTitle("Please verify information");
                    ok.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            confirm.dispose();
                        }
                    });
                    confirm.show();
                } else {
                    confirm.setDialogType(Dialog.TYPE_CONFIRMATION);
                    confirm.setTitle("Are you sure ? ");
                    ok.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            try {
                                confirm.dispose();
                                String vidText = vid.getText();
                                String titleText=title.getText();
                                String descText=desc.getText();
                                System.out.println(vidText+"  "+titleText+"   "+descText);
                                hc = (HttpConnection) Connector.
                                        open("http://localhost/Business/addCourse.php?nom="+titleText +"&desc="+descText+"&vid="+ vidText);
                                dis = hc.openDataInputStream();

                                int ascii;
                                sb = new StringBuffer();

                                while ((ascii = dis.read()) != -1) {

                                    sb.append((char) ascii);

                                }

                                if (sb.toString().equals("successfully added")) {
                                    Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                                    a.setTimeout(3000);
                                } else {
                                    Alert a = new Alert("Information", sb.toString(), null, AlertType.ERROR);
                                    a.setTimeout(3000);
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    confirm.show();
                }

            }
        });

        Reset = new Command("Reset");
        Back=new Command("Back");

        this.addCommandListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (ae.getCommand().equals(Reset)) {
                    Form f = null;
                    try {
                        f = new SubmitCourseForm("");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    f.show();

                }
                if (ae.getCommand().equals(Back))
                {
                    Midlet.INSTANCE.disp.setCurrent(new CoursesCanvas());
                }

            }
        });

        bgImage = Image.createImage("/tesseracttest/images/bgSubmit.png");
        getStyle().setBgImage(bgImage);

        titleIcon = Image.createImage("/tesseracttest/images/titleSubmit.png");
        descIcon = Image.createImage("/tesseracttest/images/descSubmit.png");
        vidIcon = Image.createImage("/tesseracttest/images/videoSubmit.jpg");
        durationIcon = Image.createImage("/tesseracttest/images/durationSubmit.png");

        title = new TextField("Title");
        title.setSelectCommandText("");
        title.getNextFocusRight();
        desc = new TextArea("Description", 0);
        desc.getNextFocusRight();
        desc.setSelectCommandText("");
        vid = new TextField("Summary Video", 0);
        vid.getNextFocusRight();
        vid.setSelectCommandText("");
        duration = new TextField("Duration", 0);
        duration.getNextFocusRight();
        duration.setSelectCommandText("");

        tabs = new Tabs(Tabs.BRB_CONSTANT_ASCENT);

        tabs.addTab(
                "", titleIcon, title);
        tabs.addTab(
                "", descIcon, desc);
        tabs.addTab(
                "", durationIcon, duration);
        tabs.addTab(
                "", vidIcon, vid);

        addComponent(tabs);
        addComponent(Submit);

        addCommand(Reset);
        setFocused(tabs.findFirstFocusable());

    }
    //add matière et les champs manquants

}
