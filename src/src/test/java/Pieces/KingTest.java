package Pieces;

import Board.Board;
import Board.SquarePosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void Movement_to_all_possible_squares(){
        Board board = new Board();
        board.getSquare(4,7).set_piece(null);

        King king = new King(PieceColor.BLACK, new SquarePosition(3,4));

        board.getSquare(3,4).set_piece(king);

        assertTrue(king.move(new SquarePosition(4, 4), board));
        assertTrue(king.move(new SquarePosition(2, 4), board));
        assertTrue(king.move(new SquarePosition(4, 3), board));
        assertTrue(king.move(new SquarePosition(2, 5), board));
        assertTrue(king.move(new SquarePosition(4, 5), board));
        assertTrue(king.move(new SquarePosition(4, 3), board));
        assertTrue(king.move(new SquarePosition(3, 5), board));
        assertTrue(king.move(new SquarePosition(3, 3), board));


    }
    @Test
    void Not_possible_movement(){
        Board board = new Board();
        board.getSquare(4,7).set_piece(null);
        board.getSquare(7,7).set_piece(null);

        King king = new King(PieceColor.BLACK, new SquarePosition(7,7));

        board.getSquare(7,7).set_piece(king);

        assertFalse(king.move(new SquarePosition(3, 4), board));
        assertFalse(king.move(new SquarePosition(3, 6), board));
        assertFalse(king.move(new SquarePosition(1, 4), board));
    }

    @Test
    void Edge_movement(){
        Board board = new Board();
        board.getSquare(4,7).set_piece(null);

        King king = new King(PieceColor.BLACK, new SquarePosition(3,4));

        board.getSquare(3,4).set_piece(king);

        assertFalse(king.move(new SquarePosition(7, 8), board));
        assertFalse(king.move(new SquarePosition(8, 7), board));

    }


}