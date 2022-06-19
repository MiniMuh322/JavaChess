package Board;

import Pieces.Piece;

/**
 * Square class - represents a square.
 * Square has a position and a color.
 * Square can be occupied by a piece
 * or not.
 */
public class Square{
    private final SquareColor color;
    SquarePosition position;

    public Piece piece;
    public Square(SquareColor color, SquarePosition position){
        this.position = position;
        this.color = color;
    }

    /**
     * Put piece on the square.
     * @param piece    piece to be putted on the current square
     * null represents no piece on the square
     */
    public void set_piece(Piece piece){this.piece = piece; }

    /**
     * @return Returns piece on the square.
     */
    public Piece get_piece(){return this.piece;}

    /**
     * @return Returns square position.
     */
    public SquarePosition getPosition(){ return position;}


    public SquareColor getSquare_color(){ return color;}

    /**
     * @return Returns true if  square is occupied -
     *  false otherwise.
     */
    public boolean is_occupied() { return this.piece != null; }
}