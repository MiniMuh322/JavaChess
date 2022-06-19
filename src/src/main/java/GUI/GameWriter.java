package GUI;

import Board.SquarePosition;
import Pieces.PieceNames;
import Player.Player;
import java.time.LocalDate;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Level;

public class GameWriter {
    private BufferedWriter game_writer = null;
    Log log;
    public GameWriter( Player white, Player black) throws IOException {
        log = new Log();

        try {
            game_writer = new BufferedWriter(new FileWriter(white.getPlayer_name() + " vs " + black.getPlayer_name() + ".txt"));
            game_writer.write("[" + white.getPlayer_name() + " vs " + black.getPlayer_name() + "]\n");
            game_writer.append("\n");

            LocalDate current_day = LocalDate.now();
            LocalTime time = LocalTime.now();

            game_writer.write("[" + String.valueOf(current_day) + "]\n");
            game_writer.write("[" + String.valueOf(time.withSecond(0)) + "]\n");
            game_writer.append("\n");

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write_move(PieceNames name, SquarePosition position, int move, boolean end){
        try {


            if (game_writer != null) {
                if (move % 6 == 0){
                    game_writer.append("\n");
                }
                if (move % 2 == 0) {
                    game_writer.write((move / 2) + "." + piece_name(name) + column_to_letters(position.getColumn()) + (8 - position.getRank())  + " ");
                }else{
                    game_writer.write(piece_name(name) + column_to_letters(position.getColumn()) + (8 - position.getRank()) + " ");
                }
                game_writer.flush();

                if (end){
                    if (move % 2 == 0){
                        game_writer.write(" 1 - 0 [White won]");
                    }else{
                        game_writer.write(" 0 - 1 [Black won]");
                    }
                    game_writer.flush();
                    game_writer.close();
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not write!", "Warning", JOptionPane.PLAIN_MESSAGE);
            log.logger.setLevel(Level.WARNING);
            log.logger.info("Warning file has to exist to write");
        }
    }

    private String column_to_letters(int column){
        return switch (column) {
            case 0 -> "a";
            case 1 -> "b";
            case 2 -> "c";
            case 3 -> "d";
            case 4 -> "e";
            case 5 -> "f";
            case 6 -> "g";
            default -> "h";
        };
    }

    private String piece_name(PieceNames name){
        return switch (name) {
            case PAWN -> "";
            case ROOK -> "R";
            case KNIGHT -> "Kn";
            case KING -> "K";
            case QUEEN -> "Q";
            case BISHOP -> "B";
        };
    }
}