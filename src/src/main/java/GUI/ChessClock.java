package GUI;

import javax.swing.*;
import java.awt.*;

enum ClockStatus {
    ACTIVE, PAUSED
}

public class ChessClock extends Thread{
    public int seconds;
    public JPanel time_panel;
    private final JLabel time_label;

    public ClockStatus status;
    public ChessClock(){
        status = ClockStatus.PAUSED;
        seconds = 600;
        time_panel = new JPanel();
        time_label = new JLabel(seconds / 60 + " : 00");
        time_label.setFont(new Font("Comic sans", Font.ITALIC,30));
        time_label.setForeground(Color.WHITE);

        time_panel.add(time_label);
    }

    @Override
    public void run(){
        if (status == ClockStatus.ACTIVE) {
        while (true) {

                try {
                    Thread.sleep(1000);
                    seconds--;
                    time_label.setText(seconds / 60 + " : " + (seconds - (seconds / 60) * 60));
                    time_label.revalidate();


                } catch (InterruptedException e) {
                    JOptionPane.showMessageDialog(null, "Thread run time error!", "Warning", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }

    }

    public void pause() {
        status = ClockStatus.PAUSED;
    }

    public void activate() {
        status = ClockStatus.ACTIVE;
    }
}