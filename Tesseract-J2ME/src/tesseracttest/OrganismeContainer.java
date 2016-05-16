package tesseracttest;

import com.sun.lwuit.Component;
import com.sun.lwuit.Container;
import com.sun.lwuit.Font;
import com.sun.lwuit.Label;
import com.sun.lwuit.List;
import com.sun.lwuit.Painter;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.list.ListCellRenderer;

public class OrganismeContainer extends Container implements ListCellRenderer ,ActionListener{

    private Label name = new Label("");
    private Label email = new Label("");
    private Label pic = new Label("");

    private Label focus = new Label("");

    public OrganismeContainer() {

      setLayout(new BorderLayout());
      addComponent(BorderLayout.WEST, pic);
      Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
      name.getStyle().setBgTransparency(0);
      name.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
      email.getStyle().setBgTransparency(0);
      cnt.addComponent(name);
      cnt.addComponent(email);
      addComponent(BorderLayout.CENTER, cnt);

      focus.getStyle().setBgTransparency(100);

       

    }

    public Component getListCellRendererComponent(List list, Object o, int i, boolean bln) {
        Organisation org = (Organisation) o;
        name.setText(org.getNom());
        email.setText(org.geteMail());

        return this;
    }

    public Component getListFocusComponent(List list) {
        return focus;
    }

    public void actionPerformed(ActionEvent ae) {
   
    
    }

}
