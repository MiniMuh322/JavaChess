package Pieces;


import Board.Board;
import Board.SquarePosition;

/**
 * Piece class - piece model.
 * Every piece can move, piece
 * has a color and position. After
 * move piece can change position.
 */
public abstract class Piece {

    /**
     * piece color must be final.
     */
    final PieceColor color;
    /**
     * piece current position.
     */
    SquarePosition position;

    /**
     * Constructor creates new piece
     * with its color and position
     * @param color color of the created piece
     * @param position position  of the created piece
     */
    public  Piece(PieceColor color,SquarePosition position){
        this.position = position;
        this.color = color;
    }
    /**
     *  Gets possible move and Board with other pieces.
     *  @return true if the move is possible, false
     *  otherwise.
     *
     * @param pos_move - possible move.
     * @param board - model of a chess board.
     */
    public abstract boolean move(SquarePosition pos_move, Board board);

    /**
     * @return piece color.
     */
    public abstract PieceColor get_Piece_color();

    /**
     * @return current square position.
     */
    public abstract SquarePosition get_position();
    /**
     *  Sets new square position after any move.
     * @param position new position to be changed.
     */
    public abstract void set_position(SquarePosition position);
    /**
     * @return piece color.
     */
    public abstract PieceNames get_piece_name();

}