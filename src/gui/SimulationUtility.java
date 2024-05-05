package gui;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

public class SimulationUtility {

    public static Image loadImage(String fileName) {

        String imagePath = "Images" + File.separator + fileName; 
        String absolutePath = new File(imagePath).getAbsolutePath();

        return Toolkit.getDefaultToolkit().getImage(absolutePath); 
    }
}
