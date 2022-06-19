package Controller;


import GUI.GameWriter;
import GUI.Window;
import Board.Board;
import Board.SquarePosition;
import Pieces.PieceColor;
import Pieces.PieceNames;
import Pieces.Queen;
import Player.Player;

import java.io.IOException;

/**
 * Game_controller class - controller between model and
 * GUI visualisation of the board and pieces. Gets position
 * from the GUI package and decides what to do with it.
 */
public class GameController {

    private boolean white_long_castle = true;

    private boolean white_short_castle = true;

    private boolean black_long_castle = true;

    private boolean black_short_castle = true;


    private boolean end = false;
    private int move = 2;
    private SquarePosition cur_pos = new SquarePosition(0,0);
    private SquarePosition prev_pos = new SquarePosition(0,0);

    public Board board;

    private GameWriter game_writer;

    public GameController(Player white_player, Player black_player) throws IOException {
        game_writer = new GameWriter(white_player,black_player);
        board = new Board();

    }

    public static void main(String[] args) {
        new Window();

    }

    /**
     * "Great mind" gets which turn to play
     * and decides if the move is valid or not
     *
     * @return true if the move is possible, false
     * otherwise.
     *
     * @param white_to_move boolean variable represents
     * black or white to move.
     */
    public boolean game_update(boolean white_to_move){
        if (!board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).is_occupied()){
            return false;
        }
        if(white_to_move){
            if (board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).get_piece().get_Piece_color() == PieceColor.BLACK){
                return false;
            }
        }
        if(!white_to_move){
            if (board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).get_piece().get_Piece_color() == PieceColor.WHITE){
                return false;
            }
        }
        if (board.getSquare(cur_pos.getColumn(), cur_pos.getRank()).is_occupied() &&
                board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).is_occupied() &&
                board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).get_piece().get_Piece_color() ==
                        board.getSquare(cur_pos.getColumn(), cur_pos.getRank()).get_piece().get_Piece_color()){
            return false;
        }

        if (!board.getSquare(4,7).is_occupied()){
            white_short_castle = false;
            white_long_castle = false;
        }

        if (!board.getSquare(4,0).is_occupied()){
            black_short_castle = false;
            black_long_castle = false;
        }

        if (!board.getSquare(0,7).is_occupied()){
            white_long_castle = false;
        }

        if (!board.getSquare(7,7).is_occupied()){
            white_short_castle = false;
        }

        if (!board.getSquare(0,0).is_occupied()){
            black_long_castle = false;
        }
        if (!board.getSquare(7,0).is_occupied()){
            black_short_castle = false;
        }

        if (white_short_castle && cur_pos.getColumn() == 6 && cur_pos.getRank() == 7
                && !check_status(white_to_move) && !board.getSquare(6,7).is_occupied()
                && !board.getSquare(5,7).is_occupied()){
            board.getSquare(4,7).get_piece().set_position(new SquarePosition(6,7));
            board.getSquare(6,7).set_piece(board.getSquare(4,7).get_piece());
            board.getSquare(4,7).set_piece(null);

            board.getSquare(7,7).get_piece().set_position(new SquarePosition(5,7));
            board.getSquare(5,7).set_piece(board.getSquare(7,7).get_piece());
            board.getSquare(7,7).set_piece(null);
            return true;
        }

        if (white_long_castle && cur_pos.getColumn() == 2 && cur_pos.getRank() == 7
                && !check_status(white_to_move) && !board.getSquare(3,7).is_occupied()
                && !board.getSquare(2,7).is_occupied() && !board.getSquare(1,7).is_occupied()){
            board.getSquare(4,7).get_piece().set_position(new SquarePosition(2,7));
            board.getSquare(2,7).set_piece(board.getSquare(4,7).get_piece());
            board.getSquare(4,7).set_piece(null);

            board.getSquare(0,7).get_piece().set_position(new SquarePosition(3,7));
            board.getSquare(3,7).set_piece(board.getSquare(0,7).get_piece());
            board.getSquare(0,7).set_piece(null);
            return true;
        }

        if (black_short_castle && cur_pos.getColumn() == 6 && cur_pos.getRank() == 0
                && !check_status(white_to_move) && !board.getSquare(6,0).is_occupied()
                && !board.getSquare(5,0).is_occupied()){
            board.getSquare(4,0).get_piece().set_position(new SquarePosition(6,0));
            board.getSquare(6,0).set_piece(board.getSquare(4,0).get_piece());
            board.getSquare(4,0).set_piece(null);

            board.getSquare(7,0).get_piece().set_position(new SquarePosition(5,0));
            board.getSquare(5,0).set_piece(board.getSquare(7,0).get_piece());
            board.getSquare(7,0).set_piece(null);
            return true;
        }

        if (black_long_castle && cur_pos.getColumn() == 2 && cur_pos.getRank() == 0
                && !check_status(white_to_move) && !board.getSquare(3,0).is_occupied()
                && !board.getSquare(2,0).is_occupied() && !board.getSquare(1,0).is_occupied()){
            board.getSquare(4,0).get_piece().set_position(new SquarePosition(2,0));
            board.getSquare(2,0).set_piece(board.getSquare(4,0).get_piece());
            board.getSquare(4,0).set_piece(null);

            board.getSquare(0,0).get_piece().set_position(new SquarePosition(3,0));
            board.getSquare(3,0).set_piece(board.getSquare(0,0).get_piece());
            board.getSquare(0,0).set_piece(null);
            return true;
        }

        if (board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).get_piece().move(cur_pos, board)){

            if (board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).is_occupied() &&
                    board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).get_piece().get_piece_name() == PieceNames.PAWN
                    && cur_pos.getRank() == 0){
                board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).set_piece(null);
                board.getSquare(cur_pos.getColumn(), cur_pos.getRank()).set_piece(null);
                board.getSquare(cur_pos.getColumn(), cur_pos.getRank()).set_piece(new Queen(PieceColor.WHITE,
                        new SquarePosition(cur_pos.getColumn(), cur_pos.getRank())));
            }else if (board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).is_occupied() &&
                    board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).get_piece().get_piece_name() == PieceNames.PAWN
                    && cur_pos.getRank() == 7){
                board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).set_piece(null);
                board.getSquare(cur_pos.getColumn(), cur_pos.getRank()).set_piece(null);
                board.getSquare(cur_pos.getColumn(), cur_pos.getRank()).set_piece(new Queen(PieceColor.BLACK,
                        new SquarePosition(cur_pos.getColumn(), cur_pos.getRank())));
            }else{
                board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).get_piece().set_position(cur_pos);
                board.getSquare(cur_pos.getColumn(), cur_pos.getRank()).set_piece(board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).get_piece());
                board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).set_piece(null);

            }

            if (check_status(white_to_move)){
                board.getSquare(cur_pos.getColumn(), cur_pos.getRank()).get_piece().set_position(prev_pos);
                board.getSquare(prev_pos.getColumn(), prev_pos.getRank()).set_piece(board.getSquare(cur_pos.getColumn(), cur_pos.getRank()).get_piece());
                board.getSquare(cur_pos.getColumn(), cur_pos.getRank()).set_piece(null);
                return false;
            }

            if (check_mate(white_to_move)){
                end = true;
            }

            game_writer.write_move(board.getSquare(cur_pos.getColumn(), cur_pos.getRank()).get_piece().get_piece_name(),
                    cur_pos, move, end);
            move++;
            return true;
        }
        return false;
    }

    /**
     * Gets which move to play and goes through
     * the whole board and watch if any piece is attacking
     * the king.
     *
     * @return true if check false otherwise
     * @param white_to_move - current move
     */
    private boolean check_status(boolean white_to_move){
        if (white_to_move){
            SquarePosition king_pos = new SquarePosition(4,7);
            for(int x = 0;  x < 8; ++x){
                for(int y = 0; y < 8; ++y){
                    if (board.getSquare(x,y).is_occupied() && board.getSquare(x,y).get_piece().get_piece_name() == PieceNames.KING &&
                            board.getSquare(x,y).get_piece().get_Piece_color() == PieceColor.WHITE) {
                        king_pos.set_position(x, y);
                    }
                }
            }
            for(int x = 0;  x < 8; ++x){
                for(int y = 0; y < 8; ++y){
                    if (board.getSquare(x,y).is_occupied() && board.getSquare(x,y).get_piece().move(king_pos,board) &&
                            board.getSquare(x,y).get_piece().get_Piece_color() == PieceColor.BLACK){
                        return true;
                    }
                }
            }
        }else{
            SquarePosition king_pos = new SquarePosition(4,0);
            for(int x = 0;  x < 8; ++x){
                for(int y = 0; y < 8; ++y){
                    if (board.getSquare(x,y).is_occupied() && board.getSquare(x,y).get_piece().get_piece_name() == PieceNames.KING &&
                            board.getSquare(x,y).get_piece().get_Piece_color() == PieceColor.BLACK) {
                        king_pos.set_position(x, y);
                    }
                }
            }
            for(int x = 0;  x < 8; ++x){
                for(int y = 0; y < 8; ++y){
                    if (board.getSquare(x,y).is_occupied() && board.getSquare(x,y).get_piece().move(king_pos,board) &&
                            board.getSquare(x,y).get_piece().get_Piece_color() == PieceColor.WHITE){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Looks if there is any possible move
     *  to prevent check. If there is not
     *  any move that prevents check then
     *  it is checkmate.
     *
     * @param white_to_move - move to play
     * @return true if checkmate false otherwise.
     */
    public boolean check_mate(boolean white_to_move){
        if (white_to_move && check_status(white_to_move)){
            for(int x = 0; x < 8; ++x){
                for(int y = 0; y < 8; ++y){
                    if (board.getSquare(x,y).is_occupied() && board.getSquare(x,y).get_piece().get_Piece_color() == PieceColor.WHITE){
                        for(int i = 0; i < 8; ++i){
                            for(int j = 0; j < 8; ++j){
                                if ((board.getSquare(x,y).get_piece().move(new SquarePosition(i,j),board)
                                    && !board.getSquare(i,j).is_occupied()) || ( board.getSquare(i,j).is_occupied()
                                        && board.getSquare(i,j).get_piece().get_Piece_color() == PieceColor.BLACK)){

                                    board.getSquare(x,y).get_piece().set_position(new SquarePosition(i,j));
                                    board.getSquare(i,j).set_piece(board.getSquare(x,y).get_piece());
                                    board.getSquare(x,y).set_piece(null);

                                    if (!check_status(white_to_move)){
                                        board.getSquare(i,j).get_piece().set_position(new SquarePosition(x,y));
                                        board.getSquare(x,y).set_piece(board.getSquare(i,j).get_piece());
                                        board.getSquare(i,j).set_piece(null);
                                        return false;
                                    }
                                    board.getSquare(i,j).get_piece().set_position(new SquarePosition(x,y));
                                    board.getSquare(x,y).set_piece(board.getSquare(i,j).get_piece());
                                    board.getSquare(i,j).set_piece(null);
                                }
                            }
                        }
                    }
                }
            }
        }else if (!white_to_move && check_status(!white_to_move)){
            for(int x = 0; x < 8; ++x){
                for(int y = 0; y < 8; ++y){
                    if (board.getSquare(x,y).is_occupied() && board.getSquare(x,y).get_piece().get_Piece_color() == PieceColor.BLACK){
                        for(int i = 0; i < 8; ++i){
                            for(int j = 0; j < 8; ++j){
                                if ((board.getSquare(x,y).get_piece().move(new SquarePosition(i,j),board)
                                        && !board.getSquare(i,j).is_occupied()) || ( board.getSquare(i,j).is_occupied()
                                        && board.getSquare(i,j).get_piece().get_Piece_color() == PieceColor.WHITE)){

                                    board.getSquare(x,y).get_piece().set_position(new SquarePosition(i,j));
                                    board.getSquare(i,j).set_piece(board.getSquare(x,y).get_piece());
                                    board.getSquare(x,y).set_piece(null);

                                    if (!check_status(!white_to_move)){
                                        board.getSquare(i,j).get_piece().set_position(new SquarePosition(x,y));
                                        board.getSquare(x,y).set_piece(board.getSquare(i,j).get_piece());
                                        board.getSquare(i,j).set_piece(null);
                                        return false;
                                    }
                                    board.getSquare(i,j).get_piece().set_position(new SquarePosition(x,y));
                                    board.getSquare(x,y).set_piece(board.getSquare(i,j).get_piece());
                                    board.getSquare(i,j).set_piece(null);
                                }
                            }
                        }
                    }
                }
            }
        }
        return check_status(white_to_move);
    }
    /**
     * Gets previous position from the GUI
     * by clicking on the board and sets it
     * for the game controller.
     * @param position    position to be set
     */
    public void setPrev_pos(SquarePosition position){prev_pos.set_position(position.getColumn(),position.getRank());}

    /**
     * Gets current position from the GUI
     * by clicking on the board and sets it
     * for the game controller.
     * @param position    position to be set
     */
    public void setCur_pos(SquarePosition position){cur_pos.set_position(position.getColumn(),position.getRank());}

}