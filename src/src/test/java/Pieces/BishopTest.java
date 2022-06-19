package Pieces;

import Board.Board;
import Board.SquarePosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {
    @Test
    void Basic_movement(){
        Board board = new Board();
        board.getSquare(3,7).set_piece(null);

        Bishop bishop = new Bishop(PieceColor.BLACK, new SquarePosition(3,4));

        board.getSquare(3,4).set_piece(bishop);

        assertTrue(bishop.move(new SquarePosition(4,5), board));
        assertTrue(bishop.move(new SquarePosition(2,3), board));

    }

    @Test
    void Movement_through_pieces(){
        Board board = new Board();

        assertFalse(board.getSquare(2,0).get_piece().move(new SquarePosition(0,2),board));
        assertFalse(board.getSquare(2,0).get_piece().move(new SquarePosition(5,2),board));

    }
}