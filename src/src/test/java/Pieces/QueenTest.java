package Pieces;

import Board.Board;
import Board.SquarePosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void Basic_movement(){
        Board board = new Board();

        Queen queen = new Queen(PieceColor.BLACK, new SquarePosition(3,4));

        board.getSquare(3,4).set_piece(queen);

        assertTrue(queen.move(new SquarePosition(3,6), board));
        assertTrue(queen.move(new SquarePosition(3,2), board));
        assertTrue(queen.move(new SquarePosition(5,6), board));
        assertTrue(queen.move(new SquarePosition(2,3), board));
        assertTrue(queen.move(new SquarePosition(7,4), board));
        assertTrue(queen.move(new SquarePosition(0,4), board));
    }

    @Test
    void Movement_through_pieces(){
        Board board = new Board();
        board.getSquare(3,0).set_piece(null);
        board.getSquare(3,7).set_piece(null);

        Queen queen = new Queen(PieceColor.BLACK, new SquarePosition(3,4));

        board.getSquare(3,4).set_piece(queen);

        assertFalse(queen.move(new SquarePosition(3,0), board));
        assertFalse(queen.move(new SquarePosition(3,7), board));

    }

}