package GUI;

import Board.SquareColor;
import Board.SquarePosition;

import Controller.GameController;
import Pieces.Piece;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Game_GUI class - represents visualised board.
 * Creates GUI_board with 64 panels - squares. Put pieces
 * on the panels. Reacts on mouse clicks and returns
 * positions to the Game_controller class. After that
 * Game_controller decides whether  Game_GUI is ready to
 * be updated or no.
 */

public class GameGUI implements MouseListener, ActionListener {

    private ChessClock clock;
    private Player white_player;
    private Player black_player;
    private boolean white_to_move = true;
    private final GameController controller;
    final int square_size = 100;
    final int window_size = 835;
    final int rows = 8;

    final ChessClock white_clock;
    final ChessClock black_clock;

    final int columns = 8;
    private JPanel[][] GUI_board = new JPanel[columns][rows];

    private SquarePosition prev_pos = new SquarePosition(0,0);

    private JFrame game = new JFrame();

    private JLabel time  = new JLabel("Time");
    private JLabel b_label = new JLabel();
    private JLabel w_label = new JLabel();

    JButton w_resign_button;

    JButton b_resign_button;

    public GameGUI(String white_name, String black_name) throws IOException {

        w_resign_button = new JButton("Resign");
        b_resign_button = new JButton("Resign");

        w_resign_button.addActionListener(this);
        b_resign_button.addActionListener(this);

        white_clock = new ChessClock();
        black_clock = new ChessClock();

        w_resign_button.setBounds(800,600, 200, 75);
        b_resign_button.setBounds(800,100, 200, 75);

        w_resign_button.setFocusable(false);
        w_resign_button.setFont(new Font("Comic Sans", Font.BOLD,20));
        w_resign_button.setBackground(Color.WHITE);
        w_resign_button.setBorder(BorderFactory.createBevelBorder(3));

        b_resign_button.setFocusable(false);
        b_resign_button.setFont(new Font("Comic Sans", Font.BOLD,20));
        b_resign_button.setBackground(Color.WHITE);
        b_resign_button.setBorder(BorderFactory.createBevelBorder(3));

        white_clock.time_panel.setBounds(800,450, 200, 100);
        black_clock.time_panel.setBounds(800,200, 200, 100);

        white_clock.time_panel.setBackground(Color.DARK_GRAY);
        black_clock.time_panel.setBackground(Color.DARK_GRAY);


        white_player = new Player(PlayerColor.WHITE, white_name);
        black_player = new Player(PlayerColor.BLACK, black_name);

        controller = new GameController(white_player,black_player);

        w_label.setText(white_name);
        w_label.setFont(new Font("Comic sans", Font.ITALIC,30));
        time.setForeground(Color.WHITE);


        JPanel w_panel = new JPanel();
        w_panel.add(w_label);
        w_panel.setBounds(800,750, 200, 50);

        b_label.setText(black_name);
        b_label.setFont(new Font("Comic sans", Font.ITALIC,30));
        time.setForeground(Color.WHITE);

        JPanel b_panel = new JPanel();
        b_panel.add(b_label);
        b_panel.setBounds(800,0, 200, 50);

        time.setFont(new Font("Comic sans", Font.ITALIC,30));
        time.setForeground(Color.WHITE);
        time.setBounds(860,300, 100, 100);

        game.getContentPane().setBackground(Color.darkGray);
        game.setTitle("Game");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setResizable(false);
        game.setSize(1000, window_size);
        game.setLayout(null);
        game.setVisible(true);

        game.add(time);
        game.add(w_panel);
        game.add(b_panel);


        game.add(white_clock.time_panel);
        game.add(black_clock.time_panel);

        game.add(w_resign_button);
        game.add(b_resign_button);

        visualise_board();
        draw_pieces();

    }

    /**
     * Creates 32 black and white panels
     * for visualising board.
     */

    private void visualise_board(){
        SquareColor color = SquareColor.WHITE;
        for(int y = 0; y < rows; ++y){
            for(int x = 0; x < columns; ++x){
                GUI_board[x][y] = new JPanel();

                if (color == SquareColor.WHITE){
                    GUI_board[x][y].setBackground(Color.WHITE);
                    if (x != columns - 1){
                        color = SquareColor.BLACK;
                    }
                }else{
                    GUI_board[x][y].setBackground(Color.GRAY);
                    if (x != columns -1){
                        color = SquareColor.WHITE;
                    }
                }
                GUI_board[x][y].addMouseListener(this);
                GUI_board[x][y].setBounds(x*square_size,y*square_size,square_size,square_size);
                game.add(GUI_board[x][y]);
            }
        }
    }
    /**
     * Creates pieces as labels and putting
     * then on the panels.
     */

    private void draw_pieces() {
        for (int x = 0; x < rows; ++x) {
            for (int y = 0; y < columns; ++y) {
                GUI_board[x][y].removeAll();
                GUI_board[x][y].revalidate();
            }
        }

        for (int x = 0; x < rows; ++x) {
            for (int y = 0; y < columns; ++y) {
                Piece piece = controller.board.getSquare(x, y).get_piece();
                if (piece != null) {
                    GUI_board[x][y].add(new Pieces(piece.get_piece_name(), piece.get_Piece_color()).visualise_piece());
                    GUI_board[x][y].revalidate();
                }
            }
        }
    }

    /**
     * Updates board - changes piece position
     * for the one panel to another.
     *
     * @param position - current position to be updated.
     * @param prev_position - previous position.
     */
    private void refresh(SquarePosition position, SquarePosition prev_position){
        GUI_board[position.getColumn()][position.getRank()].removeAll();
        for (Component jc : GUI_board[prev_position.getColumn()][prev_position.getRank()].getComponents()) {
            if ( jc instanceof JLabel ) {
                GUI_board[position.getColumn()][position.getRank()].add(jc);
            }
        }
        GUI_board[prev_position.getColumn()][prev_position.getRank()].removeAll();
        GUI_board[position.getColumn()][position.getRank()].revalidate();
    }

    @Override
    public void mouseClicked(MouseEvent e){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int y = 0; y < rows; ++y) {
            for (int x = 0; x < columns; ++x) {
                if (GUI_board[x][y].equals(e.getSource())) {
                    if (prev_pos.getColumn() % 2 == 0 && prev_pos.getRank() % 2 == 0) {
                        GUI_board[prev_pos.getColumn()][prev_pos.getRank()].setBackground(Color.WHITE);
                    } else if (prev_pos.getColumn() % 2 != 0 && prev_pos.getRank() % 2 != 0) {
                        GUI_board[prev_pos.getColumn()][prev_pos.getRank()].setBackground(Color.WHITE);
                    } else {
                        GUI_board[prev_pos.getColumn()][prev_pos.getRank()].setBackground(Color.GRAY);
                    }
                    controller.setCur_pos(new SquarePosition(x, y));

                    if (controller.game_update(white_to_move)) {
                        draw_pieces();
                        white_to_move = !white_to_move;
                    }
                    if (white_clock.seconds == 0){
                        game_end(false);
                    }else if (black_clock.seconds == 0){
                        game_end(true);
                    }
                    if (controller.check_mate(white_to_move)){
                        game_end(!white_to_move);
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (int y = 0; y < rows; ++y) {
            for (int x = 0; x < columns; ++x) {
                if (GUI_board[x][y].equals(e.getSource())) {
                    GUI_board[x][y].setBackground(new Color(30,90,240));
                    prev_pos.set_position(x,y);
                    controller.setPrev_pos(prev_pos);

                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void game_end(boolean white_to_move){
        if (white_to_move){
            JOptionPane.showMessageDialog(null, "You did it! White won", "Wow", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "You did it! Black won", "Wow", JOptionPane.PLAIN_MESSAGE);
        }
        game.dispose();

        System.exit(1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == w_resign_button){
            game_end(false);
        }else if (e.getSource() == b_resign_button){
            game_end(true);
        }
    }
}