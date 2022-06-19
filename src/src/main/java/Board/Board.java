package Board;
import Pieces.*;

import java.util.ArrayList;

import static Board.SquareColor.BLACK;
import static Board.SquareColor.WHITE;

/**
 * Board class - represents model board.
 * Contains squares Array for easy communication
 *
 * between Game_controller class and GUI package
 */
public class Board{

    /**
     * rows of the board
     */
    final int rows = 8;

    /**
     * columns of the board
     */
    final int columns = 8;


    private ArrayList<Piece> pieces = new ArrayList<>();

    /**
     * Array of the squares representing board
     */
    private Square[][] board = new Square[columns][rows];


    /**
     * After creating the board constructor
     * sets the board squares by it`s position
     * and colors. After that sets default pieces
     * on their position at the board array.
     */
    public Board(){
        set_board();
        set_pieces();
    }

    /**
     * Sets board by creating squares
     * and adding them to the board Array
     */
    private void set_board(){
        for(int y = columns - 1; y >= 0; --y){
            for(int x = 0; x < columns; ++x){
                board[x][y] = create_square(x,y);
            }
        }
    }

    /**
     * Creates pieces and set them on
     * the default positions on the board.
     */
    private void set_pieces(){
        for(int x = 0; x < columns; ++x){
            Piece whitePawn = new Pawn(PieceColor.WHITE, new SquarePosition(x,6));
            pieces.add(whitePawn);
            board[x][6].set_piece(whitePawn);

            Piece blackPawn = new Pawn(PieceColor.BLACK, new SquarePosition(x,1));
            pieces.add(blackPawn);
            board[x][1].set_piece(blackPawn);
        }

        for(int x = 0; x < columns; x = x + 7){
            Piece whiteRook = new Rook(PieceColor.WHITE, new SquarePosition(x,7));
            pieces.add(whiteRook);
            board[x][7].set_piece(whiteRook);

            Piece blackRook = new Rook(PieceColor.BLACK, new SquarePosition(x,0));
            pieces.add(blackRook);
            board[x][0].set_piece(blackRook);
        }

        for(int x = 1; x < columns; x = x + 5){
            Piece whiteKnight = new Knight(PieceColor.WHITE, new SquarePosition(x,7));
            pieces.add(whiteKnight);
            board[x][7].set_piece(whiteKnight);

            Piece blackKnight = new Knight(PieceColor.BLACK, new SquarePosition(x,0));
            pieces.add(blackKnight);
            board[x][0].set_piece(blackKnight);
        }

        for(int x = 2; x < columns; x = x + 3){
            Piece whiteBishop = new Bishop(PieceColor.WHITE, new SquarePosition(x,7));
            pieces.add(whiteBishop);
            board[x][7].set_piece(whiteBishop);

            Piece blackBishop = new Bishop(PieceColor.BLACK, new SquarePosition(x,0));
            pieces.add(blackBishop);
            board[x][0].set_piece(blackBishop);
        }

        Piece whiteKing = new King(PieceColor.WHITE, new SquarePosition(4,7));
        pieces.add(whiteKing);
        board[4][7].set_piece(whiteKing);
        Piece blackKing = new King(PieceColor.BLACK, new SquarePosition(4,0));
        pieces.add(blackKing);
        board[4][0].set_piece(blackKing);
        Piece whiteQueen = new Queen(PieceColor.WHITE, new SquarePosition(3,7));
        pieces.add(whiteQueen);
        board[3][7].set_piece(whiteQueen);
        Piece blackQueen = new Queen(PieceColor.BLACK, new SquarePosition(3,0));
        pieces.add(blackQueen);
        board[3][0].set_piece(blackQueen);

    }

    /**
     * Returns square by his position on the board
     * @param column    x position of the returning square on the board
     * @param rank      y position of the returning square on the board
     * @return square by its position
     */
    public Square getSquare(int column, int rank){
        return board[column][rank];
    }

    /**
     * Creates square by his position on the board
     * @param column    x position of the creating square on the board
     * @param rank      y position of the creating square on the board
     * @return creating new square by its position
     */
    private Square create_square(int column, int rank){
        SquareColor color;
        SquarePosition position = new SquarePosition(rank,column);
        if ( column % 2 == 0 && rank % 2 == 0){
            color = WHITE;
        }else if ( column % 2 != 0 && rank % 2 != 0){
            color = WHITE;
        }else {
            color = BLACK;
        }
        return new Square(color, position);
    }

    public ArrayList<Piece> getPieces(){
        return pieces;
    }
}