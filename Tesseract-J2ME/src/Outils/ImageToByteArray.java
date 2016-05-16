/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Outils;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.lcdui.Image;
 
public class ImageToByteArray {
     
    public static byte[] getByteArray(Image img) {
        byte[] bytes = null;
        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = new int[width * height];
        img.getRGB(pixels, 0, width, 0, 0, width, height);
 
        try {
            // convert int[] to byte[]
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            DataOutputStream dout = new DataOutputStream(bout);
            // I suggest writing these first: you'll need them to convert back
            dout.writeInt(width);
            dout.writeInt(height);
            // then the pixels
            for (int i = 0; i < pixels.length; i++) {
                dout.writeInt(pixels[i]);
            }
            bytes = bout.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return bytes;
    }
}
