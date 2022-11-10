package net.uart;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Tesseract tesseract = new Tesseract();
        try {
            String text
                    = tesseract.doOCR(new File("/home/seaeagle/Pictures/Screenshots/Screenshot from 2022-11-10 09-44-26.png"));

            System.out.print(text);
        }
        catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}
