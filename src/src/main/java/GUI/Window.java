package GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

public class Window extends JFrame implements ActionListener {
    final int window_size = 800;
    private JButton start_button;
    private JButton optional;

    public Window(){

        start_button = new JButton();
        start_button.addActionListener(this);

        start_button.setBounds(250,200,300,100);
        start_button.setText("Create new game");
        start_button.setFocusable(false);

        start_button.setFont(new Font("Comic Sans", Font.BOLD,20));
        start_button.setBackground(Color.WHITE);
        start_button.setBorder(BorderFactory.createBevelBorder(3));

        optional = new JButton();
        optional.addActionListener(this);

        optional.setBounds(250,350,300,100);
        optional.setText("Watch games");
        optional.setFocusable(false);

        optional.setFont(new Font("Comic Sans", Font.BOLD,20));
        optional.setBackground(Color.WHITE);
        optional.setBorder(BorderFactory.createBevelBorder(5));

        this.getContentPane().setBackground(Color.GRAY);
        this.setTitle("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(window_size, window_size);
        this.setLayout(null);
        this.setVisible(true);

        this.add(start_button);
        this.add(optional);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start_button){
            this.dispose();
            new GameStats();
        }
        if(e.getSource() == optional){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("./chess_games"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);

            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try (Scanner fileIn = new Scanner(file)) {
                    if (file.isFile()) {
                        Log log = new Log();
                        log.logger.setLevel(Level.WARNING);
                        log.logger.warning("Warning msg - file has to exist");

                        log.logger.info("Warning file");

                        StringBuilder line = new StringBuilder();
                        while (fileIn.hasNextLine()) {
                            line.append(fileIn.nextLine()).append("\n");
                        }
                        new TextViewer(line.toString());
                    }
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(null, "Could not read the file!", "Warning", JOptionPane.PLAIN_MESSAGE);
                    e1.printStackTrace();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Problem!", "Warning", JOptionPane.PLAIN_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}