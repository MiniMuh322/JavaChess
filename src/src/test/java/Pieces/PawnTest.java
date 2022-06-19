package Pieces;

import Board.Board;
import Board.SquarePosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    @Test
    void Basic_movement(){
        Board board = new Board();
        board.getSquare(3,7).set_piece(null);

        assertTrue(board.getSquare(3,1).get_piece().move(new SquarePosition(3,3), board));
        assertTrue(board.getSquare(3,1).get_piece().move(new SquarePosition(3,2), board));
        assertTrue(board.getSquare(3,6).get_piece().move(new SquarePosition(3,5), board));
        assertTrue(board.getSquare(3,6).get_piece().move(new SquarePosition(3,4), board));

    }
    @Test
    void Attack_move(){
        Board board = new Board();

        Pawn w_pawn = new Pawn(PieceColor.WHITE, new SquarePosition(5,2));
        board.getSquare(5,5).set_piece(w_pawn);
        Pawn b_pawn = new Pawn(PieceColor.BLACK, new SquarePosition(5,5));
        board.getSquare(5,3).set_piece(b_pawn);

        assertTrue(w_pawn.move(new SquarePosition(4,1),board));
        assertTrue(w_pawn.move(new SquarePosition(6,1),board));
        assertTrue(b_pawn.move(new SquarePosition(4,6),board));
        assertTrue(b_pawn.move(new SquarePosition(6,6),board));
    }
}