package mkgosisejo.utils;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class GUIHelper {
    public static void SetDefaultFrame(JFrame frame, String title, int width, int height){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        FixFrameSize(frame, width, height);
        // frame.addWindowListener(new WindowAction());
    }

    public static void FixFrameSize(JFrame frame, int width, int height){
        if (System.getProperty("os.name").startsWith("Windows")){
            frame.setSize(width, (height + 13));
        }else{
            frame.setSize(width, height);
        }
    }

    private static class WindowAction implements WindowListener {
        public void windowOpened(WindowEvent e) {}

        public void windowClosing(WindowEvent e) {
            SwingyIO.ConsoleOutLine("windowClosing()");
        }

        public void windowClosed(WindowEvent e) {}

        public void windowIconified(WindowEvent e) {}

        public void windowDeiconified(WindowEvent e) {}

        public void windowActivated(WindowEvent e) {}

        public void windowDeactivated(WindowEvent e) {}
    }
}