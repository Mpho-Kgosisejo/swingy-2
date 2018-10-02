package mkgosisejo.utils;

import javax.swing.JFrame;

public class GUIHelper {
    public static void SetDefaultFrame(JFrame frame, String title, int width, int height){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        FixFrameSize(frame, width, height);
    }

    public static void FixFrameSize(JFrame frame, int width, int height){
        if (System.getProperty("os.name").startsWith("Windows")){
            frame.setSize(width, (height + 13));
        }else{
            frame.setSize(width, height);
        }
    }
}