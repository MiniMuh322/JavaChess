package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameStats implements ActionListener{
    final int window_size = 800;
    private JButton start;
    private PlayerName white_name;
    private PlayerName black_name;
    private JFrame game_stats = new JFrame();

    public GameStats(){
        white_name = new PlayerName();
        black_name = new PlayerName();

        start = new JButton();
        start.addActionListener(this);

        start.setBounds(250,100,300,100);
        start.setText("Create new game");
        start.setFocusable(false);

        start.setFont(new Font("Comic Sans", Font.BOLD,15));
        start.setBackground(Color.WHITE);
        start.setBorder(BorderFactory.createBevelBorder(5));

        game_stats.getContentPane().setBackground(Color.GRAY);
        game_stats.setTitle("Game configurations");
        game_stats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game_stats.setResizable(false);
        game_stats.setSize(window_size, window_size);
        game_stats.setLayout(null);
        game_stats.setVisible(true);

        JLabel white = new JLabel("Enter white name");
        JLabel black = new JLabel("Enter black name");

        white_name.textField.setBounds(250, 300, 300,40);
        black_name.textField.setBounds(250, 400, 300,40);

        white.setBounds(275,240, 300,75);
        white.setForeground(Color.WHITE);
        white.setFont(new Font("Comic sans", Font.PLAIN,25));

        black.setBounds(275,340, 300,75);
        black.setForeground(Color.WHITE);
        black.setFont(new Font("Comic sans", Font.PLAIN,25));


        game_stats.add(start);
        game_stats.add(white_name.textField);
        game_stats.add(black_name.textField);
        game_stats.add(white);
        game_stats.add(black);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start){
            if (white_name.textField.getText().equals("") ||
            black_name.textField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter player name!", "Warning", JOptionPane.PLAIN_MESSAGE);
            }else{
            game_stats.dispose();
                try {
                    new GameGUI(white_name.textField.getText(),black_name.textField.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }


}