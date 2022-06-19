package Pieces;

import Board.Board;
import Board.SquarePosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void Basic_movement(){
        Board board = new Board();
        board.getSquare(3,7).set_piece(null);

        Rook rook = new Rook(PieceColor.BLACK, new SquarePosition(3,4));

        board.getSquare(3,4).set_piece(rook);

        assertTrue(rook.move(new SquarePosition(7,4), board));
        assertTrue(rook.move(new SquarePosition(0,4), board));
        assertTrue(rook.move(new SquarePosition(3,5), board));
        assertTrue(rook.move(new SquarePosition(3,2), board));
    }

    @Test
    void Movement_through_pieces(){
        Board board = new Board();
        board.getSquare(3,7).set_piece(null);

        Rook rook = new Rook(PieceColor.BLACK, new SquarePosition(3,4));

        board.getSquare(3,4).set_piece(rook);

        assertFalse(rook.move(new SquarePosition(3,7), board));

    }
}