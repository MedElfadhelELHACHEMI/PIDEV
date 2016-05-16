

package tesseracttest;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author haikal
 */
public class Midlet extends MIDlet {
    public Display disp =Display.getDisplay(this);
    public static Midlet INSTANCE ;

    public void startApp() {
        com.sun.lwuit.Display.init(this);
        INSTANCE=this;
//        disp.setCurrent(new HomeCanvas("Coach"));
         disp.setCurrent(new SplashFormateur());
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
