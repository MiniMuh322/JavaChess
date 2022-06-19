package GUI;


import Pieces.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pieces extends JFrame {
    final PieceNames name;
    final PieceColor color;

    private Image[] imgs = new Image[12];
    private BufferedImage all;

    public Pieces(PieceNames name, PieceColor color) {
        try {
            all = ImageIO.read(new File("src/pieces.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int ind = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance
                        (100, 100, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        this.color = color;
        this.name = name;
    }

    public JLabel visualise_piece(){
        return new JLabel(new ImageIcon(imgs[this.name.getIndex() + this.color.getIndex()]));
    }
}